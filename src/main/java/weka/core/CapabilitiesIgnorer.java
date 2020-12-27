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
 * CapabilitiesIgnorer.java
 * Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

/**
 * Classes implementing this interface make it possible to turn off
 * capabilities checking.
 * 
 * @author  Eibe Frank
 * @version $Revision: 11004 $
 * @see     Capabilities
 */
public interface CapabilitiesIgnorer {
  
  /** 
   * Returns true if we do not actually want to check
   * capabilities to conserver runtime.
   */
  public boolean getDoNotCheckCapabilities();
  
  /** 
   * If argument is true, capabilities are not actually
   * checked to improve runtime.
   */
  public void setDoNotCheckCapabilities(boolean flag);
}
