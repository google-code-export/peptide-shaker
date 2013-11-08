package eu.isas.peptideshaker.gui.pride;

import com.compomics.util.examples.BareBonesBrowserLaunch;
import com.compomics.util.experiment.identification.SearchParameters;
import com.compomics.util.experiment.identification.SequenceFactory;
import com.compomics.util.gui.JOptionEditorPane;
import com.compomics.util.gui.waiting.waitinghandlers.ProgressDialogX;
import com.compomics.util.preferences.UtilitiesUserPreferences;
import eu.isas.peptideshaker.gui.PeptideShakerGUI;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * Dialog to help the user select the correct database.
 *
 * @author Harald Barsnes
 */
public class DatabaseHelpDialog extends javax.swing.JDialog {

    /**
     * The PeptideShakerGUI main frame.
     */
    private PeptideShakerGUI peptideShakerGUI;
    /**
     * The current species, can be null.
     */
    private String species;
    /**
     * The current taxonomy, can be null.
     */
    private String taxonomy;
    /**
     * A simple progress dialog.
     */
    private static ProgressDialogX progressDialog;
    /**
     * The sequence factory.
     */
    private SequenceFactory sequenceFactory = SequenceFactory.getInstance();
    /**
     * The search parameters.
     */
    private SearchParameters searchParameters;

    /**
     * Creates a new DatabaseHelpDialog.
     *
     * @param peptideShakerGUI
     * @param searchParameters
     * @param modal
     * @param species the current species
     * @param taxonomy the current taxonomy
     */
    public DatabaseHelpDialog(PeptideShakerGUI peptideShakerGUI, SearchParameters searchParameters, boolean modal, String species, String taxonomy) {
        super(peptideShakerGUI, modal);
        initComponents();
        this.species = species;
        this.taxonomy = taxonomy;
        this.searchParameters = searchParameters;
        this.peptideShakerGUI = peptideShakerGUI;

        boolean speciesOrTaxonomySet = false;

        if (species != null && species.length() > 0) {
            speciesJTextField.setText(species);
            speciesOrTaxonomySet = true;
        } else {
            speciesJTextField.setText("(unknown)");
            this.species = null;
        }

        if (taxonomy != null && taxonomy.length() > 0) {
            taxonomyJTextField.setText(taxonomy);
            speciesOrTaxonomySet = true;
        } else {
            taxonomyJTextField.setText("(unknown)");
            this.taxonomy = null;
        }

        if (!speciesOrTaxonomySet) {
            downloadUniProtJLabel.setEnabled(false);
        }

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

        backgroundPanel = new javax.swing.JPanel();
        speciesAndTaxonomyJPanel = new javax.swing.JPanel();
        speciesLabel = new javax.swing.JLabel();
        taxonomyLabel = new javax.swing.JLabel();
        speciesJTextField = new javax.swing.JTextField();
        taxonomyJTextField = new javax.swing.JTextField();
        downloadUniProtJLabel = new javax.swing.JLabel();
        dataBasePanelSettings = new javax.swing.JPanel();
        databaseSettingsLbl = new javax.swing.JLabel();
        databaseSettingsTxt = new javax.swing.JTextField();
        browseDatabaseSettings = new javax.swing.JButton();
        targetDecoySettingsButton = new javax.swing.JButton();
        databaseHelpSettingsJLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Protein Sequence Database");
        setMinimumSize(new java.awt.Dimension(600, 270));

        backgroundPanel.setBackground(new java.awt.Color(230, 230, 230));

        speciesAndTaxonomyJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Species & Taxonomy"));
        speciesAndTaxonomyJPanel.setOpaque(false);

        speciesLabel.setText("Species");

        taxonomyLabel.setText("Taxonomy");

        speciesJTextField.setEditable(false);
        speciesJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        taxonomyJTextField.setEditable(false);
        taxonomyJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        downloadUniProtJLabel.setForeground(new java.awt.Color(0, 0, 255));
        downloadUniProtJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        downloadUniProtJLabel.setText("<html><u><i>Click here to download species database from UniProt.</i></u></html>");
        downloadUniProtJLabel.setToolTipText("Download UniProt Database");
        downloadUniProtJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downloadUniProtJLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                downloadUniProtJLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                downloadUniProtJLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout speciesAndTaxonomyJPanelLayout = new javax.swing.GroupLayout(speciesAndTaxonomyJPanel);
        speciesAndTaxonomyJPanel.setLayout(speciesAndTaxonomyJPanelLayout);
        speciesAndTaxonomyJPanelLayout.setHorizontalGroup(
            speciesAndTaxonomyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speciesAndTaxonomyJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(speciesAndTaxonomyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speciesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxonomyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(speciesAndTaxonomyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taxonomyJTextField)
                    .addComponent(speciesJTextField)
                    .addGroup(speciesAndTaxonomyJPanelLayout.createSequentialGroup()
                        .addComponent(downloadUniProtJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        speciesAndTaxonomyJPanelLayout.setVerticalGroup(
            speciesAndTaxonomyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speciesAndTaxonomyJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(speciesAndTaxonomyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speciesLabel)
                    .addComponent(speciesJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(speciesAndTaxonomyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taxonomyLabel)
                    .addComponent(taxonomyJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downloadUniProtJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dataBasePanelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Database Selection"));
        dataBasePanelSettings.setOpaque(false);

        databaseSettingsLbl.setText("Database");

        databaseSettingsTxt.setEditable(false);

        browseDatabaseSettings.setText("Browse");
        browseDatabaseSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDatabaseSettingsActionPerformed(evt);
            }
        });

        targetDecoySettingsButton.setText("Decoy");
        targetDecoySettingsButton.setToolTipText("Generate a concatenated Target/Decoy database");
        targetDecoySettingsButton.setEnabled(false);
        targetDecoySettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetDecoySettingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataBasePanelSettingsLayout = new javax.swing.GroupLayout(dataBasePanelSettings);
        dataBasePanelSettings.setLayout(dataBasePanelSettingsLayout);
        dataBasePanelSettingsLayout.setHorizontalGroup(
            dataBasePanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBasePanelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(databaseSettingsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(databaseSettingsTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(browseDatabaseSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(targetDecoySettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dataBasePanelSettingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {browseDatabaseSettings, targetDecoySettingsButton});

        dataBasePanelSettingsLayout.setVerticalGroup(
            dataBasePanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataBasePanelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataBasePanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseSettingsLbl)
                    .addComponent(browseDatabaseSettings)
                    .addComponent(databaseSettingsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(targetDecoySettingsButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        databaseHelpSettingsJLabel.setForeground(new java.awt.Color(0, 0, 255));
        databaseHelpSettingsJLabel.setText("<html><u><i>Click here for help on setting up the database.</i></u></html>");
        databaseHelpSettingsJLabel.setToolTipText("Open Database Help");
        databaseHelpSettingsJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                databaseHelpSettingsJLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                databaseHelpSettingsJLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                databaseHelpSettingsJLabelMouseExited(evt);
            }
        });

        okButton.setText("OK");
        okButton.setEnabled(false);
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

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(databaseHelpSettingsJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(speciesAndTaxonomyJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataBasePanelSettings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        backgroundPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(speciesAndTaxonomyJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataBasePanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(databaseHelpSettingsJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Opens a file chooser where the user can select the database file.
     *
     * @param evt
     */
    private void browseDatabaseSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseDatabaseSettingsActionPerformed

        File startLocation = new File(peptideShakerGUI.getLastSelectedFolder());
        UtilitiesUserPreferences utilitiesUserPreferences = UtilitiesUserPreferences.loadUserPreferences();
        if (utilitiesUserPreferences.getDbFolder() != null && utilitiesUserPreferences.getDbFolder().exists()) {
            startLocation = utilitiesUserPreferences.getDbFolder();
        }

        // First check whether a file has already been selected.
        // If so, start from that file's parent.
        if (databaseSettingsTxt.getText() != null && new File(databaseSettingsTxt.getText()).exists()) {
            File temp = new File(databaseSettingsTxt.getText());
            startLocation = temp.getParentFile();
        }

        JFileChooser fc = new JFileChooser(startLocation);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File myFile) {
                return myFile.getName().toLowerCase().endsWith("fasta")
                        || myFile.getName().toLowerCase().endsWith("fast")
                        || myFile.getName().toLowerCase().endsWith("fas")
                        || myFile.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Supported formats: FASTA (.fasta)";
            }
        };

        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            if (file.getName().indexOf(" ") != -1) {
                renameFastaFileName(file);
            } else {
                databaseSettingsTxt.setText(file.getAbsolutePath());
                databaseSettingsTxt.setText(file.getAbsolutePath());
            }

            peptideShakerGUI.setLastSelectedFolder(file.getAbsolutePath());
            targetDecoySettingsButton.setEnabled(true);

            // check if the database contains decoys
            if (!file.getAbsolutePath().endsWith(SequenceFactory.getTargetDecoyFileNameTag())) {

                int value = JOptionPane.showConfirmDialog(this,
                        "The selected FASTA file does not seem to contain decoy sequences.\n"
                        + "Decoys are required by PeptideShaker. Add decoys?", "Add Decoy Sequences?", JOptionPane.YES_NO_OPTION);

                if (value == JOptionPane.NO_OPTION) {
                    // do nothing
                } else if (value == JOptionPane.YES_OPTION) {
                    targetDecoySettingsButtonActionPerformed(null);
                }
            }

            validateParametersInput(false);
        }
    }//GEN-LAST:event_browseDatabaseSettingsActionPerformed

    /**
     * Open the database help web page.
     *
     * @param evt
     */
    private void databaseHelpSettingsJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseHelpSettingsJLabelMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        BareBonesBrowserLaunch.openURL("http://code.google.com/p/searchgui/wiki/DatabaseHelp");
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_databaseHelpSettingsJLabelMouseClicked

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void databaseHelpSettingsJLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseHelpSettingsJLabelMouseEntered
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_databaseHelpSettingsJLabelMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void databaseHelpSettingsJLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseHelpSettingsJLabelMouseExited
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_databaseHelpSettingsJLabelMouseExited

    /**
     * Open the UniProt download page for the given species.
     *
     * @param evt
     */
    private void downloadUniProtJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadUniProtJLabelMouseClicked
        if (downloadUniProtJLabel.isEnabled()) {

            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

            if (taxonomy != null) {
                BareBonesBrowserLaunch.openURL("http://www.uniprot.org/uniprot/?query=taxonomy%3A" + taxonomy + "&sort=score");
            } else {
                BareBonesBrowserLaunch.openURL("http://www.uniprot.org/uniprot/?query=%28organism%3A%22" + species + "%22%29&sort=score");
            }

            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_downloadUniProtJLabelMouseClicked

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void downloadUniProtJLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadUniProtJLabelMouseEntered
        if (downloadUniProtJLabel.isEnabled()) {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_downloadUniProtJLabelMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void downloadUniProtJLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadUniProtJLabelMouseExited
        if (downloadUniProtJLabel.isEnabled()) {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_downloadUniProtJLabelMouseExited

    /**
     * Save the selected database and close the dialog.
     *
     * @param evt
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (databaseSettingsTxt.getText().length() > 0) {
            searchParameters.setFastaFile(new File(databaseSettingsTxt.getText()));
        }
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Close the dialog without saving.
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Generates a target-decoy database.
     *
     * @param evt
     */
    private void targetDecoySettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetDecoySettingsButtonActionPerformed
        generateTargetDecoyDatabase();
    }//GEN-LAST:event_targetDecoySettingsButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton browseDatabaseSettings;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel dataBasePanelSettings;
    private javax.swing.JLabel databaseHelpSettingsJLabel;
    private javax.swing.JLabel databaseSettingsLbl;
    private javax.swing.JTextField databaseSettingsTxt;
    private javax.swing.JLabel downloadUniProtJLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel speciesAndTaxonomyJPanel;
    private javax.swing.JTextField speciesJTextField;
    private javax.swing.JLabel speciesLabel;
    private javax.swing.JButton targetDecoySettingsButton;
    private javax.swing.JTextField taxonomyJTextField;
    private javax.swing.JLabel taxonomyLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Copies the content of the FASTA file to a new file and replaces any white
     * space in the file name with '_' instead.
     *
     * @param file
     */
    public void renameFastaFileName(File file) {

        // @TODO: this method should be merged with the identical method in SearchGUI...
        String tempName = file.getName();
        tempName = tempName.replaceAll(" ", "_");

        File renamedFile = new File(file.getParentFile().getAbsolutePath() + File.separator + tempName);

        boolean success = false;

        try {
            success = renamedFile.createNewFile();

            if (success) {

                FileReader r = new FileReader(file);
                BufferedReader br = new BufferedReader(r);

                FileWriter w = new FileWriter(renamedFile);
                BufferedWriter bw = new BufferedWriter(w);

                String line = br.readLine();

                while (line != null) {
                    bw.write(line + "\n");
                    line = br.readLine();
                }

                bw.close();
                w.close();
                br.close();
                r.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (success) {
            JOptionPane.showMessageDialog(this, "Your FASTA file name contained white space and has been renamed to:\n"
                    + file.getParentFile().getAbsolutePath() + File.separator + tempName, "Renamed File", JOptionPane.WARNING_MESSAGE);
            databaseSettingsTxt.setText(file.getParentFile().getAbsolutePath() + File.separator + tempName);
            targetDecoySettingsButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Your FASTA file name contains white space and has to been renamed.",
                    "Please Rename File", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Adds a decoy database to the current FASTA file.
     */
    public void generateTargetDecoyDatabase() {

        // @TODO: this method should be merged with the identical method in SearchGUI...
        progressDialog = new ProgressDialogX(this, peptideShakerGUI,
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/peptide-shaker.gif")),
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/peptide-shaker-orange.gif")),
                true);
        progressDialog.setPrimaryProgressCounterIndeterminate(true);
        progressDialog.setTitle("Creating Decoy. Please Wait...");

        final DatabaseHelpDialog finalRef = this;

        new Thread(new Runnable() {
            public void run() {
                try {
                    progressDialog.setVisible(true);
                } catch (IndexOutOfBoundsException e) {
                    // ignore
                }
            }
        }, "ProgressDialog").start();

        new Thread("DecoyThread") {
            public void run() {

                String fastaInput = databaseSettingsTxt.getText().trim();
                try {
                    progressDialog.setTitle("Importing Database. Please Wait...");
                    progressDialog.setPrimaryProgressCounterIndeterminate(false);
                    sequenceFactory.loadFastaFile(new File(fastaInput), progressDialog);
                } catch (IOException e) {
                    progressDialog.setRunFinished();
                    JOptionPane.showMessageDialog(finalRef,
                            "File " + fastaInput + " not found.",
                            "FASTA Import Error", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                    return;
                } catch (ClassNotFoundException e) {
                    progressDialog.setRunFinished();
                    JOptionPane.showMessageDialog(finalRef, JOptionEditorPane.getJOptionEditorPane("File index of " + fastaInput + " could not be imported.<br>"
                            + "Please <a href=\"http://code.google.com/p/peptide-shaker/issues/list\">contact the developers</a>."),
                            "FASTA Import Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    return;
                } catch (StringIndexOutOfBoundsException e) {
                    progressDialog.setRunFinished();
                    JOptionPane.showMessageDialog(finalRef,
                            e.getMessage(),
                            "FASTA Import Error", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                    return;
                } catch (IllegalArgumentException e) {
                    progressDialog.setRunFinished();
                    JOptionPane.showMessageDialog(finalRef,
                            e.getMessage(),
                            "FASTA Import Error", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                    return;
                }

                if (sequenceFactory.concatenatedTargetDecoy() && !progressDialog.isRunCanceled()) {
                    progressDialog.setRunFinished();
                    JOptionPane.showMessageDialog(finalRef,
                            "The database already contains decoy sequences.",
                            "FASTA File Already Decoy!", JOptionPane.WARNING_MESSAGE);
                    targetDecoySettingsButton.setEnabled(false);
                    return;
                }

                if (!progressDialog.isRunCanceled()) {

                    try {
                        String newFasta = fastaInput.substring(0, fastaInput.lastIndexOf("."));
                        newFasta += SequenceFactory.getTargetDecoyFileNameTag();
                        progressDialog.setTitle("Appending Decoy Sequences. Please Wait...");
                        sequenceFactory.appendDecoySequences(new File(newFasta), progressDialog);
                        databaseSettingsTxt.setText(newFasta);
                        targetDecoySettingsButton.setEnabled(false);
                    } catch (IllegalArgumentException e) {
                        progressDialog.setRunFinished();
                        JOptionPane.showMessageDialog(finalRef,
                                new String[]{"FASTA File Error.", fastaInput + " already contains decoy sequences."},
                                "FASTA File Error", JOptionPane.WARNING_MESSAGE);
                        targetDecoySettingsButton.setEnabled(false);
                        e.printStackTrace();
                        return;
                    } catch (OutOfMemoryError error) {
                        Runtime.getRuntime().gc();
                        progressDialog.setRunFinished();
                        JOptionPane.showMessageDialog(finalRef,
                                "PeptideShaker used up all the available memory and had to be stopped.\n"
                                + "Memory boundaries are changed in the the Welcome Dialog (Settings\n"
                                + "& Help > Settings > Java Memory Settings) or in the Edit menu (Edit\n"
                                + "Java Options).\n\n"
                                + "More help can be found at our website http://peptide-shaker.googlecode.com.",
                                "Out Of Memory Error",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println("Ran out of memory!");
                        error.printStackTrace();
                        return;
                    } catch (IOException e) {
                        progressDialog.setRunFinished();
                        JOptionPane.showMessageDialog(finalRef,
                                new String[]{"FASTA Import Error.", "File " + fastaInput + " not found."},
                                "FASTA Import Error", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                        return;
                    } catch (InterruptedException e) {
                        progressDialog.setRunFinished();
                        JOptionPane.showMessageDialog(finalRef,
                                new String[]{"FASTA Import Error.", "File " + fastaInput + " could not be imported."},
                                "FASTA Import Error", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                        return;
                    } catch (ClassNotFoundException e) {
                        progressDialog.setRunFinished();
                        JOptionPane.showMessageDialog(finalRef,
                                new String[]{"FASTA Import Error.", "File " + fastaInput + " could not be imported."},
                                "FASTA Import Error", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                        return;
                    }
                }

                if (!progressDialog.isRunCanceled()) {
                    progressDialog.setRunFinished();
                    targetDecoySettingsButton.setEnabled(false);
                    JOptionPane.showMessageDialog(finalRef, "Concatenated decoy database created and selected.", "Decoy Created", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    progressDialog.setRunFinished();
                }
            }
        }.start();
    }

    /**
     * Inspects the parameters validity.
     *
     * @param showMessage if true an error messages are shown to the users
     * @return a boolean indicating if the parameters are valid
     */
    public boolean validateParametersInput(boolean showMessage) {

        boolean valid = true;
        databaseSettingsLbl.setForeground(Color.BLACK);

        databaseSettingsLbl.setToolTipText(null);

        if (databaseSettingsTxt.getText() == null || databaseSettingsTxt.getText().trim().equals("")) {
            if (showMessage && valid) {
                JOptionPane.showMessageDialog(this, "You need to specify a search database.", "Search Database Not Found", JOptionPane.WARNING_MESSAGE);
            }
            databaseSettingsLbl.setForeground(Color.RED);
            databaseSettingsLbl.setToolTipText("Please select a valid '.fasta' or '.fas' database file");
            valid = false;
        } else {
            File test = new File(databaseSettingsTxt.getText().trim());
            if (!test.exists()) {
                if (showMessage && valid) {
                    JOptionPane.showMessageDialog(this, "The database file could not be found.", "Search Database Not Found", JOptionPane.WARNING_MESSAGE);
                }
                databaseSettingsLbl.setForeground(Color.RED);
                databaseSettingsLbl.setToolTipText("Database file could not be found!");
                valid = false;
            }
        }

        okButton.setEnabled(valid);

        return valid;
    }
}
