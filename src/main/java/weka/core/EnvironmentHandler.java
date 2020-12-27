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
 * EnvironmentHandler.java
 * Copyright (C) 2009-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.core;


/**
 * Interface for something that can utilize environment
 * variables. NOTE: since environment variables should
 * be transient, the implementer needs to be careful
 * of state after de-serialization. Default system-wide
 * environment variables can be got via a call to
 * <code>weka.core.Environment.getSystemWide()</code>
 * 
 * @author mhall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 8034 $
 */
public interface EnvironmentHandler {
  
  /**
   * Set environment variables to use.
   * 
   * @param env the environment variables to
   * use
   */
  void setEnvironment(Environment env);
}
