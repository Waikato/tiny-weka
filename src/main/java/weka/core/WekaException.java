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
 *    WekaException.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Class for Weka-specific exceptions.
 * 
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 11353 $
 */
public class WekaException
  extends Exception {

  /** for serialization */
  private static final long serialVersionUID = 6428269989006208585L;

  /**
   * Creates a new WekaException with no message.
   * 
   */
  public WekaException() {

    super();
  }

  /**
   * Creates a new WekaException.
   * 
   * @param message the reason for raising an exception.
   */
  public WekaException(String message) {

    super(message);
  }

  /**
   * Constructor with message and cause
   * 
   * @param message the message for the exception
   * @param cause the root cause Throwable
   */
  public WekaException(String message, Throwable cause) {
    this(message);
    initCause(cause);
    fillInStackTrace();
  }

  /**
   * Constructor with cause argument
   * 
   * @param cause the root cause Throwable
   */
  public WekaException(Throwable cause) {
    this(cause.getMessage(), cause);
  }
}
