package eu.isas.peptideshaker.gui;

import eu.isas.peptideshaker.gui.pride.PrideExportDialog;

/**
 * A dialog for displaying the various save/export options.
 *
 * @author Harald Barsnes
 */
public class SaveDialog extends javax.swing.JDialog {

    /**
     * The PeptideShaker parent frame.
     */
    private PeptideShakerGUI peptideShakerGUI;

    /**
     * Create a new SaveDialog.
     *
     * @param peptideShakerGUI the dialog parent
     * @param modal modal or not modal
     */
    public SaveDialog(PeptideShakerGUI peptideShakerGUI, boolean modal) {
        super(peptideShakerGUI, modal);
        this.peptideShakerGUI = peptideShakerGUI;
        initComponents();
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
        saveAsJButton = new javax.swing.JButton();
        exportJButton = new javax.swing.JButton();
        exportPrideJButton = new javax.swing.JButton();
        saveAsJButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setTitle("Save & Export");
        setResizable(false);

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 255));

        saveAsJButton.setFont(saveAsJButton.getFont().deriveFont(saveAsJButton.getFont().getStyle() | java.awt.Font.BOLD, saveAsJButton.getFont().getSize()+3));
        saveAsJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        saveAsJButton.setText("Save Project");
        saveAsJButton.setToolTipText("Save the PeptideShaker project locally.");
        saveAsJButton.setFocusPainted(false);
        saveAsJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        saveAsJButton.setIconTextGap(27);
        saveAsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsJButtonActionPerformed(evt);
            }
        });

        exportJButton.setFont(exportJButton.getFont().deriveFont(exportJButton.getFont().getStyle() | java.awt.Font.BOLD, exportJButton.getFont().getSize()+3));
        exportJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/export.png"))); // NOI18N
        exportJButton.setText("Export Project");
        exportJButton.setToolTipText("<html>\nExport the PeptideShaker project as a<br>\nzip file to open on another computer.\n</html>");
        exportJButton.setFocusPainted(false);
        exportJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exportJButton.setIconTextGap(27);
        exportJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportJButtonActionPerformed(evt);
            }
        });

        exportPrideJButton.setFont(exportPrideJButton.getFont().deriveFont(exportPrideJButton.getFont().getStyle() | java.awt.Font.BOLD, exportPrideJButton.getFont().getSize()+3));
        exportPrideJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/export_pride.png"))); // NOI18N
        exportPrideJButton.setText("Export to PRIDE");
        exportPrideJButton.setToolTipText("Export the PeptideShaker project as PRIDE XML.");
        exportPrideJButton.setFocusPainted(false);
        exportPrideJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exportPrideJButton.setIconTextGap(25);
        exportPrideJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPrideJButtonActionPerformed(evt);
            }
        });

        saveAsJButton1.setFont(saveAsJButton1.getFont().deriveFont(saveAsJButton1.getFont().getStyle() | java.awt.Font.BOLD, saveAsJButton1.getFont().getSize()+3));
        saveAsJButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save_as.png"))); // NOI18N
        saveAsJButton1.setText("Save Project As...");
        saveAsJButton1.setToolTipText("<html>\nSave the PeptideShaker project<br>\nlocally under a different name.\n</html>");
        saveAsJButton1.setFocusPainted(false);
        saveAsJButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        saveAsJButton1.setIconTextGap(27);
        saveAsJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsJButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont((jLabel1.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, jLabel1.getFont().getSize()+1));
        jLabel1.setText("Save the project locally");

        jLabel2.setFont(jLabel2.getFont().deriveFont((jLabel2.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, jLabel2.getFont().getSize()+1));
        jLabel2.setText("Save the project under a new name");

        jLabel3.setFont(jLabel3.getFont().deriveFont((jLabel3.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, jLabel3.getFont().getSize()+1));
        jLabel3.setText("Export the project as a zip file");

        jLabel4.setFont(jLabel4.getFont().deriveFont((jLabel4.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, jLabel4.getFont().getSize()+1));
        jLabel4.setText("Export the project as PRIDE XML");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveAsJButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(saveAsJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportPrideJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveAsJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveAsJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportPrideJButton)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25))
        );

        backgroundPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {exportJButton, exportPrideJButton, saveAsJButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Saves the project.
     *
     * @param evt
     */
    private void saveAsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsJButtonActionPerformed
        this.setVisible(false);
        peptideShakerGUI.saveProject(false);
        this.dispose();
    }//GEN-LAST:event_saveAsJButtonActionPerformed

    /**
     * Export the dataset as a zip file.
     *
     * @param evt
     */
    private void exportJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportJButtonActionPerformed
        this.dispose();
        peptideShakerGUI.exportProjectAsZip();
    }//GEN-LAST:event_exportJButtonActionPerformed

    /**
     * Open the PRIDE Export dialog.
     *
     * @param evt
     */
    private void exportPrideJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPrideJButtonActionPerformed
        dispose();
        new PrideExportDialog(peptideShakerGUI, true);
    }//GEN-LAST:event_exportPrideJButtonActionPerformed

    /**
     * Save the project.
     * 
     * @param evt 
     */
    private void saveAsJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsJButton1ActionPerformed
        this.setVisible(false);
        peptideShakerGUI.saveProjectAs(false);
        this.dispose();
    }//GEN-LAST:event_saveAsJButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton exportJButton;
    private javax.swing.JButton exportPrideJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton saveAsJButton;
    private javax.swing.JButton saveAsJButton1;
    // End of variables declaration//GEN-END:variables
}