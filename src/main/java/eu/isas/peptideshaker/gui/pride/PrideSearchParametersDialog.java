package eu.isas.peptideshaker.gui.pride;

import com.compomics.software.ToolFactory;
import com.compomics.software.dialogs.SearchGuiSetupDialog;
import com.compomics.util.preferences.UtilitiesUserPreferences;
import eu.isas.peptideshaker.gui.PeptideShakerGUI;
import java.io.File;
import java.util.ArrayList;

/**
 * Display the extracted search settings to the user.
 *
 * @author Harald Barsnes
 */
public class PrideSearchParametersDialog extends javax.swing.JDialog {

    /**
     * Reference to the main frame.
     */
    private PeptideShakerGUI peptideShakerGUI;
    /**
     * The extracted search parameters file.
     */
    private File prideSearchParametersFile;
    /**
     * The mgf files.
     */
    private ArrayList<File> mgfFiles;
    /**
     * The species for the PRIDE project.
     */
    private String species;
    /**
     * The species type for the PRIDE project.
     */
    private String speciesType;

    /**
     * Creates a new PrideSearchParametersDialog
     *
     * @param peptideShakerGUI a reference to the main frame
     * @param prideSearchParametersFile the pride search parameters file
     * @param prideSearchParametersReport the pride search parameters report
     * @param mgfFiles the converted mgf files
     * @param species the species for the project, can be null and also a list
     * of species
     * @param speciesType the species type
     * @param modal
     */
    public PrideSearchParametersDialog(PeptideShakerGUI peptideShakerGUI, File prideSearchParametersFile, 
            String prideSearchParametersReport, ArrayList<File> mgfFiles, String species, String speciesType, boolean modal) {
        super(peptideShakerGUI, modal);
        initComponents();
        this.peptideShakerGUI = peptideShakerGUI;
        this.prideSearchParametersFile = prideSearchParametersFile;
        this.mgfFiles = mgfFiles;
        this.species = species;
        this.speciesType = speciesType;
        searchParametersReportEditorPane.setText(prideSearchParametersReport);
        setLocationRelativeTo(peptideShakerGUI);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchParametersReportScrollPane = new javax.swing.JScrollPane();
        searchParametersReportEditorPane = new javax.swing.JEditorPane();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRIDE Search Parameters");

        searchParametersReportEditorPane.setEditable(false);
        searchParametersReportEditorPane.setContentType("text/html"); // NOI18N
        searchParametersReportScrollPane.setViewportView(searchParametersReportEditorPane);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        infoLabel.setFont(infoLabel.getFont().deriveFont((infoLabel.getFont().getStyle() | java.awt.Font.ITALIC)));
        infoLabel.setText("Click OK to open the parameters in SearchGUI and re-analyze the data.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchParametersReportScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(infoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchParametersReportScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(infoLabel))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Close the dialog.
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Open SearchGUI with the given search parameters.
     *
     * @param evt
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.setVisible(false);
        peptideShakerGUI.setVisible(false);

        boolean openSearchGUI = true;

        // check if we have a valid SearchGUI location.
        try {
            UtilitiesUserPreferences utilitiesUserPreferences = UtilitiesUserPreferences.loadUserPreferences();
            if (utilitiesUserPreferences.getSearchGuiPath() == null || !(new File(utilitiesUserPreferences.getSearchGuiPath()).exists())) {
                SearchGuiSetupDialog searchGuiSetupDialog = new SearchGuiSetupDialog(peptideShakerGUI, true);
                openSearchGUI = !searchGuiSetupDialog.isDialogCanceled();
            }
        } catch (Exception e) {
            peptideShakerGUI.catchException(e);
        }

        if (openSearchGUI) {

            new Thread(new Runnable() {
                public void run() {
                    try {
                        File outputFolder = null;
                        if (!mgfFiles.isEmpty()) {
                            outputFolder = new File(mgfFiles.get(0).getParentFile(), "ps_results");
                            if (!outputFolder.exists()) {
                                boolean success = outputFolder.mkdir();
                                if (!success) {
                                    outputFolder = null;
                                }
                            }
                        }
                        ToolFactory.startSearchGUI(peptideShakerGUI, mgfFiles, prideSearchParametersFile, outputFolder, species, speciesType);
                        peptideShakerGUI.close();
                    } catch (Exception e) {
                        peptideShakerGUI.catchException(e);
                    }
                }
            }, "StartSearchGUI").start();
        }
    }//GEN-LAST:event_okButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JEditorPane searchParametersReportEditorPane;
    private javax.swing.JScrollPane searchParametersReportScrollPane;
    // End of variables declaration//GEN-END:variables
}
