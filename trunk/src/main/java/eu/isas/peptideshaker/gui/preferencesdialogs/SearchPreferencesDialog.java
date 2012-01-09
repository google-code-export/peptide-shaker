package eu.isas.peptideshaker.gui.preferencesdialogs;

import com.compomics.util.experiment.biology.EnzymeFactory;
import com.compomics.util.experiment.biology.PTM;
import com.compomics.util.experiment.biology.PTMFactory;
import com.compomics.util.experiment.biology.ions.PeptideFragmentIon.PeptideFragmentIonType;
import com.compomics.util.experiment.io.identifications.IdentificationParametersReader;
import com.compomics.util.gui.dialogs.ProgressDialogX;
import com.compomics.util.gui.renderers.AlignedListCellRenderer;
import eu.isas.peptideshaker.gui.HelpDialog;
import eu.isas.peptideshaker.gui.PeptideShakerGUI;
import eu.isas.peptideshaker.preferences.ModificationProfile;
import eu.isas.peptideshaker.preferences.SearchParameters;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import no.uib.jsparklines.renderers.JSparklinesColorTableCellRenderer;

/**
 * A dialog for displaying and editing the search preferences.
 *
 * @author Marc Vaudel
 * @author Harald Barsnes
 */
public class SearchPreferencesDialog extends javax.swing.JDialog {

    /**
     * The tooltips for the expected variable mods.
     */
    Vector<String> expectedVariableModsTableToolTips;
    /**
     * The search parameters needed by peptide-shaker
     */
    private SearchParameters searchParameters;
    /**
     * The enzyme factory
     */
    private EnzymeFactory enzymeFactory = EnzymeFactory.getInstance();
    /**
     * The peptide shaker gui
     */
    private PeptideShakerGUI peptideShakerGUI;
    /**
     * The compomics PTM factory
     */
    private PTMFactory ptmFactory = PTMFactory.getInstance();
    /**
     * A map of all loaded PTMs
     */
    private HashMap<String, PTM> ptms = new HashMap<String, PTM>();
    /**
     * The selected ptms
     */
    private ArrayList<String> modificationList = new ArrayList<String>();
    /**
     * File containing the modification profile. By default default.psm in the conf folder.
     */
    private File profileFile;
    /**
     * A simple progress dialog.
     */
    private static ProgressDialogX progressDialog;
    /**
     * boolean indicating whether import-related data can be edited
     */
    private boolean editable;

    /**
     * Create a new SearchPreferencesDialog.
     *
     * @param parent the PeptideShaker parent
     * @param editable  
     */
    public SearchPreferencesDialog(PeptideShakerGUI parent, boolean editable) {
        super(parent, true);
        this.editable = editable;
        this.peptideShakerGUI = parent;
        this.searchParameters = parent.getSearchParameters();
        this.profileFile = parent.getModificationProfileFile();
        loadModifications();
        initComponents();

        // set table properties
        expectedModificationsTable.getTableHeader().setReorderingAllowed(false);
        availableModificationsTable.getTableHeader().setReorderingAllowed(false);

        // set the renderer for the color column
        expectedModificationsTable.getColumn("  ").setCellRenderer(new JSparklinesColorTableCellRenderer());

        availableModificationsTable.getColumn(" ").setMaxWidth(40);
        availableModificationsTable.getColumn(" ").setMinWidth(40);

        expectedModificationsTable.getColumn(" ").setMaxWidth(40);
        expectedModificationsTable.getColumn(" ").setMinWidth(40);
        expectedModificationsTable.getColumn("  ").setMaxWidth(40);
        expectedModificationsTable.getColumn("  ").setMinWidth(40);

        expectedVariableModsTableToolTips = new Vector<String>();
        expectedVariableModsTableToolTips.add(null);
        expectedVariableModsTableToolTips.add("Modification Color");
        expectedVariableModsTableToolTips.add("Modification Name");
        expectedVariableModsTableToolTips.add("Modification Family Name");
        expectedVariableModsTableToolTips.add("Modification Short Name");

        modificationList = new ArrayList<String>(searchParameters.getModificationProfile().getUtilitiesNames());
        Collections.sort(modificationList);
        enzymesCmb.setRenderer(new AlignedListCellRenderer(SwingConstants.CENTER));
        ion1Cmb.setRenderer(new AlignedListCellRenderer(SwingConstants.CENTER));
        ion2Cmb.setRenderer(new AlignedListCellRenderer(SwingConstants.CENTER));
        precursorUnit.setRenderer(new AlignedListCellRenderer(SwingConstants.CENTER));
        loadValues();
        updateModificationLists();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fragmentIonAccuracyTxt = new javax.swing.JTextField();
        enzymesCmb = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        missedCleavagesTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ion1Cmb = new javax.swing.JComboBox();
        ion2Cmb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        precursorAccuracy = new javax.swing.JTextField();
        precursorUnit = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        addModifications = new javax.swing.JButton();
        removeModification = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        expectedModificationsTable = new JTable() {
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        java.awt.Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        tip = (String) expectedVariableModsTableToolTips.get(realIndex);
                        return tip;
                    }
                };
            }
        };
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        profileTxt = new javax.swing.JTextField();
        clearProfileBtn = new javax.swing.JButton();
        saveAsProfileBtn = new javax.swing.JButton();
        saveProfileBtn = new javax.swing.JButton();
        loadProfileBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        availableModificationsTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fileTxt = new javax.swing.JTextField();
        loadButton = new javax.swing.JButton();
        helpLineLabel = new javax.swing.JLabel();
        searchPreferencesHelpJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Parameters");
        setResizable(false);

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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Enzyme and Fragment Ions"));
        jPanel5.setOpaque(false);

        jLabel1.setText("Fragment Ion Accuracy:");

        fragmentIonAccuracyTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        enzymesCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Enzyme:");

        missedCleavagesTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        missedCleavagesTxt.setText("1");

        jLabel7.setText("Missed Cleavages:");

        jLabel2.setText("Fragment Ion Type 1:");

        jLabel8.setText("Fragment Ion Type 2:");

        ion1Cmb.setModel(new DefaultComboBoxModel(searchParameters.getIons()));

        ion2Cmb.setModel(new DefaultComboBoxModel(searchParameters.getIons()));

        jLabel9.setText("Precursor Accuracy:");

        precursorAccuracy.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        precursorUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ppm", "Da" }));

        jLabel10.setText("Da");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(precursorAccuracy, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precursorUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(enzymesCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(fragmentIonAccuracyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)))
                .addGap(71, 71, 71)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(ion2Cmb, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(missedCleavagesTxt)
                            .addComponent(ion1Cmb, 0, 286, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ion1Cmb, ion2Cmb});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(enzymesCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(missedCleavagesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(precursorAccuracy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precursorUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(ion1Cmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fragmentIonAccuracyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(ion2Cmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Modification Profile"));
        jPanel6.setOpaque(false);

        addModifications.setText("<<");
        addModifications.setToolTipText("Add");
        addModifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModificationsActionPerformed(evt);
            }
        });

        removeModification.setText(">>");
        removeModification.setToolTipText("Remove");
        removeModification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeModificationActionPerformed(evt);
            }
        });

        expectedModificationsTable.setModel(new ModificationTable());
        expectedModificationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expectedModificationsTableMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                expectedModificationsTableMouseExited(evt);
            }
        });
        expectedModificationsTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                expectedModificationsTableMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(expectedModificationsTable);

        jLabel3.setFont(jLabel3.getFont().deriveFont((jLabel3.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabel3.setText("Expected Variable Modifications");

        jLabel6.setFont(jLabel6.getFont().deriveFont((jLabel6.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabel6.setText("Available Modifications");

        profileTxt.setEditable(false);
        profileTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        clearProfileBtn.setText("Clear");
        clearProfileBtn.setToolTipText("Clear the list of expected modifications");
        clearProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearProfileBtnActionPerformed(evt);
            }
        });

        saveAsProfileBtn.setText("Save As");
        saveAsProfileBtn.setToolTipText("Save the modification profile to a psm file");
        saveAsProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsProfileBtnActionPerformed(evt);
            }
        });

        saveProfileBtn.setText("Save");
        saveProfileBtn.setToolTipText("Save as default modification profile");
        saveProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProfileBtnActionPerformed(evt);
            }
        });

        loadProfileBtn.setText("Load");
        loadProfileBtn.setToolTipText("Load a modification profile from a psm file");
        loadProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadProfileBtnActionPerformed(evt);
            }
        });

        availableModificationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " ", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(availableModificationsTable);

        jButton1.setText("Load");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(profileTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadProfileBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveProfileBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveAsProfileBtn)
                                .addGap(63, 63, 63))
                            .addComponent(clearProfileBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeModification)
                            .addComponent(addModifications)))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(addModifications)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeModification))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(saveAsProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(clearProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(profileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("SearchGUI Parameters File"));
        jPanel2.setOpaque(false);

        jLabel4.setText("SearchGUI File:");

        fileTxt.setEditable(false);

        loadButton.setText("Load");
        loadButton.setToolTipText("Load parameters from a SearchGUI parameters file");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpLineLabel.setFont(helpLineLabel.getFont().deriveFont((helpLineLabel.getFont().getStyle() | java.awt.Font.ITALIC)));
        helpLineLabel.setText("Edit the search parameters and the modification profile and click OK to save.");

        searchPreferencesHelpJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.GIF"))); // NOI18N
        searchPreferencesHelpJButton.setToolTipText("Help");
        searchPreferencesHelpJButton.setBorder(null);
        searchPreferencesHelpJButton.setBorderPainted(false);
        searchPreferencesHelpJButton.setContentAreaFilled(false);
        searchPreferencesHelpJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchPreferencesHelpJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchPreferencesHelpJButtonMouseExited(evt);
            }
        });
        searchPreferencesHelpJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPreferencesHelpJButtonActionPerformed(evt);
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
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(helpLineLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPreferencesHelpJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );

        backgroundPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(helpLineLabel)
                    .addComponent(searchPreferencesHelpJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
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
     * Saves the settings and closes the dialog.
     *
     * @param evt
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (validateInput()) {
            searchParameters.setFragmentIonAccuracy(new Double(fragmentIonAccuracyTxt.getText()));
            searchParameters.setnMissedCleavages(new Integer(missedCleavagesTxt.getText()));
            searchParameters.setEnzyme(enzymeFactory.getEnzyme((String) enzymesCmb.getSelectedItem()));
            searchParameters.setIonSearched1((String) ion1Cmb.getSelectedItem());
            searchParameters.setIonSearched2((String) ion2Cmb.getSelectedItem());

            if (((String) precursorUnit.getSelectedItem()).equalsIgnoreCase("ppm")) {
                searchParameters.setPrecursorAccuracyType(SearchParameters.PrecursorAccuracyType.PPM);
            } else { // Da
                searchParameters.setPrecursorAccuracyType(SearchParameters.PrecursorAccuracyType.DA);
            }

            searchParameters.setPrecursorAccuracy(new Double(precursorAccuracy.getText()));
            peptideShakerGUI.setSearchParameters(searchParameters);
            peptideShakerGUI.updateAnnotationPreferencesFromSearchSettings();
            peptideShakerGUI.setModificationProfileFile(profileFile);
            peptideShakerGUI.setDataSaved(false);
            this.dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Closes the dialog.
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Adds a modification to the list.
     *
     * @param evt
     */
    private void addModificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModificationsActionPerformed

        int[] selectedRows = availableModificationsTable.getSelectedRows();

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            String name = (String) availableModificationsTable.getValueAt(selectedRows[i], 1);
            searchParameters.getModificationProfile().setPeptideShakerName(name, name);
            if (!searchParameters.getModificationProfile().getPeptideShakerNames().contains(name)) {
                int index = name.length();
                if (name.lastIndexOf(" ") > 0) {
                    index = name.indexOf(" ");
                }
                if (name.lastIndexOf("-") > 0) {
                    index = Math.min(index, name.indexOf("-"));
                }
                searchParameters.getModificationProfile().setShortName(name, name.substring(0, index));
                searchParameters.getModificationProfile().setColor(name, Color.lightGray);
            }
            modificationList.add(name);
            ((DefaultTableModel) availableModificationsTable.getModel()).removeRow(selectedRows[i]);
        }

        updateModificationLists();
    }//GEN-LAST:event_addModificationsActionPerformed

    /**
     * Removes a modification from the list.
     *
     * @param evt
     */
    private void removeModificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeModificationActionPerformed

        ArrayList<String> toRemove = new ArrayList<String>();

        for (int selectedRow : expectedModificationsTable.getSelectedRows()) {
            toRemove.add((String) expectedModificationsTable.getValueAt(selectedRow, 2));
        }

        for (String name : toRemove) {
            modificationList.remove(name);
            searchParameters.getModificationProfile().remove(name);
        }

        updateModificationLists();
    }//GEN-LAST:event_removeModificationActionPerformed

    /**
     * Loads the search preferences from a SearchGUI file.
     *
     * @param evt
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        JFileChooser fc = new JFileChooser(peptideShakerGUI.getLastSelectedFolder());

        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File myFile) {
                return myFile.getName().toLowerCase().endsWith("properties") || myFile.isDirectory();
            }

            @Override
            public String getDescription() {
                return "(SearchGUI properties file) *.properties";
            }
        };

        fc.setFileFilter(filter);

        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                Properties props = IdentificationParametersReader.loadProperties(file);
                setScreenProps(props);
                searchParameters.setParametersFile(file);
                fileTxt.setText(file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, file.getName() + " not found.", "File Not Found", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occured while reading " + file.getName() + ".\n"
                        + "Please verify the version compatibility.", "File Import Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * Clears the list of selected proteins.
     * 
     * @param evt 
     */
    private void clearProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearProfileBtnActionPerformed
        searchParameters.setModificationProfile(new ModificationProfile());
        modificationList.clear();
        expectedModificationsTable.revalidate();
        expectedModificationsTable.repaint();
        profileTxt.setText("");
    }//GEN-LAST:event_clearProfileBtnActionPerformed

    /**
     * Loads a modification profile.
     * 
     * @param evt 
     */
    private void loadProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadProfileBtnActionPerformed
        JFileChooser fc = new JFileChooser(peptideShakerGUI.getLastSelectedFolder());

        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File myFile) {
                return myFile.getName().toLowerCase().endsWith("psm") || myFile.isDirectory();
            }

            @Override
            public String getDescription() {
                return "(Profile psm file) *.psm";
            }
        };

        fc.setFileFilter(filter);

        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            loadModificationProfile(file);
        }
    }//GEN-LAST:event_loadProfileBtnActionPerformed

    /**
     * Save a modification profile.
     * 
     * @param evt 
     */
    private void saveAsProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsProfileBtnActionPerformed

        final JFileChooser fileChooser = new JFileChooser(peptideShakerGUI.getLastSelectedFolder());
        fileChooser.setDialogTitle("Save As...");
        fileChooser.setMultiSelectionEnabled(false);

        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File myFile) {
                return myFile.getName().toLowerCase().endsWith("psm") || myFile.isDirectory();
            }

            @Override
            public String getDescription() {
                return "(PeptideShaker Modifications) *.psm";
            }
        };

        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            progressDialog = new ProgressDialogX(peptideShakerGUI, peptideShakerGUI, true);
            progressDialog.doNothingOnClose();

            final PeptideShakerGUI tempRef = peptideShakerGUI; // needed due to threading issues

            new Thread(new Runnable() {

                public void run() {
                    progressDialog.setIndeterminate(true);
                    progressDialog.setTitle("Saving. Please Wait...");
                    progressDialog.setVisible(true);
                }
            }, "ProgressDialog").start();

            new Thread("SaveThread") {

                @Override
                public void run() {

                    String selectedFile = fileChooser.getSelectedFile().getPath();

                    if (!selectedFile.endsWith(".psm")) {
                        selectedFile += ".psm";
                    }

                    File newFile = new File(selectedFile);
                    int outcome = JOptionPane.YES_OPTION;

                    if (newFile.exists()) {
                        outcome = JOptionPane.showConfirmDialog(progressDialog,
                                "Should " + selectedFile + " be overwritten?", "Selected File Already Exists",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    }

                    if (outcome != JOptionPane.YES_OPTION) {
                        progressDialog.setVisible(false);
                        progressDialog.dispose();
                        return;
                    }

                    try {

                        FileOutputStream fos = new FileOutputStream(newFile);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(searchParameters.getModificationProfile());
                        oos.close();
                        profileFile = newFile;
                        profileTxt.setText(newFile.getName().substring(0, newFile.getName().lastIndexOf(".")));
                        progressDialog.setVisible(false);
                        progressDialog.dispose();

                    } catch (Exception e) {

                        progressDialog.setVisible(false);
                        progressDialog.dispose();

                        JOptionPane.showMessageDialog(tempRef, "Failed saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }//GEN-LAST:event_saveAsProfileBtnActionPerformed

    /**
     * Save a modification profile.
     * 
     * @param evt 
     */
    private void saveProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProfileBtnActionPerformed

        progressDialog = new ProgressDialogX(peptideShakerGUI, peptideShakerGUI, true);
        progressDialog.doNothingOnClose();

        final PeptideShakerGUI tempRef = peptideShakerGUI; // needed due to threading issues

        new Thread(new Runnable() {

            public void run() {
                progressDialog.setIndeterminate(true);
                progressDialog.setTitle("Saving. Please Wait...");
                progressDialog.setVisible(true);
            }
        }, "ProgressDialog").start();

        new Thread("SaveThread") {

            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(profileFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(searchParameters.getModificationProfile());
                    oos.close();
                    progressDialog.setVisible(false);
                    progressDialog.dispose();
                } catch (Exception e) {
                    progressDialog.setVisible(false);
                    progressDialog.dispose();
                    JOptionPane.showMessageDialog(tempRef, "Failed saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }.start();
    }//GEN-LAST:event_saveProfileBtnActionPerformed

    /**
     * Change the cursor to a hand cursor.
     * 
     * @param evt 
     */
    private void searchPreferencesHelpJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchPreferencesHelpJButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}//GEN-LAST:event_searchPreferencesHelpJButtonMouseEntered

    /**
     * Change the cursor back to the default cursor.
     * 
     * @param evt 
     */
    private void searchPreferencesHelpJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchPreferencesHelpJButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_searchPreferencesHelpJButtonMouseExited

    /**
     * Open the help dialog.
     * 
     * @param evt 
     */
    private void searchPreferencesHelpJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPreferencesHelpJButtonActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        new HelpDialog(peptideShakerGUI, getClass().getResource("/helpFiles/SearchPreferencesDialog.html"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_searchPreferencesHelpJButtonActionPerformed

    /**
     * Changes the cursor to a hand cursor if over the color column.
     * 
     * @param evt 
     */
private void expectedModificationsTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expectedModificationsTableMouseMoved

    int row = expectedModificationsTable.rowAtPoint(evt.getPoint());
    int column = expectedModificationsTable.columnAtPoint(evt.getPoint());

    if (row != -1 && column == 1) {
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    } else {
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
}//GEN-LAST:event_expectedModificationsTableMouseMoved

    /**
     * Changes the cursor back to the default cursor.
     * 
     * @param evt 
     */
private void expectedModificationsTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expectedModificationsTableMouseExited
    this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_expectedModificationsTableMouseExited

    /**
     * Opens a file chooser where the color for the ptm can be changed.
     * 
     * @param evt 
     */
private void expectedModificationsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expectedModificationsTableMouseClicked

    int row = expectedModificationsTable.rowAtPoint(evt.getPoint());
    int column = expectedModificationsTable.columnAtPoint(evt.getPoint());

    if (row != -1 && column == 1) {
        Color newColor = JColorChooser.showDialog(this, "Pick a Color", (Color) expectedModificationsTable.getValueAt(row, column));

        if (newColor != null) {
            searchParameters.getModificationProfile().setColor(searchParameters.getModificationProfile().getPeptideShakerName(modificationList.get(row)), newColor);
            expectedModificationsTable.repaint();
        }
    }
}//GEN-LAST:event_expectedModificationsTableMouseClicked

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    JFileChooser fc = new JFileChooser(peptideShakerGUI.getLastSelectedFolder());

    FileFilter filter = new FileFilter() {

        @Override
        public boolean accept(File myFile) {
            return myFile.getName().toLowerCase().endsWith("usermods.xml") || myFile.isDirectory();
        }

        @Override
        public String getDescription() {
            return "(user modification file) *usermods.xml";
        }
    };

    fc.setFileFilter(filter);

    int result = fc.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        try {
            ptmFactory.importModifications(file, true);
            loadModifications();
            updateModificationLists();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while importing " + file.getName() + ".", "User Modification File Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addModifications;
    private javax.swing.JTable availableModificationsTable;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearProfileBtn;
    private javax.swing.JComboBox enzymesCmb;
    private javax.swing.JTable expectedModificationsTable;
    private javax.swing.JTextField fileTxt;
    private javax.swing.JTextField fragmentIonAccuracyTxt;
    private javax.swing.JLabel helpLineLabel;
    private javax.swing.JComboBox ion1Cmb;
    private javax.swing.JComboBox ion2Cmb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton loadProfileBtn;
    private javax.swing.JTextField missedCleavagesTxt;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField precursorAccuracy;
    private javax.swing.JComboBox precursorUnit;
    private javax.swing.JTextField profileTxt;
    private javax.swing.JButton removeModification;
    private javax.swing.JButton saveAsProfileBtn;
    private javax.swing.JButton saveProfileBtn;
    private javax.swing.JButton searchPreferencesHelpJButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Returns true if the input can be correctly imported.
     * 
     * @return true if the input can be correctly imported
     */
    private boolean validateInput() {
        try {
            new Double(fragmentIonAccuracyTxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please verify the input for fragment ion accuracy.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            new Integer(missedCleavagesTxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please verify the input for allowed missed cleavages.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            new Double(precursorAccuracy.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please verify the input for allowed missed cleavages.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * This method takes the specified properties instance and reads the values
     * for (some of) the GUI components from it. Keys are defined as constants on this
     * class.
     * Method inspired from searchGUI
     *
     * @param aProps Properties with the values for the GUI.
     */
    public void setScreenProps(Properties aProps) {

        ArrayList<String> variableMods = new ArrayList<String>();

        String temp = aProps.getProperty(IdentificationParametersReader.VARIABLE_MODIFICATIONS);

        if (temp != null && !temp.trim().equals("")) {
            variableMods = IdentificationParametersReader.parseModificationLine(temp);
        }

        ArrayList<String> missing = new ArrayList<String>();
        for (String name : variableMods) {
            if (!modificationList.contains(name)) {
                if (!ptmFactory.containsPTM(name)) {
                    missing.add(name);
                } else {
                    searchParameters.getModificationProfile().setPeptideShakerName(name, name);
                    if (!searchParameters.getModificationProfile().getPeptideShakerNames().contains(name)) {
                        int index = name.length() - 1;
                        if (name.lastIndexOf(" ") > 0) {
                            index = name.indexOf(" ");
                        }
                        if (name.lastIndexOf("-") > 0) {
                            index = Math.min(index, name.indexOf("-"));
                        }
                        searchParameters.getModificationProfile().setShortName(name, name.substring(0, index));
                        searchParameters.getModificationProfile().setColor(name, Color.lightGray);  // @TODO: color should not be hardcoded!
                    }
                    modificationList.add(name);
                }
            }
        }
        if (!missing.isEmpty()) {
            if (missing.size() == 1) {
                JOptionPane.showMessageDialog(this, "The following modification is currently not recognized by PeptideShaker: "
                        + missing.get(0) + ".\nPlease import it by opening a usermods.xml file.", "Modification Not Found", JOptionPane.WARNING_MESSAGE);
            } else {
                String output = "The following modifications are currently not recognized by PeptideShaker:\n";
                boolean first = true;
                for (String ptm : missing) {
                    if (first) {
                        first = false;
                    } else {
                        output += ", ";
                    }
                    output += ptm;
                }
                output += ".\nPlease import it by opening a usermods.xml file.";
                JOptionPane.showMessageDialog(this, output, "Modification Not Found", JOptionPane.WARNING_MESSAGE);
            }
        }
        updateModificationLists();

        temp = aProps.getProperty(IdentificationParametersReader.ENZYME);

        if (temp != null && !temp.equals("")) {
            enzymesCmb.setSelectedItem(temp.trim());
        }

        temp = aProps.getProperty(IdentificationParametersReader.FRAGMENT_ION_MASS_ACCURACY);

        if (temp == null) {
            temp = "";
        }

        fragmentIonAccuracyTxt.setText(temp.trim());
        temp = aProps.getProperty(IdentificationParametersReader.MISSED_CLEAVAGES);

        if (temp == null) {
            temp = "";
        }
        missedCleavagesTxt.setText(temp.trim());

        temp = aProps.getProperty(IdentificationParametersReader.FRAGMENT_ION_TYPE_1);

        if (temp != null && temp.length() > 0) {
            ion1Cmb.setSelectedItem(temp);
        }

        temp = aProps.getProperty(IdentificationParametersReader.FRAGMENT_ION_TYPE_2);

        if (temp != null && temp.length() > 0) {
            ion2Cmb.setSelectedItem(temp);
        }

        temp = aProps.getProperty(IdentificationParametersReader.PRECURSOR_MASS_ACCURACY_UNIT);

        if (temp != null) {
            precursorUnit.setSelectedItem(temp);
        }


        temp = aProps.getProperty(IdentificationParametersReader.FRAGMENT_ION_MASS_ACCURACY);

        if (temp == null) {
            temp = "";
        }

        precursorAccuracy.setText(temp.trim());

    }

    /**
     * Repaints the table.
     */
    private void repaintTable() {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                expectedModificationsTable.revalidate();
                expectedModificationsTable.repaint();
            }
        });
    }

    /**
     * Loads the implemented enzymes.
     *
     * @return the list of enzyme names
     */
    private String[] loadEnzymes() {

        ArrayList<String> tempEnzymes = new ArrayList<String>();

        for (int i = 0; i < enzymeFactory.getEnzymes().size(); i++) {
            tempEnzymes.add(enzymeFactory.getEnzymes().get(i).getName());
        }

        Collections.sort(tempEnzymes);

        String[] enzymes = new String[tempEnzymes.size()];

        for (int i = 0; i < tempEnzymes.size(); i++) {
            enzymes[i] = tempEnzymes.get(i);
        }

        return enzymes;
    }

    /**
     * Loads values from the searchParameters.
     */
    private void loadValues() {

        fragmentIonAccuracyTxt.setText(searchParameters.getFragmentIonAccuracy() + "");
        enzymesCmb.setModel(new DefaultComboBoxModel(loadEnzymes()));
        enzymesCmb.setRenderer(new AlignedListCellRenderer(SwingConstants.CENTER));
        enzymesCmb.setSelectedItem(searchParameters.getEnzyme().getName());
        missedCleavagesTxt.setText(searchParameters.getnMissedCleavages() + "");

        if (searchParameters.getPrecursorAccuracyType() == SearchParameters.PrecursorAccuracyType.PPM) {
            precursorUnit.setSelectedItem("ppm");
        } else { // Dalton
            precursorUnit.setSelectedItem("Da");
        }

        precursorAccuracy.setText(searchParameters.getPrecursorAccuracy() + "");
        setIons();
        if (searchParameters.getParametersFile() != null) {
            fileTxt.setText(searchParameters.getParametersFile().getAbsolutePath());
        }
    }

    /**
     * Sets the selected ion types
     */
    private void setIons() {
        if (searchParameters.getIonSearched1() == PeptideFragmentIonType.A_ION) {
            ion1Cmb.setSelectedItem("a");
        } else if (searchParameters.getIonSearched1() == PeptideFragmentIonType.B_ION) {
            ion1Cmb.setSelectedItem("b");
        } else if (searchParameters.getIonSearched1() == PeptideFragmentIonType.C_ION) {
            ion1Cmb.setSelectedItem("c");
        } else if (searchParameters.getIonSearched1() == PeptideFragmentIonType.X_ION) {
            ion1Cmb.setSelectedItem("x");
        } else if (searchParameters.getIonSearched1() == PeptideFragmentIonType.Y_ION) {
            ion1Cmb.setSelectedItem("y");
        } else if (searchParameters.getIonSearched1() == PeptideFragmentIonType.Z_ION) {
            ion1Cmb.setSelectedItem("z");
        }
        if (searchParameters.getIonSearched2() == PeptideFragmentIonType.A_ION) {
            ion2Cmb.setSelectedItem("a");
        } else if (searchParameters.getIonSearched2() == PeptideFragmentIonType.B_ION) {
            ion2Cmb.setSelectedItem("b");
        } else if (searchParameters.getIonSearched2() == PeptideFragmentIonType.C_ION) {
            ion2Cmb.setSelectedItem("c");
        } else if (searchParameters.getIonSearched2() == PeptideFragmentIonType.X_ION) {
            ion2Cmb.setSelectedItem("x");
        } else if (searchParameters.getIonSearched2() == PeptideFragmentIonType.Y_ION) {
            ion2Cmb.setSelectedItem("y");
        } else if (searchParameters.getIonSearched2() == PeptideFragmentIonType.Z_ION) {
            ion2Cmb.setSelectedItem("z");
        }
    }

    /**
     * Loads the modification profile from the given file.
     * 
     * @param aFile the given file
     */
    private void loadModificationProfile(File aFile) {
        try {
            FileInputStream fis = new FileInputStream(aFile);
            ObjectInputStream in = new ObjectInputStream(fis);
            ModificationProfile modificationProfile = (ModificationProfile) in.readObject();
            in.close();

            searchParameters.setModificationProfile(modificationProfile);

            modificationList = new ArrayList<String>(searchParameters.getModificationProfile().getUtilitiesNames());
            Collections.sort(modificationList);
            profileFile = aFile;
            profileTxt.setText(aFile.getName().substring(0, aFile.getName().lastIndexOf(".")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, aFile.getName() + " not found.", "File Not Found", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occured while reading " + aFile.getName() + ".\n"
                    + "Please verify the version compatibility.", "File Import Error", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occured while reading " + aFile.getName() + ".\n"
                    + "Please verify the version compatibility.", "File Import Error", JOptionPane.WARNING_MESSAGE);
        } catch (ClassCastException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occured while reading " + aFile.getName() + ".\n"
                    + "Please verify the version compatibility.", "File Import Error", JOptionPane.WARNING_MESSAGE);
        }

        expectedModificationsTable.revalidate();
        expectedModificationsTable.repaint();
    }

    /**
     * Loads the modifications from the modification file.
     */
    private void loadModifications() {
        for (String ptm : ptmFactory.getPTMs()) {
            ptms.put(ptm, ptmFactory.getPTM(ptm));
        }
    }

    /**
     * Updates the modification list (right).
     */
    private void updateModificationLists() {

        ArrayList<String> allModificationsList = new ArrayList<String>(ptms.keySet());
        ArrayList<String> allModifications = new ArrayList<String>();
        boolean found = false;

        for (String name : allModificationsList) {
            found = false;

            for (String modification : modificationList) {
                if (modification.equals(name)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                allModifications.add(name);
            }
        }

        String[] allModificationsAsArray = new String[allModifications.size()];

        for (int i = 0; i < allModifications.size(); i++) {
            allModificationsAsArray[i] = allModifications.get(i);
        }

        Arrays.sort(allModificationsAsArray);

        DefaultTableModel dm = (DefaultTableModel) availableModificationsTable.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();

        for (int i = 0; i < allModificationsAsArray.length; i++) {
            ((DefaultTableModel) availableModificationsTable.getModel()).addRow(new Object[]{
                        (i + 1),
                        allModificationsAsArray[i]
                    });
        }

        if (availableModificationsTable.getRowCount() > 0) {
            availableModificationsTable.setRowSelectionInterval(0, 0);
        }

        repaintTable();
    }

    /**
     * Verifies that this family name is not used by another modification with a different mass
     * @param utilitiesName     the utilities name to convert
     * @param peptideShakerName the destination family name
     * @return true if the family name is not used for another modification with a different mass
     */
    private boolean freePSName(String utilitiesName, String peptideShakerName) {
        for (String ptmName : searchParameters.getModificationProfile().getUtilitiesNames()) {
            if (searchParameters.getModificationProfile().getPeptideShakerName(ptmName).equals(peptideShakerName)
                    && ptmFactory.getPTM(ptmName).getMass() != ptmFactory.getPTM(utilitiesName).getMass()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Table model for the modification table.
     */
    private class ModificationTable extends DefaultTableModel {

        @Override
        public int getRowCount() {
            return modificationList.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 1:
                    return "  ";
                case 2:
                    return "Name";
                case 3:
                    return "Family";
                case 4:
                    return "Short";
                default:
                    return " ";
            }
        }

        @Override
        public Object getValueAt(int row, int column) {
            switch (column) {
                case 0:
                    return row + 1;
                case 1:
                    return searchParameters.getModificationProfile().getColor(
                            searchParameters.getModificationProfile().getPeptideShakerName(modificationList.get(row)));
                case 2:
                    return modificationList.get(row);
                case 3:
                    return searchParameters.getModificationProfile().getPeptideShakerName(modificationList.get(row));
                case 4:
                    return searchParameters.getModificationProfile().getShortName(
                            searchParameters.getModificationProfile().getPeptideShakerName(modificationList.get(row)));
                default:
                    return "";
            }
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            try {
                if (column == 3 && editable) {
                    if (freePSName(getValueAt(row, 2).toString(), aValue.toString())) {
                        searchParameters.getModificationProfile().setPeptideShakerName(modificationList.get(row), aValue.toString().trim());
                    } else {
                        JOptionPane.showMessageDialog(null, aValue.toString() + " is already used for a modification with a different mass. Please select another name.",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (column == 4) {
                    searchParameters.getModificationProfile().setShortName(searchParameters.getModificationProfile().getPeptideShakerName(modificationList.get(row)), aValue.toString().trim());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please verify the input for " + modificationList.get(row) + " occurrence.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            repaintTable();
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            for (int i = 0; i < getRowCount(); i++) {
                if (getValueAt(i, columnIndex) != null) {
                    return getValueAt(i, columnIndex).getClass();
                }
            }
            return (new Double(0.0)).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int column) {

            if (column == 1 || column == 3 && editable || column == 4) {
                return true;
            } else {
                return false;
            }
        }
    }
}
