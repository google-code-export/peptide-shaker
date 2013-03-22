package eu.isas.peptideshaker.gui;

import com.compomics.software.ToolFactory;
import com.compomics.software.dialogs.JavaOptionsDialog;
import com.compomics.software.dialogs.SearchGuiSetupDialog;
import com.compomics.util.examples.BareBonesBrowserLaunch;
import com.compomics.util.gui.error_handlers.BugReport;
import com.compomics.util.gui.error_handlers.HelpDialog;
import com.compomics.util.preferences.UtilitiesUserPreferences;
import eu.isas.peptideshaker.gui.gettingStarted.GettingStartedDialog;
import eu.isas.peptideshaker.gui.pride.PrideReshakeGui;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.*;

/**
 * A simple welcome dialog with the option to open an existing project or create
 * a new one.
 *
 * @author Harald Barsnes
 * @author Marc Vaudel
 */
public class WelcomeDialog extends javax.swing.JDialog {

    /**
     * The PeptideShaker parent frame.
     */
    private PeptideShakerGUI peptideShakerGUI;
    /**
     * A reference to the open dialog.
     */
    private NewDialog openDialog;
    /**
     * A dummy parent frame to be able to show an icon in the task bar.
     */
    static private DummyFrame dummyParentFrame = new DummyFrame("");

    /**
     * Create a new WelcomeDialog.
     *
     * @param peptideShakerGUI the dialog parent
     * @param modal modal or not modal
     */
    public WelcomeDialog(PeptideShakerGUI peptideShakerGUI, boolean modal) {
        super(dummyParentFrame.setNewTitle(peptideShakerGUI.getTitle()), modal);
        this.peptideShakerGUI = peptideShakerGUI;
        initComponents();

        openDialog = new NewDialog(peptideShakerGUI, false);
        setLocationRelativeTo(null);
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

        quantifyJButton = new javax.swing.JButton();
        settingsPopupMenu = new javax.swing.JPopupMenu();
        settingsMenu = new javax.swing.JMenu();
        javaSettingsMenuItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        searchGUISettingsMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        gettingStartedMenuItem = new javax.swing.JMenuItem();
        bugReportMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();
        backgroundPanel = new javax.swing.JPanel();
        openJButton = new javax.swing.JButton();
        newJButton = new javax.swing.JButton();
        compomicsButton = new javax.swing.JButton();
        probeButton = new javax.swing.JButton();
        isasButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        searchJButton = new javax.swing.JButton();
        recentProjectsLabel = new javax.swing.JLabel();
        reshakeJButton = new javax.swing.JButton();
        gettingStartedJButton1 = new javax.swing.JButton();
        openExampleDatasetJButton = new javax.swing.JButton();
        settingsLabel = new javax.swing.JLabel();

        quantifyJButton.setFont(quantifyJButton.getFont().deriveFont(quantifyJButton.getFont().getStyle() | java.awt.Font.BOLD, quantifyJButton.getFont().getSize()+3));
        quantifyJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reporter_logo.png"))); // NOI18N
        quantifyJButton.setText("Reporter Ions");
        quantifyJButton.setToolTipText("<html>\nQuantify your proteins using reporter ions<br>\n(Coming soon...)\n</html>");
        quantifyJButton.setFocusPainted(false);
        quantifyJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quantifyJButton.setIconTextGap(11);
        quantifyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantifyJButtonActionPerformed(evt);
            }
        });

        settingsMenu.setText("Settings");

        javaSettingsMenuItem.setText("Java Memory Settings");
        javaSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javaSettingsMenuItemActionPerformed(evt);
            }
        });
        settingsMenu.add(javaSettingsMenuItem);

        toolsMenu.setText("Tools");

        searchGUISettingsMenuItem.setText("SearchGUI Settings");
        searchGUISettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchGUISettingsMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(searchGUISettingsMenuItem);

        settingsMenu.add(toolsMenu);

        settingsPopupMenu.add(settingsMenu);

        helpMenu.setText("Help");

        gettingStartedMenuItem.setText("Getting Started");
        gettingStartedMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gettingStartedMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(gettingStartedMenuItem);

        bugReportMenuItem.setText("Bug Report");
        bugReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bugReportMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(bugReportMenuItem);
        helpMenu.add(jSeparator2);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        settingsPopupMenu.add(helpMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Welcome to PeptideShaker");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 255));

        openJButton.setFont(openJButton.getFont().deriveFont(openJButton.getFont().getStyle() | java.awt.Font.BOLD, openJButton.getFont().getSize()+3));
        openJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/peptide-shaker-medium-blue-shadow.png"))); // NOI18N
        openJButton.setText("Open Project");
        openJButton.setToolTipText("Open an existing PeptideShaker project");
        openJButton.setFocusPainted(false);
        openJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        openJButton.setIconTextGap(20);
        openJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openJButtonActionPerformed(evt);
            }
        });

        newJButton.setFont(newJButton.getFont().deriveFont(newJButton.getFont().getStyle() | java.awt.Font.BOLD, newJButton.getFont().getSize()+3));
        newJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/peptide-shaker-medium-orange-shadow.png"))); // NOI18N
        newJButton.setText("New Project");
        newJButton.setToolTipText("Create a new PeptideShaker project");
        newJButton.setFocusPainted(false);
        newJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newJButton.setIconTextGap(24);
        newJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newJButtonActionPerformed(evt);
            }
        });

        compomicsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compomics.png"))); // NOI18N
        compomicsButton.setToolTipText("Computational Omics and Systems Biology Group");
        compomicsButton.setBorder(null);
        compomicsButton.setBorderPainted(false);
        compomicsButton.setContentAreaFilled(false);
        compomicsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                compomicsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                compomicsButtonMouseExited(evt);
            }
        });
        compomicsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compomicsButtonActionPerformed(evt);
            }
        });

        probeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/probe.png"))); // NOI18N
        probeButton.setToolTipText("Proteomics Unit at the University of Bergen");
        probeButton.setBorder(null);
        probeButton.setBorderPainted(false);
        probeButton.setContentAreaFilled(false);
        probeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                probeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                probeButtonMouseExited(evt);
            }
        });
        probeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probeButtonActionPerformed(evt);
            }
        });

        isasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/isas.png"))); // NOI18N
        isasButton.setToolTipText("<html>ISAS - Institute for Analytical Science</html>");
        isasButton.setBorder(null);
        isasButton.setBorderPainted(false);
        isasButton.setContentAreaFilled(false);
        isasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isasButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isasButtonMouseExited(evt);
            }
        });
        isasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isasButtonActionPerformed(evt);
            }
        });

        searchJButton.setFont(searchJButton.getFont().deriveFont(searchJButton.getFont().getStyle() | java.awt.Font.BOLD, searchJButton.getFont().getSize()+3));
        searchJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchgui-medium-shadow.png"))); // NOI18N
        searchJButton.setText("Start Search");
        searchJButton.setToolTipText("Start a SearchGUI protein identification search");
        searchJButton.setFocusPainted(false);
        searchJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        searchJButton.setIconTextGap(20);
        searchJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJButtonActionPerformed(evt);
            }
        });

        recentProjectsLabel.setFont(recentProjectsLabel.getFont().deriveFont(recentProjectsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        recentProjectsLabel.setText("<html><a href>Recent Projects</html>");
        recentProjectsLabel.setToolTipText("Open recently opened projects");
        recentProjectsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        recentProjectsLabel.setIconTextGap(-4);
        recentProjectsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recentProjectsLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                recentProjectsLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                recentProjectsLabelMouseExited(evt);
            }
        });

        reshakeJButton.setFont(reshakeJButton.getFont().deriveFont(reshakeJButton.getFont().getStyle() | java.awt.Font.BOLD, reshakeJButton.getFont().getSize()+3));
        reshakeJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/relims_logo.png"))); // NOI18N
        reshakeJButton.setText("Reshake");
        reshakeJButton.setToolTipText("<html>\nReanalyze a PRIDE experiment<br>\n(Coming soon...)\n</html>");
        reshakeJButton.setFocusPainted(false);
        reshakeJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reshakeJButton.setIconTextGap(23);
        reshakeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reshakeJButtonActionPerformed(evt);
            }
        });

        gettingStartedJButton1.setFont(gettingStartedJButton1.getFont().deriveFont(gettingStartedJButton1.getFont().getStyle() | java.awt.Font.BOLD, gettingStartedJButton1.getFont().getSize()+3));
        gettingStartedJButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help-medium-shadow.png"))); // NOI18N
        gettingStartedJButton1.setText("Getting Started");
        gettingStartedJButton1.setToolTipText("Open the Getting Started tutorial");
        gettingStartedJButton1.setFocusPainted(false);
        gettingStartedJButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gettingStartedJButton1.setIconTextGap(20);
        gettingStartedJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gettingStartedJButton1ActionPerformed(evt);
            }
        });

        openExampleDatasetJButton.setFont(openExampleDatasetJButton.getFont().deriveFont(openExampleDatasetJButton.getFont().getStyle() | java.awt.Font.BOLD, openExampleDatasetJButton.getFont().getSize()+3));
        openExampleDatasetJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/peptideshaker_example_dataset.png"))); // NOI18N
        openExampleDatasetJButton.setText("Open Example");
        openExampleDatasetJButton.setToolTipText("Opens a PeptideShaker example dataset");
        openExampleDatasetJButton.setFocusPainted(false);
        openExampleDatasetJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        openExampleDatasetJButton.setIconTextGap(20);
        openExampleDatasetJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openExampleDatasetJButtonActionPerformed(evt);
            }
        });

        settingsLabel.setFont(settingsLabel.getFont().deriveFont(settingsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        settingsLabel.setText("<html><a href>Settings & Help</html>");
        settingsLabel.setToolTipText("Edit the general settings or see the basic help");
        settingsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        settingsLabel.setIconTextGap(-4);
        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(compomicsButton)
                        .addGap(18, 18, 18)
                        .addComponent(probeButton)
                        .addGap(10, 10, 10)
                        .addComponent(isasButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(backgroundPanelLayout.createSequentialGroup()
                                    .addComponent(newJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(openJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(backgroundPanelLayout.createSequentialGroup()
                                    .addComponent(openExampleDatasetJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gettingStartedJButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(backgroundPanelLayout.createSequentialGroup()
                                    .addComponent(searchJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(reshakeJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(recentProjectsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reshakeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gettingStartedJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openExampleDatasetJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recentProjectsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(compomicsButton)
                    .addComponent(probeButton)
                    .addComponent(isasButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        backgroundPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {newJButton, openJButton});

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
     * Open the dialog for creating a new project.
     *
     * @param evt
     */
    private void newJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newJButtonActionPerformed
        this.setVisible(false);
        openDialog.setModal(true);
        openDialog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newJButtonActionPerformed

    /**
     * Open the dialog for opening an existing project.
     *
     * @param evt
     */
    private void openJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openJButtonActionPerformed

        // @TODO: the default folder should be the example dataset folder !!!!

        File newFile = peptideShakerGUI.getUserSelectedFile(".cps", "Supported formats: PeptideShaker (.cps)", "Open PeptideShaker Project", true);

        if (newFile != null) {
            if (!newFile.getName().toLowerCase().endsWith("cps")) {
                JOptionPane.showMessageDialog(this, "Not a PeptideShaker file (.cps).", "Wrong File.", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                peptideShakerGUI.setVisible(true);
                peptideShakerGUI.importPeptideShakerFile(newFile);
                peptideShakerGUI.getUserPreferences().addRecentProject(newFile);
                peptideShakerGUI.updateRecentProjectsList();
                peptideShakerGUI.setLastSelectedFolder(newFile.getAbsolutePath());
                dispose();
            }
        }
    }//GEN-LAST:event_openJButtonActionPerformed

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void compomicsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compomicsButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_compomicsButtonMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void compomicsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compomicsButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_compomicsButtonMouseExited

    /**
     * Open the Compomics web page.
     *
     * @param evt
     */
    private void compomicsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compomicsButtonActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        BareBonesBrowserLaunch.openURL("http://compomics.wordpress.com");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_compomicsButtonActionPerformed

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void probeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_probeButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_probeButtonMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void probeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_probeButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_probeButtonMouseExited

    /**
     * Open the PROBE web page.
     *
     * @param evt
     */
    private void probeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probeButtonActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        BareBonesBrowserLaunch.openURL("http://probe.uib.no");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_probeButtonActionPerformed

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void isasButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_isasButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_isasButtonMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void isasButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_isasButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_isasButtonMouseExited

    /**
     * Open the ISAS web page.
     *
     * @param evt
     */
    private void isasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isasButtonActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        BareBonesBrowserLaunch.openURL("http://www.isas.de");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_isasButtonActionPerformed

    /**
     * Open SearchGUI.
     *
     * @param evt
     */
    private void searchJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJButtonActionPerformed

        // @TODO: the default searchgui folder has to be set!!!

        this.setVisible(false);
        peptideShakerGUI.setVisible(false);

        new Thread(new Runnable() {
            public void run() {
                try {
                    ToolFactory.startSearchGUI(peptideShakerGUI, null, null, null);
                    peptideShakerGUI.close();
                } catch (Exception e) {
                    peptideShakerGUI.catchException(e);
                }
            }
        }, "StartSearchGUI").start();
    }//GEN-LAST:event_searchJButtonActionPerformed

    /**
     * Start Reporter.
     *
     * @param evt
     */
    private void quantifyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantifyJButtonActionPerformed

        JOptionPane.showMessageDialog(this, "In development. Coming soon...", "In Developement...", JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/icons/reporter_logo.png")));

//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    ToolFactory.startReporter(peptideShakerGUI);
//                } catch (Exception e) {
//                    peptideShakerGUI.catchException(e);
//                }
//            }
//        }, "StartReporter").start();
    }//GEN-LAST:event_quantifyJButtonActionPerformed

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void recentProjectsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recentProjectsLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_recentProjectsLabelMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void recentProjectsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recentProjectsLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_recentProjectsLabelMouseExited

    /**
     * Open a recently opened project.
     *
     * @param evt
     */
    private void recentProjectsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recentProjectsLabelMouseClicked
        JPopupMenu popupMenu = new JPopupMenu();
        peptideShakerGUI.loadRecentProjectsList(popupMenu, this);
        popupMenu.show(recentProjectsLabel, evt.getX(), evt.getY());
    }//GEN-LAST:event_recentProjectsLabelMouseClicked

    /**
     * Open the Reshake dialog.
     *
     * @param evt
     */
    private void reshakeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reshakeJButtonActionPerformed
        //JOptionPane.showMessageDialog(this, "In development. Coming soon...", "In Development", JOptionPane.INFORMATION_MESSAGE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        dispose();
        new PrideReshakeGui(peptideShakerGUI, dummyParentFrame, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        peptideShakerGUI.close();
    }//GEN-LAST:event_reshakeJButtonActionPerformed

    /**
     * Open the Getting Started tutorial.
     *
     * @param evt
     */
    private void gettingStartedJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gettingStartedJButton1ActionPerformed
        new GettingStartedDialog(peptideShakerGUI, this, true);
    }//GEN-LAST:event_gettingStartedJButton1ActionPerformed

    /**
     * Open the example dataset.
     *
     * @param evt
     */
    private void openExampleDatasetJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openExampleDatasetJButtonActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setVisible(false);
        peptideShakerGUI.setVisible(true);
        dispose();
        peptideShakerGUI.openExampleFile();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_openExampleDatasetJButtonActionPerformed

    /**
     * The dialog is closing. Close the main PeptideShaker frame.
     *
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        peptideShakerGUI.close();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Open the settings pop up menu.
     *
     * @param evt
     */
    private void settingsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseClicked
        settingsPopupMenu.show(settingsLabel, evt.getX(), evt.getY());
    }//GEN-LAST:event_settingsLabelMouseClicked

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void settingsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_settingsLabelMouseEntered

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void settingsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_settingsLabelMouseExited

    /**
     * Open a SearchGuiSetupDialog were the user can setup the SearchGUI link.
     *
     * @param evt
     */
    private void searchGUISettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchGUISettingsMenuItemActionPerformed
        try {
            new SearchGuiSetupDialog(peptideShakerGUI, true);
            peptideShakerGUI.loadUserPreferences();
        } catch (Exception ex) {
            peptideShakerGUI.catchException(ex);
        }
    }//GEN-LAST:event_searchGUISettingsMenuItemActionPerformed

    /**
     * Open the Java Options menu.
     *
     * @param evt
     */
    private void javaSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javaSettingsMenuItemActionPerformed
        
        // reload the user preferences as these may have been changed by other tools
        try {
            peptideShakerGUI.setUtilitiesUserPreferences(UtilitiesUserPreferences.loadUserPreferences());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured when reading the user preferences.", "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        new JavaOptionsDialog(peptideShakerGUI, peptideShakerGUI, this, "PeptideShaker");
    }//GEN-LAST:event_javaSettingsMenuItemActionPerformed

    /**
     * Open the Getting Started tutorial.
     *
     * @param evt
     */
    private void gettingStartedMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gettingStartedMenuItemActionPerformed
        gettingStartedJButton1ActionPerformed(null);
    }//GEN-LAST:event_gettingStartedMenuItemActionPerformed

    /**
     * Opens a new bug report dialog.
     *
     * @param evt
     */
    private void bugReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bugReportMenuItemActionPerformed
        new BugReport(this, peptideShakerGUI.getLastSelectedFolder(), "PeptideShaker", "peptide-shaker",
                peptideShakerGUI.getVersion(), new File(peptideShakerGUI.getJarFilePath() + "/resources/PeptideShaker.log"));
    }//GEN-LAST:event_bugReportMenuItemActionPerformed

    /**
     * Opens the About dialog.
     *
     * @param evt
     */
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        new HelpDialog(peptideShakerGUI, getClass().getResource("/helpFiles/AboutPeptideShaker.html"),
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/help.GIF")),
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/peptide-shaker.gif")),
                "About PeptideShaker");
    }//GEN-LAST:event_aboutMenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JMenuItem bugReportMenuItem;
    private javax.swing.JButton compomicsButton;
    private javax.swing.JButton gettingStartedJButton1;
    private javax.swing.JMenuItem gettingStartedMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton isasButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem javaSettingsMenuItem;
    private javax.swing.JButton newJButton;
    private javax.swing.JButton openExampleDatasetJButton;
    private javax.swing.JButton openJButton;
    private javax.swing.JButton probeButton;
    private javax.swing.JButton quantifyJButton;
    private javax.swing.JLabel recentProjectsLabel;
    private javax.swing.JButton reshakeJButton;
    private javax.swing.JMenuItem searchGUISettingsMenuItem;
    private javax.swing.JButton searchJButton;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JPopupMenu settingsPopupMenu;
    private javax.swing.JMenu toolsMenu;
    // End of variables declaration//GEN-END:variables

    /**
     * Make sure that the dummy frame is hidden when the dialog is not visible.
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            ((DummyFrame) getParent()).dispose();
        }
    }
}