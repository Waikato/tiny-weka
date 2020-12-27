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
 *    SemanticException.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.expressionlanguage.core;

/**
 * An exception that should be used if a program doesn't have valid semantics
 * 
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
public class SemanticException extends Exception {

  /** for serialization */
  private static final long serialVersionUID = -1212116135142845573L;

  /**
   * Constructs a {@link SemanticException} with a message and cause
   * 
   * @param msg the message of the exception
   * @param e the cause of the exception
   */
  public SemanticException(String msg, Exception e) {
    super(msg, e);
  }

  /**
   * Constructs a {@link SemanticException} with a message
   * 
   * @param msg the message of the exception
   */
  public SemanticException(String msg) {
    super(msg);
  }
}
