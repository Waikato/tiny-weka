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
 *    UnassignedDatasetException.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Exception that is raised when trying to use something that has no
 * reference to a dataset, when one is required.
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public class UnassignedDatasetException
  extends RuntimeException {

  /** for serialization */
  private static final long serialVersionUID = -9000116786626328854L;

  /**
   * Creates a new UnassignedDatasetException with no message.
   *
   */
  public UnassignedDatasetException() {

    super();
  }

  /**
   * Creates a new UnassignedDatasetException.
   *
   * @param message the reason for raising an exception.
   */
  public UnassignedDatasetException(String message) {

    super(message);
  }
}
