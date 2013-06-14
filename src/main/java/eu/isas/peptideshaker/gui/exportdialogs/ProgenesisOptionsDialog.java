package eu.isas.peptideshaker.gui.exportdialogs;

import com.compomics.util.experiment.identification.Identification;
import com.compomics.util.experiment.identification.SearchParameters;
import com.compomics.util.experiment.identification.matches.ModificationMatch;
import com.compomics.util.experiment.identification.matches.PeptideMatch;
import com.compomics.util.experiment.identification.matches.ProteinMatch;
import com.compomics.util.experiment.identification.matches.SpectrumMatch;
import eu.isas.peptideshaker.myparameters.PSParameter;
import eu.isas.peptideshaker.preferences.FilterPreferences;
import eu.isas.peptideshaker.utils.IdentificationFeaturesGenerator;
import java.awt.Frame;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This dialog allows the user to choose between the different Progenesis export
 * options
 *
 * @author Marc Vaudel
 * @author Harald Barsnes
 */
public class ProgenesisOptionsDialog extends javax.swing.JDialog {

    /**
     * Boolean indicating whether the user canceled the process.
     */
    private boolean canceled = false;
    /**
     * The identification of the project.
     */
    private Identification identification;
    /**
     * The identification features generator.
     */
    private IdentificationFeaturesGenerator identificationFeaturesGenerator;
    /**
     * The filter preferences.
     */
    private FilterPreferences filterPreferences;
    /**
     * The parent frame.
     */
    private Frame parent;
    /**
     * The search parameters.
     */
    private SearchParameters searchParameters;

    /**
     * Creates a new ProgenesisOptionsDialog.
     * 
     * @param parent
     * @param identification
     * @param identificationFeaturesGenerator
     * @param filterPreferences
     * @param searchParameters 
     */
    public ProgenesisOptionsDialog(java.awt.Frame parent, Identification identification, IdentificationFeaturesGenerator identificationFeaturesGenerator, 
            FilterPreferences filterPreferences, SearchParameters searchParameters) {
        super(parent, true);
        this.identification = identification;
        this.identificationFeaturesGenerator = identificationFeaturesGenerator;
        this.filterPreferences = filterPreferences;
        this.parent = parent;
        this.searchParameters = searchParameters;
        initComponents();
        this.setLocationRelativeTo(parent);
        setVisible(true);
    }

    /**
     * Returns the index selected by the user
     * @return 
     */
    public int getUserSelection() {
        return psmSelectionComboBox.getSelectedIndex();
    }
    
    /**
     * Indicates whether the used canceled
     * @return 
     */
    public boolean canceled() {
        return canceled;
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
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        selectionPanel = new javax.swing.JPanel();
        psmSelectionComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Progenesis Export");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        backgroundPanel.setBackground(new java.awt.Color(230, 230, 230));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        selectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("PSMs Selection"));
        selectionPanel.setOpaque(false);

        psmSelectionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<html>&emsp;Protein - <i>validated PSMs of the validated peptides of the validated proteins</i></html>", "<html>&emsp;Peptide - <i>validated PSMs of the validated peptides</i></html>", "<html>&emsp;PSM - <i>validated PSMs</i></html>", "<html>&emsp;Confidently Localized PTMs - <i>validated PSMs containing a confidently localized PTM</i></html>" }));

        javax.swing.GroupLayout selectionPanelLayout = new javax.swing.GroupLayout(selectionPanel);
        selectionPanel.setLayout(selectionPanelLayout);
        selectionPanelLayout.setHorizontalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(psmSelectionComboBox, 0, 581, Short.MAX_VALUE)
                .addContainerGap())
        );
        selectionPanelLayout.setVerticalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(psmSelectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(selectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        backgroundPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
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
     * Close the dialog without saving.
     * 
     * @param evt 
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        canceled = true;
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Save the setting and then close the dialog.
     * 
     * @param evt 
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Close the dialog without saving.
     * 
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        canceled = true;
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox psmSelectionComboBox;
    private javax.swing.JPanel selectionPanel;
    // End of variables declaration//GEN-END:variables
}
