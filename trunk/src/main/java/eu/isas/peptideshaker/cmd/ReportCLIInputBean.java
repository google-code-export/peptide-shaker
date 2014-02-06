package eu.isas.peptideshaker.cmd;

import com.compomics.software.CommandLineUtils;
import eu.isas.peptideshaker.export.PSExportFactory;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;

/**
 * This class is a simple bean wrapping the CLI parameters provided in an
 * Options instance.
 *
 * @author Marc Vaudel
 */
public class ReportCLIInputBean {

    /**
     * The cps file to work on.
     */
    private File cpsFile = null;
    /**
     * Folder where to export the reports.
     */
    private File outputFolder = null;
    /**
     * The report types required by the user.
     */
    private ArrayList<String> reportTypes = new ArrayList<String>();
    /**
     * The documentation types required by the user.
     */
    private ArrayList<String> documentationTypes = new ArrayList<String>();

    /**
     * Construct a FollowUpCLIInputBean from an Apache CLI instance.
     *
     * @param aLine the command line
     */
    public ReportCLIInputBean(CommandLine aLine) {

        if (aLine.hasOption(ReportCLIParams.CPS_FILE.id)) {
            cpsFile = new File(aLine.getOptionValue(ReportCLIParams.CPS_FILE.id));
        }
        if (aLine.hasOption(ReportCLIParams.EXPORT_FOLDER.id)) {
            outputFolder = new File(aLine.getOptionValue(ReportCLIParams.EXPORT_FOLDER.id));
        }
        if (aLine.hasOption(ReportCLIParams.REPORT_TYPE.id)) {
            ArrayList<Integer> options = CommandLineUtils.getIntegerListFromString(aLine.getOptionValue(ReportCLIParams.REPORT_TYPE.id), ",");
            PSExportFactory exportFactory = PSExportFactory.getInstance();
            for (int option : options) {
                reportTypes.add(exportFactory.getExportTypeFromCommandLineOption(option));
            }
        }
        if (aLine.hasOption(ReportCLIParams.DOCUMENTATION_TYPE.id)) {
            ArrayList<Integer> options = CommandLineUtils.getIntegerListFromString(aLine.getOptionValue(ReportCLIParams.DOCUMENTATION_TYPE.id), ",");
            PSExportFactory exportFactory = PSExportFactory.getInstance();
            for (int option : options) {
                documentationTypes.add(exportFactory.getExportTypeFromCommandLineOption(option));
            }
        }
    }

    /**
     * Returns the cps file from which the information can be obtained.
     *
     * @return the cps file from which the information can be obtained
     */
    public File getCpsFile() {
        return cpsFile;
    }

    /**
     * Returns the output folder.
     *
     * @return the output folder
     */
    public File getOutputFolder() {
        return outputFolder;
    }

    /**
     * Returns the types of output required by the user
     *
     * @return the types of output
     */
    public ArrayList<String> getReportTypes() {
        return reportTypes;
    }

    /**
     * Returns the type of documentation required by the user.
     *
     * @return the type of documentation types
     */
    public ArrayList<String> getDocumentationTypes() {
        return documentationTypes;
    }

    /**
     * Indicates whether a report export is needed.
     *
     * @return true if a report export is needed
     */
    public boolean exportNeeded() {
        return reportExportNeeded() || documentationExportNeeded();
    }

    /**
     * Indicates whether a report export is required by the user.
     *
     * @return true if a report export is required
     */
    public boolean reportExportNeeded() {
        return outputFolder != null && !reportTypes.isEmpty();
    }

    /**
     * Indicates whether a documentation export is required by the user.
     *
     * @return true if documentation export is require
     */
    public boolean documentationExportNeeded() {
        return outputFolder != null && !documentationTypes.isEmpty();
    }
}