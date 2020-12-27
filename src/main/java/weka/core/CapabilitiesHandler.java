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
 * CapabilitiesHandler.java
 * Copyright (C) 2006-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

/**
 * Classes implementing this interface return their capabilities in regards
 * to datasets.
 * 
 * @author  FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 11006 $
 * @see     Capabilities
 */
public interface CapabilitiesHandler {

  /**
   * Returns the capabilities of this object.
   *
   * @return            the capabilities of this object
   * @see               Capabilities
   */
  public Capabilities getCapabilities();
}
