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
 *    Associator.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.associations;

import weka.core.Capabilities;
import weka.core.Instances;

public interface Associator {

  /**
   * Generates an associator. Must initialize all fields of the associator
   * that are not being set via options (ie. multiple calls of buildAssociator
   * must always lead to the same result). Must not change the dataset
   * in any way.
   *
   * @param data set of instances serving as training data 
   * @exception Exception if the associator has not been 
   * generated successfully
   */
  void buildAssociations(Instances data) throws Exception;

  /** 
   * Returns the Capabilities of this associator. Derived associators have to
   * override this method to enable capabilities.
   *
   * @return            the capabilities of this object
   * @see               Capabilities
   */
  Capabilities getCapabilities();
}