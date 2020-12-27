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
 *    DateAttributeInfo.java
 *    Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 *
 */
package weka.core;

import java.text.SimpleDateFormat;

/**
 * Stores information for date attributes.
 */
public class DateAttributeInfo implements AttributeInfo {

  /** Date format specification for date attributes */
  protected SimpleDateFormat m_DateFormat;

  /**
   * Constructs info based on argument.
   */
  public DateAttributeInfo(String dateFormat) {
    if (dateFormat != null) {
      m_DateFormat = new SimpleDateFormat(dateFormat);
    } else {
      m_DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }
    m_DateFormat.setLenient(false);
  }
}