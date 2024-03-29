package eu.isas.peptideshaker.protein_inference;

import com.compomics.util.exceptions.ExceptionHandler;
import com.compomics.util.experiment.biology.AminoAcidPattern;
import com.compomics.util.experiment.biology.AminoAcidSequence;
import com.compomics.util.experiment.biology.Ion;
import com.compomics.util.experiment.biology.PTM;
import com.compomics.util.experiment.biology.PTMFactory;
import com.compomics.util.experiment.biology.Peptide;
import com.compomics.util.experiment.biology.ions.TagFragmentIon;
import com.compomics.util.experiment.identification.Advocate;
import com.compomics.util.experiment.identification.NeutralLossesMap;
import com.compomics.util.experiment.identification.PeptideAssumption;
import com.compomics.util.experiment.identification.SearchParameters;
import com.compomics.util.experiment.identification.TagAssumption;
import com.compomics.util.experiment.identification.identification_parameters.PepnovoParameters;
import com.compomics.util.experiment.identification.matches.IonMatch;
import com.compomics.util.experiment.identification.matches.ModificationMatch;
import com.compomics.util.experiment.identification.matches.SpectrumMatch;
import com.compomics.util.experiment.identification.protein_inference.proteintree.ProteinTree;
import com.compomics.util.experiment.identification.spectrum_annotators.TagSpectrumAnnotator;
import com.compomics.util.experiment.identification.tags.Tag;
import com.compomics.util.experiment.identification.tags.TagComponent;
import com.compomics.util.experiment.identification.tags.tagcomponents.MassGap;
import com.compomics.util.experiment.io.identifications.IdfileReader;
import com.compomics.util.experiment.massspectrometry.MSnSpectrum;
import com.compomics.util.experiment.massspectrometry.SpectrumFactory;
import com.compomics.util.experiment.identification.SequenceFactory;
import com.compomics.util.experiment.identification.protein_inference.proteintree.ProteinTreeComponentsFactory;
import com.compomics.util.experiment.identification.tags.matchers.TagMatcher;
import com.compomics.util.memory.MemoryConsumptionStatus;
import com.compomics.util.preferences.AnnotationPreferences;
import com.compomics.util.preferences.IdentificationParameters;
import com.compomics.util.preferences.ModificationProfile;
import com.compomics.util.preferences.SequenceMatchingPreferences;
import com.compomics.util.waiting.WaitingHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import uk.ac.ebi.jmzml.xml.io.MzMLUnmarshallerException;

/**
 * This class can be used to map tags to proteins.
 *
 * @author Marc Vaudel
 */
public class TagMapper {

    /**
     * The spectrum factory.
     */
    private SpectrumFactory spectrumFactory = SpectrumFactory.getInstance();
    /**
     * The PTM factory.
     */
    private PTMFactory ptmFactory = PTMFactory.getInstance();
    /**
     * The protein tree to use for the mapping.
     */
    private ProteinTree proteinTree;
    /**
     * The identification parameters.
     */
    private IdentificationParameters identificationParameters;
    /**
     * Exception handler.
     */
    private ExceptionHandler exceptionHandler;
    /**
     * The sequence factory.
     */
    private SequenceFactory sequenceFactory = SequenceFactory.getInstance();

    /**
     * Constructor.
     *
     * @param proteinTree the protein tree to use for the mapping
     * @param identificationParameters the identification parameters
     * @param exceptionHandler an exception handler
     */
    public TagMapper(ProteinTree proteinTree, IdentificationParameters identificationParameters, ExceptionHandler exceptionHandler) {
        this.proteinTree = proteinTree;
        this.identificationParameters = identificationParameters;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * Maps the tags found in an identification files to the ProteinTree of this
     * mapper.
     *
     * @param idfileReader the identification file to map
     * @param waitingHandler a waiting handler used to display progress and
     * cancel the process
     * @param nThreads the number of threads to use
     *
     * @throws IOException thrown if an IOException occurs
     * @throws InterruptedException thrown if an InterruptedException occurs
     * @throws SQLException thrown if an SQLException occurs
     * @throws ClassNotFoundException thrown if a ClassNotFoundException occurs
     * @throws IllegalArgumentException thrown if an IllegalArgumentException
     * occurs
     * @throws MzMLUnmarshallerException thrown if an MzMLUnmarshallerException
     * occurs
     */
    public void mapTags(IdfileReader idfileReader, WaitingHandler waitingHandler, int nThreads) throws IOException,
            InterruptedException, ClassNotFoundException, SQLException, MzMLUnmarshallerException {
        if (nThreads == 1) {
            mapTagsSingleThread(idfileReader, waitingHandler);
        } else {
            mapTagsThreadingPerKey(idfileReader, waitingHandler, nThreads);
        }
    }

    /**
     * Maps tags in the protein database.
     *
     * @param idfileReader the id file reader
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws MzMLUnmarshallerException
     */
    private void mapTagsSingleThread(IdfileReader idfileReader, WaitingHandler waitingHandler) throws IOException, InterruptedException, ClassNotFoundException, SQLException, MzMLUnmarshallerException {

        HashMap<String, LinkedList<SpectrumMatch>> tagMap = idfileReader.getTagsMap();
        if (tagMap != null && !tagMap.isEmpty()) {
            waitingHandler.setMaxSecondaryProgressCounter(tagMap.size());
            waitingHandler.appendReport("Mapping de novo tags to peptides.", true, true);
            ModificationProfile modificationProfile = identificationParameters.getSearchParameters().getModificationProfile();
            for (String key : tagMap.keySet()) {
                TagMatcher tagMatcher = new TagMatcher(modificationProfile.getFixedModifications(), modificationProfile.getAllNotFixedModifications(), identificationParameters.getSequenceMatchingPreferences());
                Iterator<SpectrumMatch> matchIterator = tagMap.get(key).iterator();
                while (matchIterator.hasNext()) {
                    SpectrumMatch spectrumMatch = matchIterator.next();
                    mapTagsForSpectrumMatch(spectrumMatch, tagMatcher, key, waitingHandler, !matchIterator.hasNext());
                }
            }
        }
    }

    /**
     * Maps tags in the protein database.
     *
     * @param idfileReader the id file reader
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws MzMLUnmarshallerException
     */
    private void mapTagsThreadingPerMatch(IdfileReader idfileReader, WaitingHandler waitingHandler, int nThreads) throws IOException, InterruptedException, ClassNotFoundException, SQLException, MzMLUnmarshallerException {

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        HashMap<String, LinkedList<SpectrumMatch>> tagMap = idfileReader.getTagsMap();
        if (tagMap != null && !tagMap.isEmpty()) {
            waitingHandler.setMaxSecondaryProgressCounter(tagMap.size());
            waitingHandler.appendReport("Mapping de novo tags to peptides.", true, true);
            ModificationProfile modificationProfile = identificationParameters.getSearchParameters().getModificationProfile();
            for (String key : tagMap.keySet()) {
                TagMatcher tagMatcher = new TagMatcher(modificationProfile.getFixedModifications(), modificationProfile.getAllNotFixedModifications(), identificationParameters.getSequenceMatchingPreferences());
                tagMatcher.setSynchronizedIndexing(true);
                Iterator<SpectrumMatch> matchIterator = tagMap.get(key).iterator();
                while (matchIterator.hasNext()) {
                    SpectrumMatch spectrumMatch = matchIterator.next();
                    SpectrumMatchTagMapperRunnable tagMapperRunnable = new SpectrumMatchTagMapperRunnable(spectrumMatch, tagMatcher, key, waitingHandler, !matchIterator.hasNext());
                    pool.submit(tagMapperRunnable);
                    if (waitingHandler.isRunCanceled()) {
                        pool.shutdownNow();
                        return;
                    }
                }
            }
        }
        pool.shutdown();
        if (!pool.awaitTermination(1, TimeUnit.DAYS)) {
            waitingHandler.appendReport("Mapping tags timed out. Please contact the developers.", true, true);
        }
    }

    /**
     * Maps tags in the protein database.
     *
     * @param idfileReader the id file reader
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws MzMLUnmarshallerException
     */
    private void mapTagsThreadingPerKey(IdfileReader idfileReader, WaitingHandler waitingHandler, int nThreads) throws IOException, InterruptedException, ClassNotFoundException, SQLException, MzMLUnmarshallerException {

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        HashMap<String, LinkedList<SpectrumMatch>> tagMap = idfileReader.getTagsMap();
        if (tagMap != null && !tagMap.isEmpty()) {
            waitingHandler.setMaxSecondaryProgressCounter(tagMap.size());
            waitingHandler.appendReport("Mapping de novo tags to peptides.", true, true);
            ModificationProfile modificationProfile = identificationParameters.getSearchParameters().getModificationProfile();
            for (String key : tagMap.keySet()) {
                LinkedList<SpectrumMatch> spectrumMatches = tagMap.get(key);
                KeyTagMapperRunnable tagMapperRunnable = new KeyTagMapperRunnable(spectrumMatches, modificationProfile.getFixedModifications(), modificationProfile.getAllNotFixedModifications(), identificationParameters.getSequenceMatchingPreferences(), key, waitingHandler);
                pool.submit(tagMapperRunnable);
                if (waitingHandler.isRunCanceled()) {
                    pool.shutdownNow();
                    return;
                }
            }
        }
        pool.shutdown();
        if (!pool.awaitTermination(1, TimeUnit.DAYS)) {
            waitingHandler.appendReport("Mapping tags timed out. Please contact the developers.", true, true);
        }
    }

    /**
     * Maps tags in the protein database.
     *
     * @param idfileReader the id file reader
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws MzMLUnmarshallerException
     */
    private void mapTagsForSpectrumMatch(SpectrumMatch spectrumMatch, TagMatcher tagMatcher, String key, WaitingHandler waitingHandler, boolean increaseProgress) throws IOException, InterruptedException, ClassNotFoundException, SQLException, MzMLUnmarshallerException {

        TagSpectrumAnnotator spectrumAnnotator = new TagSpectrumAnnotator();
        int keySize = key.length();
        ArrayList<Integer> charges = new ArrayList<Integer>(1);
        charges.add(1); //@TODO: use other charges?
        String spectrumKey = spectrumMatch.getKey();
        MSnSpectrum spectrum = (MSnSpectrum) spectrumFactory.getSpectrum(spectrumKey);
        AnnotationPreferences annotationPreferences = identificationParameters.getAnnotationPreferences();
        SequenceMatchingPreferences sequenceMatchingPreferences = identificationParameters.getSequenceMatchingPreferences();
        SearchParameters searchParameters = identificationParameters.getSearchParameters();
        HashMap<Integer, HashMap<String, ArrayList<TagAssumption>>> tagAssumptionsMap = spectrumMatch.getTagAssumptionsMap(keySize, identificationParameters.getSequenceMatchingPreferences());
        for (int advocateId : tagAssumptionsMap.keySet()) {
            HashMap<String, ArrayList<TagAssumption>> algorithmTags = tagAssumptionsMap.get(advocateId);
            ArrayList<TagAssumption> tagAssumptions = algorithmTags.get(key);
            if (tagAssumptions != null) {
                ArrayList<String> inspectedTags = new ArrayList<String>();
                ArrayList<String> peptidesFound = new ArrayList<String>();
                for (TagAssumption tagAssumption : tagAssumptions) {
                    String tagSequence = tagAssumption.getTag().asSequence();
                    if (!inspectedTags.contains(tagSequence)) {
                        mapPtmsForTag(tagAssumption.getTag(), advocateId);
                        ArrayList<TagAssumption> extendedTagList = new ArrayList<TagAssumption>();
                        extendedTagList.add(tagAssumption);
                        ArrayList<IonMatch> annotations = spectrumAnnotator.getSpectrumAnnotation(annotationPreferences.getIonTypes(),
                                new NeutralLossesMap(),
                                charges,
                                tagAssumption.getIdentificationCharge().value,
                                spectrum, tagAssumption.getTag(),
                                0,
                                annotationPreferences.getFragmentIonAccuracy(),
                                false, annotationPreferences.isHighResolutionAnnotation());
                        int nB = 0, nY = 0;
                        for (IonMatch ionMatch : annotations) {
                            Ion ion = ionMatch.ion;
                            if (ion instanceof TagFragmentIon) {
                                int ionType = ion.getSubType();
                                if (ionType == TagFragmentIon.A_ION
                                        || ionType == TagFragmentIon.B_ION
                                        || ionType == TagFragmentIon.C_ION) {
                                    nB++;
                                } else {
                                    nY++;
                                }
                            }
                        }
                        if (nB < 3) {
                            extendedTagList.addAll(tagAssumption.getPossibleTags(false, searchParameters.getMinChargeSearched().value, searchParameters.getMaxChargeSearched().value, 2));
                        }
                        if (nY < 3) {
                            extendedTagList.addAll(tagAssumption.getPossibleTags(true, searchParameters.getMinChargeSearched().value, searchParameters.getMaxChargeSearched().value, 2));
                        }
                        if (tagAssumption.getTag().canReverse()) {
                            extendedTagList.add(tagAssumption.reverse(nY >= nB));
                        }
                        for (TagAssumption extendedAssumption : extendedTagList) {
                            // free memory if needed and possible
                            if (MemoryConsumptionStatus.memoryUsed() > 0.9) {
                                tagMatcher.setUseCache(false);
                                tagMatcher.clearCache();
                            }
                            HashMap<Peptide, HashMap<String, ArrayList<Integer>>> proteinMapping = proteinTree.getProteinMapping(extendedAssumption.getTag(), tagMatcher, sequenceMatchingPreferences, searchParameters.getFragmentIonAccuracy());
                            for (Peptide peptide : proteinMapping.keySet()) {
                                String peptideKey = peptide.getKey();
                                if (!peptidesFound.contains(peptideKey)) {
                                    PeptideAssumption peptideAssumption = new PeptideAssumption(peptide, extendedAssumption.getRank(), advocateId, tagAssumption.getIdentificationCharge(), tagAssumption.getScore(), tagAssumption.getIdentificationFile());
                                    peptideAssumption.addUrParam(tagAssumption);
                                    spectrumMatch.addHit(advocateId, peptideAssumption, true);
                                    peptidesFound.add(peptideKey);
                                }
                            }
                            String extendedSequence = extendedAssumption.getTag().asSequence();
                            inspectedTags.add(extendedSequence);
                        }
                    }
                }
            }
        }
        if (increaseProgress) {
            tagMatcher.clearCache();
            waitingHandler.increaseSecondaryProgressCounter();
        }
        if (MemoryConsumptionStatus.memoryUsed() > 0.8 && !ProteinTreeComponentsFactory.getInstance().getCache().isEmpty()) {
            ProteinTreeComponentsFactory.getInstance().getCache().reduceMemoryConsumption(0.5, null);
        }
        // free memory if needed and possible
        if (MemoryConsumptionStatus.memoryUsed() > 0.9) {
            tagMatcher.clearCache();
        }
        if (MemoryConsumptionStatus.memoryUsed() > 0.9 && sequenceFactory.getNodesInCache() > 0) {
            sequenceFactory.reduceNodeCacheSize(0.5);
        }
    }

    /**
     * Remaps the PTMs for a given tag based on the search parameters.
     *
     * @param tag the tag with original algorithm PTMs
     * @param searchParameters the parameters used for the identification
     * @param sequenceMatchingPreferences the sequence matching preferences
     * @param advocateId the ID of the advocate
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void mapPtmsForTag(Tag tag, int advocateId) throws IOException, InterruptedException, FileNotFoundException, ClassNotFoundException, SQLException {

        SearchParameters searchParameters = identificationParameters.getSearchParameters();
        ModificationProfile modificationProfile = searchParameters.getModificationProfile();
        // add the fixed PTMs
        ptmFactory.checkFixedModifications(modificationProfile, tag, identificationParameters.getSequenceMatchingPreferences());

        // rename the variable modifications
        for (TagComponent tagComponent : tag.getContent()) {
            if (tagComponent instanceof AminoAcidPattern) {

                AminoAcidPattern aminoAcidPattern = (AminoAcidPattern) tagComponent;

                for (int aa : aminoAcidPattern.getModificationIndexes()) {
                    for (ModificationMatch modificationMatch : aminoAcidPattern.getModificationsAt(aa)) {
                        if (modificationMatch.isVariable()) {
                            if (advocateId == Advocate.pepnovo.getIndex()) {
                                String pepnovoPtmName = modificationMatch.getTheoreticPtm();
                                PepnovoParameters pepnovoParameters = (PepnovoParameters) searchParameters.getIdentificationAlgorithmParameter(advocateId);
                                String utilitiesPtmName = pepnovoParameters.getUtilitiesPtmName(pepnovoPtmName);
                                if (utilitiesPtmName == null) {
                                    throw new IllegalArgumentException("PepNovo+ PTM " + pepnovoPtmName + " not recognized.");
                                }
                                modificationMatch.setTheoreticPtm(utilitiesPtmName);
                            } else if (advocateId == Advocate.direcTag.getIndex()) {
                                Integer directagIndex = new Integer(modificationMatch.getTheoreticPtm());
                                String utilitiesPtmName = modificationProfile.getVariableModifications().get(directagIndex);
                                if (utilitiesPtmName == null) {
                                    throw new IllegalArgumentException("DirecTag PTM " + directagIndex + " not recognized.");
                                }
                                modificationMatch.setTheoreticPtm(utilitiesPtmName);
                                PTM ptm = ptmFactory.getPTM(utilitiesPtmName);
                                ArrayList<Character> aaAtTarget = ptm.getPattern().getAminoAcidsAtTarget();
                                if (aaAtTarget.size() > 1) {
                                    throw new IllegalArgumentException("More than one amino acid can be targeted by the modification " + ptm + ", tag duplication required.");
                                }
                                int aaIndex = aa - 1;
                                aminoAcidPattern.setTargeted(aaIndex, aaAtTarget);
                            } else {
                                Advocate notImplemented = Advocate.getAdvocate(advocateId);
                                if (notImplemented == null) {
                                    throw new IllegalArgumentException("Advocate of id " + advocateId + " not recognized.");
                                }
                                throw new IllegalArgumentException("PTM mapping not implemented for " + Advocate.getAdvocate(advocateId).getName() + ".");
                            }
                        }
                    }
                }
            } else if (tagComponent instanceof AminoAcidSequence) {

                AminoAcidSequence aminoAcidSequence = (AminoAcidSequence) tagComponent;

                for (int aa : aminoAcidSequence.getModificationIndexes()) {
                    for (ModificationMatch modificationMatch : aminoAcidSequence.getModificationsAt(aa)) {
                        if (modificationMatch.isVariable()) {
                            if (advocateId == Advocate.pepnovo.getIndex()) {
                                String pepnovoPtmName = modificationMatch.getTheoreticPtm();
                                PepnovoParameters pepnovoParameters = (PepnovoParameters) searchParameters.getIdentificationAlgorithmParameter(advocateId);
                                String utilitiesPtmName = pepnovoParameters.getUtilitiesPtmName(pepnovoPtmName);
                                if (utilitiesPtmName == null) {
                                    throw new IllegalArgumentException("PepNovo+ PTM " + pepnovoPtmName + " not recognized.");
                                }
                                modificationMatch.setTheoreticPtm(utilitiesPtmName);
                            } else if (advocateId == Advocate.direcTag.getIndex()) {
                                Integer directagIndex = new Integer(modificationMatch.getTheoreticPtm());
                                String utilitiesPtmName = modificationProfile.getVariableModifications().get(directagIndex);
                                if (utilitiesPtmName == null) {
                                    throw new IllegalArgumentException("DirecTag PTM " + directagIndex + " not recognized.");
                                }
                                modificationMatch.setTheoreticPtm(utilitiesPtmName);
                                PTM ptm = ptmFactory.getPTM(utilitiesPtmName);
                                ArrayList<Character> aaAtTarget = ptm.getPattern().getAminoAcidsAtTarget();
                                if (aaAtTarget.size() > 1) {
                                    throw new IllegalArgumentException("More than one amino acid can be targeted by the modification " + ptm + ", tag duplication required.");
                                }
                                int aaIndex = aa - 1;
                                aminoAcidSequence.setAaAtIndex(aaIndex, aaAtTarget.get(0));
                            } else {
                                Advocate notImplemented = Advocate.getAdvocate(advocateId);
                                if (notImplemented == null) {
                                    throw new IllegalArgumentException("Advocate of id " + advocateId + " not recognized.");
                                }
                                throw new IllegalArgumentException("PTM mapping not implemented for " + Advocate.getAdvocate(advocateId).getName() + ".");
                            }
                        }
                    }
                }
            } else if (tagComponent instanceof MassGap) {
                // no PTM there
            } else {
                throw new UnsupportedOperationException("PTM mapping not implemeted for tag component " + tagComponent.getClass() + ".");
            }
        }
    }

    /**
     * Private runnable to map tags of all spectrum matches of a key.
     */
    private class KeyTagMapperRunnable implements Runnable {

        /**
         * The spectrum matches to process.
         */
        private final LinkedList<SpectrumMatch> spectrumMatches;

        /**
         * The tree key.
         */
        private final String key;

        /**
         * The waiting handler to display progress and cancel the process.
         */
        private final WaitingHandler waitingHandler;
        /**
         * The tag to protein matcher.
         */
        private final TagMatcher tagMatcher;

        /**
         * Constructor.
         *
         * @param spectrumMatches the spectrum matches to process
         * @param key the key of tags to map
         * @param waitingHandler waiting handler to display progress and cancel
         * the process
         */
        public KeyTagMapperRunnable(LinkedList<SpectrumMatch> spectrumMatches, ArrayList<String> fixedModifications, ArrayList<String> variableModifications, SequenceMatchingPreferences sequenceMatchingPreferences, String key, WaitingHandler waitingHandler) {
            this.spectrumMatches = spectrumMatches;
            this.key = key;
            this.waitingHandler = waitingHandler;
            this.tagMatcher = new TagMatcher(fixedModifications, variableModifications, sequenceMatchingPreferences);
        }

        @Override
        public void run() {

            try {
                Iterator<SpectrumMatch> matchIterator = spectrumMatches.iterator();
                while (matchIterator.hasNext()) {
                    SpectrumMatch spectrumMatch = matchIterator.next();
                    if (!waitingHandler.isRunCanceled()) {
                        mapTagsForSpectrumMatch(spectrumMatch, tagMatcher, key, waitingHandler, !matchIterator.hasNext());
                    }
                }
            } catch (Exception e) {
                if (!waitingHandler.isRunCanceled()) {
                    exceptionHandler.catchException(e);
                    waitingHandler.setRunCanceled();
                }
            }
        }
    }

    /**
     * Private runnable to map tags of a spectrum match.
     */
    private class SpectrumMatchTagMapperRunnable implements Runnable {

        /**
         * The spectrum match to process.
         */
        private final SpectrumMatch spectrumMatch;

        /**
         * The tree key.
         */
        private final String key;

        /**
         * The waiting handler to display progress and cancel the process.
         */
        private final WaitingHandler waitingHandler;
        /**
         * boolean indicating whether the progress bar should be increased.
         */
        private final boolean increaseProgress;
        /**
         * The tag to protein matcher.
         */
        private final TagMatcher tagMatcher;

        /**
         * Constructor.
         *
         * @param spectrumMatch the spectrum match to process
         * @param key the key of tags to map
         * @param waitingHandler waiting handler to display progress and cancel
         * the process
         */
        public SpectrumMatchTagMapperRunnable(SpectrumMatch spectrumMatch, TagMatcher tagMatcher, String key, WaitingHandler waitingHandler, boolean increaseProgress) {
            this.spectrumMatch = spectrumMatch;
            this.key = key;
            this.waitingHandler = waitingHandler;
            this.increaseProgress = increaseProgress;
            this.tagMatcher = tagMatcher;
        }

        @Override
        public void run() {

            try {
                if (!waitingHandler.isRunCanceled()) {
                    mapTagsForSpectrumMatch(spectrumMatch, tagMatcher, key, waitingHandler, increaseProgress);
                }
            } catch (Exception e) {
                if (!waitingHandler.isRunCanceled()) {
                    exceptionHandler.catchException(e);
                    waitingHandler.setRunCanceled();
                }
            }
        }
    }
}
