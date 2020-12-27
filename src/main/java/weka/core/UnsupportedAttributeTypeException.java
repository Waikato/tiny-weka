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
 *    UnsuppotedAttributeTypeException.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Exception that is raised by an object that is unable to process some of the
 * attribute types it has been passed.
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public class UnsupportedAttributeTypeException
  extends WekaException {

  /** for serialization */
  private static final long serialVersionUID = 2658987325328414838L;

  /**
   * Creates a new UnsupportedAttributeTypeException with no message.
   *
   */
  public UnsupportedAttributeTypeException() {

    super();
  }

  /**
   * Creates a new UnsupportedAttributeTypeException.
   *
   * @param message the reason for raising an exception.
   */
  public UnsupportedAttributeTypeException(String message) {

    super(message);
  }
}
