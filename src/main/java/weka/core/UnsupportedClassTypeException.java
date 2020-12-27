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
 *    UnsuppotedClassTypeException.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Exception that is raised by an object that is unable to process the
 * class type of the data it has been passed.
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public class UnsupportedClassTypeException
  extends WekaException {

  /** for serialization */
  private static final long serialVersionUID = 5175741076972192151L;

  /**
   * Creates a new UnsupportedClassTypeException with no message.
   *
   */
  public UnsupportedClassTypeException() {

    super();
  }

  /**
   * Creates a new UnsupportedClassTypeException.
   *
   * @param message the reason for raising an exception.
   */
  public UnsupportedClassTypeException(String message) {

    super(message);
  }
}
