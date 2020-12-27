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
 *    UnassignedClassException.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Exception that is raised when trying to use some data that has no
 * class assigned to it, but a class is needed to perform the operation.
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public class UnassignedClassException
  extends RuntimeException {

  /** for serialization */
  private static final long serialVersionUID = 6268278694768818235L;

  /**
   * Creates a new UnassignedClassException with no message.
   *
   */
  public UnassignedClassException() {

    super();
  }

  /**
   * Creates a new UnassignedClassException.
   *
   * @param message the reason for raising an exception.
   */
  public UnassignedClassException(String message) {

    super(message);
  }
}
