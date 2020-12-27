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

/**
 * CapabilitiesUtils.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package weka.core;

/**
 * Helper class for Capabilities.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class CapabilitiesUtils {

  /**
   * returns a comma-separated list of all the capabilities, excluding interface-based ones.
   *
   * @param c the capabilities to get a string representation from
   * @return the string describing the capabilities
   */
  public static String listCapabilities(Capabilities c) {
    return c.listCapabilities();
  }

  /**
   * generates a string from the capabilities, suitable to add to the help
   * text.
   *
   * @param title the title for the capabilities
   * @param c the capabilities
   * @return a string describing the capabilities
   */
  public static String addCapabilities(String title, Capabilities c) {
   return c.addCapabilities(title);
  }
}
