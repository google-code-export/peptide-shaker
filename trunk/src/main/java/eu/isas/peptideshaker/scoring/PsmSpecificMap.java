package eu.isas.peptideshaker.scoring;

import eu.isas.peptideshaker.scoring.targetdecoy.TargetDecoyMap;
import com.compomics.util.experiment.identification.matches.SpectrumMatch;
import com.compomics.util.waiting.WaitingHandler;
import eu.isas.peptideshaker.PeptideShaker;
import eu.isas.peptideshaker.filtering.PsmFilter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This map will store target decoy informations about the psms grouped
 * according to their precursor charge.
 *
 * @author Marc Vaudel
 */
public class PsmSpecificMap implements Serializable {

    /**
     * Serial version UID for post-serialization compatibility.
     */
    static final long serialVersionUID = 746516685643358198L;
    /**
     * The map of the psm target/decoy maps indexed by the psm charge.
     */
    private HashMap<Integer, TargetDecoyMap> psmsMaps = new HashMap<Integer, TargetDecoyMap>();
    /**
     * Map used to group charges together in order to ensure statistical.
     * relevance
     */
    private HashMap<Integer, Integer> grouping = new HashMap<Integer, Integer>();
    /**
     * The filters to use to flag doubtful matches.
     * @deprecated use the specific map instead
     */
    private ArrayList<PsmFilter> doubtfulMatchesFilters = null;
    /**
     * The filters to use to flag doubtful matches in a map: charge -> file name -> list of filters
     */
    private HashMap<Integer, HashMap< String, ArrayList<PsmFilter>>> doubtfulMatchesFiltersSpecificMap = new HashMap<Integer, HashMap< String, ArrayList<PsmFilter>>>();

    /**
     * Constructor.
     */
    public PsmSpecificMap() {
    }

    /**
     * Returns the filters used to flag doubtful matches in a map.
     * 
     * @return the filters used to flag doubtful matches
     */
    public HashMap<Integer, HashMap< String, ArrayList<PsmFilter>>> getDoubtfulMatchesFilters() {
        if (doubtfulMatchesFiltersSpecificMap == null) { // Backward compatibility check for projects without filters
            doubtfulMatchesFiltersSpecificMap = new HashMap<Integer, HashMap< String, ArrayList<PsmFilter>>>();
        }
        return doubtfulMatchesFiltersSpecificMap;
    }

    /**
     * Returns the filters used to flag doubtful matches corresponding to the given charge and file. An empty list if none found.
     * 
     * @param charge the charge of the psm
     * @param fileName the name of the spectrum file
     * @return the filters used to flag doubtful matches
     */
    public ArrayList<PsmFilter> getDoubtfulMatchesFilters(Integer charge, String fileName) {
        if (doubtfulMatchesFiltersSpecificMap == null) { // Backward compatibility check for projects without filters
            doubtfulMatchesFiltersSpecificMap = new HashMap<Integer, HashMap< String, ArrayList<PsmFilter>>>();
        }
        HashMap< String, ArrayList<PsmFilter>> chargeFilters = doubtfulMatchesFiltersSpecificMap.get(charge);
        if (chargeFilters == null) {
            return new ArrayList<PsmFilter>();
        }
        ArrayList<PsmFilter> fileFilters = chargeFilters.get(fileName);
        if (fileFilters == null) {
            return new ArrayList<PsmFilter>();
        }
        return fileFilters;
    }
    
    /**
     * Adds a PSM filter to the list of doubtful matches filters.
     * 
     * @param charge the charge of the psm
     * @param fileName the name of the spectrum file
     * @param psmFilter the new filter to add
     */
    public void addDoubtfulMatchesFilter(Integer charge, String fileName, PsmFilter psmFilter) {
        HashMap< String, ArrayList<PsmFilter>> chargeFilters = doubtfulMatchesFiltersSpecificMap.get(charge);
        if (chargeFilters == null) {
            chargeFilters = new HashMap<String, ArrayList<PsmFilter>>();
            doubtfulMatchesFiltersSpecificMap.put(charge, chargeFilters);
        }
        ArrayList<PsmFilter> fileFilters = chargeFilters.get(fileName);
        if (fileFilters == null) {
            fileFilters = new ArrayList<PsmFilter>();
            chargeFilters.put(fileName, fileFilters);
        }
        fileFilters.add(psmFilter);
    }

    /**
     * Estimate the posterior error probabilities of the PSMs.
     *
     * @param waitingHandler the handler displaying feedback to the user
     */
    public void estimateProbabilities(WaitingHandler waitingHandler) {

        int max = getMapsSize();
        waitingHandler.setSecondaryProgressCounterIndeterminate(false);
        waitingHandler.setMaxSecondaryProgressCounter(max);

        for (Integer charge : psmsMaps.keySet()) {

            waitingHandler.increaseSecondaryProgressCounter();

            if (!grouping.containsKey(charge)) {
                psmsMaps.get(charge).estimateProbabilities(waitingHandler);
            }
        }

        waitingHandler.setSecondaryProgressCounterIndeterminate(true);
    }

    /**
     * Returns the probability of the given spectrum match at the given score.
     *
     * @param specificKey the charge of the match of interest
     * @param score the corresponding score
     * @return the probability of the given spectrum match at the given score
     */
    public double getProbability(int specificKey, double score) {
        int key = getCorrectedKey(specificKey);
        return psmsMaps.get(key).getProbability(score);
    }

    /**
     * Returns the probability of the given spectrum match at the given score.
     *
     * @param specificKey the charge of the match
     * @param score the corresponding score
     * @return the probability of the given spectrum match at the given score
     */
    public double getProbability(String specificKey, double score) {
        Integer keeyAsInteger;
        try {
            keeyAsInteger = new Integer(specificKey);
        } catch (Exception e) {
            throw new IllegalArgumentException("PSM maps are indexed by charge. Input: " + specificKey);
        }
        return getProbability(keeyAsInteger, score);
    }

    /**
     * Adds a point representing the corresponding spectrum match at a given
     * score.
     *
     * @param probabilityScore the estimated score
     * @param spectrumMatch the spectrum match of interest
     * @param mzTolerance The ms2 m/z tolerance
     * 
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void addPoint(double probabilityScore, SpectrumMatch spectrumMatch, double mzTolerance) throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        int key = getKey(spectrumMatch);
        if (!psmsMaps.containsKey(key)) {
            psmsMaps.put(key, new TargetDecoyMap());
        }
        psmsMaps.get(key).put(probabilityScore, spectrumMatch.getBestPeptideAssumption().getPeptide().isDecoy(PeptideShaker.MATCHING_TYPE, mzTolerance));
    }

    /**
     * Returns a list of keys from maps presenting a suspicious input.
     *
     * @return a list of keys from maps presenting a suspicious input
     */
    public ArrayList<String> suspiciousInput() {
        ArrayList<String> result = new ArrayList<String>();
        for (Integer key : psmsMaps.keySet()) {
            if (!grouping.containsKey(key) && psmsMaps.get(key).suspiciousInput() && !grouping.containsKey(key)) {
                result.add(getGroupKey(key));
            }
        }
        return result;
    }

    /**
     * This method groups the statistically non significant psms with the ones
     * having a charge directly smaller.
     */
    public void clean() {
        ArrayList<Integer> charges = new ArrayList(psmsMaps.keySet());
        Collections.sort(charges);
        int ref = 0;
        for (int charge : charges) {
            if (psmsMaps.get(charge).getnMax() >= 100 && psmsMaps.get(charge).getnTargetOnly() >= 100) {
                ref = charge;
            } else if (ref == 0) {
                ref = charge;
            } else {
                grouping.put(charge, ref);
            }
        }
        for (int charge : grouping.keySet()) {
            ref = grouping.get(charge);
            psmsMaps.get(ref).addAll(psmsMaps.get(charge));
        }
    }

    /**
     * Returns a map of the keys: charge -> group name.
     *
     * @return a map of the keys: charge -> group name
     */
    public HashMap<Integer, String> getKeys() {
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        for (int key : psmsMaps.keySet()) {
            if (!grouping.containsKey(key)) {
                result.put(key, getGroupKey(key));
            }
        }
        return result;
    }

    /**
     * Return a key of the selected charge group indexed by the main charge.
     *
     * @param mainCharge the selected charge
     * @return key of the corresponding charge group
     */
    private String getGroupKey(Integer mainCharge) {
        String tempKey = mainCharge + "";
        for (int mergedKey : grouping.keySet()) {
            if (grouping.get(mergedKey) == mainCharge) {
                tempKey += ", " + mergedKey;
            }
        }
        return tempKey;
    }

    /**
     * Returns the key (here the charge) associated to the corresponding
     * spectrum match after curation.
     *
     * @param specificKey the spectrum match of interest
     * @return the corresponding key
     */
    public Integer getCorrectedKey(int specificKey) {
        if (grouping.containsKey(specificKey)) {
            return grouping.get(specificKey);
        }
        return specificKey;
    }

    /**
     * Returns the key (here the charge) associated to the corresponding
     * spectrum match after curation.
     *
     * @param specificKey the spectrum match of interest
     * @return the corresponding key
     */
    public Integer getCorrectedKey(String specificKey) {
        Integer keeyAsInteger;
        try {
            keeyAsInteger = new Integer(specificKey);
        } catch (Exception e) {
            throw new IllegalArgumentException("PSM maps are indexed by charge. Input: " + specificKey);
        }
        return getCorrectedKey(keeyAsInteger);
    }

    /**
     * Returns the key (here the charge) associated to the corresponding
     * spectrum match.
     *
     * @param spectrumMatch the spectrum match of interest
     * @return the corresponding key
     */
    public Integer getKey(SpectrumMatch spectrumMatch) {
        try {
            return spectrumMatch.getBestPeptideAssumption().getIdentificationCharge().value;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the desired target decoy map.
     *
     * @param key the key of the desired map
     * @return the corresponding target decoy map
     */
    public TargetDecoyMap getTargetDecoyMap(int key) {
        return psmsMaps.get(key);
    }

    /**
     * Returns the overall number of points across all maps.
     *
     * @return the overall number of points across all maps.
     */
    public int getMapsSize() {
        int result = 0;
        for (TargetDecoyMap targetDecoyMap : psmsMaps.values()) {
            result += targetDecoyMap.getMapSize();
        }
        return result;
    }

    /**
     * Returns the maximal precursor charge observed in the identified spectra.
     *
     * @return the maximal precursor charge observed in the identified spectra
     */
    public int getMaxCharge() {
        int maxCharge = 0;
        for (int charge : psmsMaps.keySet()) {
            if (charge > maxCharge) {
                maxCharge = charge;
            }
        }
        return maxCharge;
    }
}
