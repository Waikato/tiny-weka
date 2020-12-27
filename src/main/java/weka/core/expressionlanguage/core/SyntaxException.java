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
 *    SyntaxException.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.expressionlanguage.core;

/**
 * An exception to represent an invalid syntax of a program
 * 
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
public class SyntaxException extends Exception {

  /** for serialization */
  private static final long serialVersionUID = 390076835893846782L;


  /**
   * Constructs a {@link SyntaxException} with a message
   * 
   * @param msg the message of the exception
   */
  public SyntaxException(String msg) {
    super(msg);
  }
}
