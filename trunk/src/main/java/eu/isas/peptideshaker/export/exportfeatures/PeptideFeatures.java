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

    accessions("Protein(s)", "Protein(s) to which this peptide can be attached."),
    protein_description("Description(s)", "Description of the Protein(s) to which this peptide can be attached."),
    unique("Unique", "Indicates whether the peptide is found uniquely in the protein match of interest."),
    pi("PI", "The protein inference status of this peptide."),
    sequence("Sequence", "Sequence of the peptide."),
    missed_cleavages("Missed Cleavages", "The number of missed cleavages."),
    modified_sequence("Modified Sequence", "The peptide sequence annotated with variable modifications."),
    position("Position", "Position of the peptide in the protein sequence."),
    aaBefore("AAs Before", "The amino-acids before the sequence."),
    aaAfter("AAs After", "The amino-acids after the sequence."),
    variable_ptms("Variable Modifications", "The variable modifications."),
    fixed_ptms("Fixed Modifications", "The fixed modifications."),
    localization_confidence("Localization Confidence", "The confidence in PTMs localization."),
    confident_phosphosites("Confident Phosphosites", "The confidently localized phosphorylation sites."),
    other_phosphosites("Other Phosphosites", "The other phosphorylation sites."),
    validated_psms("#Validated PSMs", "Number of validated PSMs."),
    psms("#PSMs", "Number of PSMs."),
    score("Score", "Score of the peptide."),
    confidence("Confidence", "Confidence in percent associated to the peptide."),
    decoy("Decoy", "Indicates whether the peptide is a decoy (1: yes, 0: no)."),
    validated("Validation", "Indicates the validation level of the protein group."),
    starred("Starred", "Indicates whether the match was starred in the interface (1: yes, 0: no)."),
    hidden("Hidden", "Indicates whether the match was hidden in the interface (1: yes, 0: no).");
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
     * Constructor.
     *
     * @param title title of the feature
     * @param description description of the feature
     */
    private PeptideFeatures(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public ArrayList<ExportFeature> getExportFeatures() {
        ArrayList<ExportFeature> result = new ArrayList<ExportFeature>();
        result.addAll(Arrays.asList(values()));
        result.addAll(PsmFeatures.values()[0].getExportFeatures());
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
}
