package eu.isas.peptideshaker.export.exportfeatures;

import com.compomics.util.io.export.ExportFeature;
import static eu.isas.peptideshaker.export.exportfeatures.ValidationFeatures.values;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class lists the peptide identification features.
 *
 * @author Marc Vaudel
 */
public enum PeptideFeatures implements ExportFeature {

    accessions("Protein(s)", "Protein(s) to which this peptide can be attached.", false),
    protein_description("Description(s)", "Description of the Protein(s) to which this peptide can be attached.", false),
    unique("Unique", "Indicates whether the peptide is found uniquely in the protein match of interest.", false),
    pi("PI", "The protein inference status of this peptide.", false),
    sequence("Sequence", "Sequence of the peptide.", false),
    missed_cleavages("Missed Cleavages", "The number of missed cleavages.", false),
    modified_sequence("Modified Sequence", "The peptide sequence annotated with variable modifications.", false),
    position("Position", "Position of the peptide in the protein sequence.", false),
    aaBefore("AAs Before", "The amino-acids before the sequence.", false),
    aaAfter("AAs After", "The amino-acids after the sequence.", false),
    variable_ptms("Variable Modifications", "The variable modifications.", false),
    fixed_ptms("Fixed Modifications", "The fixed modifications.", false),
    localization_confidence("Localization Confidence", "The confidence in PTMs localization.", false),
    confident_phosphosites("Confident Phosphosites", "The confidently localized phosphorylation sites.", false),
    other_phosphosites("Other Phosphosites", "The other phosphorylation sites.", false),
    validated_psms("#Validated PSMs", "Number of validated PSMs.", false),
    psms("#PSMs", "Number of PSMs.", false),
    score("Score", "Score of the peptide.", true),
    raw_score("Raw Score", "Peptide score before log transform.", true),
    confidence("Confidence", "Confidence in percent associated to the peptide.", false),
    decoy("Decoy", "Indicates whether the peptide is a decoy (1: yes, 0: no).", false),
    validated("Validation", "Indicates the validation level of the protein group.", false),
    starred("Starred", "Indicates whether the match was starred in the interface (1: yes, 0: no).", false),
    hidden("Hidden", "Indicates whether the match was hidden in the interface (1: yes, 0: no).", false);
    /**
     * The title of the feature which will be used for column heading.
     */
    public String title;
    /**
     * The description of the feature.
     */
    public String description;
    /**
     * The type of export feature.
     */
    public final static String type = "Peptide Identification Summary";
    /**
     * indicates whether a feature is for advanced user only
     */
    private boolean advanced;

    /**
     * Constructor.
     *
     * @param title title of the feature
     * @param description description of the feature
     * @param advanced indicates whether a feature is for advanced user only
     */
    private PeptideFeatures(String title, String description, boolean advanced) {
        this.title = title;
        this.description = description;
        this.advanced = advanced;
    }

    @Override
    public ArrayList<ExportFeature> getExportFeatures(boolean includeSubFeatures) {
        ArrayList<ExportFeature> result = new ArrayList<ExportFeature>();
        result.addAll(Arrays.asList(values()));
        if (includeSubFeatures) {
        result.addAll(PsmFeatures.values()[0].getExportFeatures(includeSubFeatures));
        }
        return result;
    }

    @Override
    public String[] getTitles() {
        return new String[]{title};
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getFeatureFamily() {
        return type;
    }

    @Override
    public boolean isAdvanced() {
        return advanced;
    }
}
