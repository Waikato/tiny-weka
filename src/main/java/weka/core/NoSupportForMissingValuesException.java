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
 *    NoSupportForMissingValuesException.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Exception that is raised by an object that is unable to process 
 * data with missing values.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public class NoSupportForMissingValuesException
  extends WekaException {

  /** for serialization */
  private static final long serialVersionUID = 5161175307725893973L;

  /**
   * Creates a new NoSupportForMissingValuesException with no message.
   *
   */
  public NoSupportForMissingValuesException() {

    super();
  }

  /**
   * Creates a new NoSupportForMissingValuesException.
   *
   * @param message the reason for raising an exception.
   */
  public NoSupportForMissingValuesException(String message) {

    super(message);
  }
}
