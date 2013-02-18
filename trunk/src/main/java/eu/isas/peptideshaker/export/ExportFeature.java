/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.isas.peptideshaker.export;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This interface represtents an export feature
 *
 * @author Marc
 */
public interface ExportFeature extends Serializable {
    
    /**
     * returns the title of the feature
     * @return the title of the feature
     */
    public String getTitle();
    /**
     * Returns the description of the feature
     * @return the description of the feature
     */
    public String getDescription();
    /**
     * Returns the family type of this export feature
     * @return the family type of this export feature
     */
    public String getFeatureFamily();
    /**
     * Returns a list of all implemented export features
     * @return a list of all implemented export features
     */
    public ArrayList<ExportFeature> getExportFeatures();
}
