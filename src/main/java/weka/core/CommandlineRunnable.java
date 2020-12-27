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
 *    CommandlineRunnable.java
 *    Copyright (C) 2010-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Interface to something that can be run from the command line.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 12184 $
 */
public interface CommandlineRunnable {

  /**
   * Perform any setup stuff that might need to happen before execution.
   *
   * @throws Exception if a problem occurs during setup
   */
  void preExecution() throws Exception;
  
  /**
   * Execute the supplied object.
   * 
   * @param toRun the object to execute
   * @param options any options to pass to the object
   * @throws Exception if a problem occurs.
   */
  void run(Object toRun, String[] options) throws Exception;

  /**
   * Perform any teardown stuff that might need to happen after execution.
   *
   * @throws Exception if a problem occurs during teardown
   */
  void postExecution() throws Exception;
}
