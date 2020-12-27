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
 *    RelationalAttributeInfo.java
 *    Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 *
 */
package weka.core;

/**
 * Stores information for relational attributes.
 */
public class RelationalAttributeInfo extends NominalAttributeInfo {

  /** The header information for a relation-valued attribute. */
  protected Instances m_Header;

  /**
   * Constructs the information object based on the given parameter.
   */
  public RelationalAttributeInfo(Instances header) {

    super(null, null);
    m_Header = header;
  }
}