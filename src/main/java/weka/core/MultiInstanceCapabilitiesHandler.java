/*
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 * MultiInstanceClassifier.java
 * Copyright (C) 2006-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

/**
 * Multi-Instance classifiers can specify an additional Capabilities object
 * for the data in the relational attribute, since the format of multi-instance
 * data is fixed to "bag/NOMINAL,data/RELATIONAL,class".
 * 
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 8034 $
 */
public interface MultiInstanceCapabilitiesHandler
  extends CapabilitiesHandler {

  /**
   * Returns the capabilities of this multi-instance classifier for the
   * relational data (i.e., the bags).
   *
   * @return            the capabilities of this object
   * @see               Capabilities
   */
  public Capabilities getMultiInstanceCapabilities();
}
