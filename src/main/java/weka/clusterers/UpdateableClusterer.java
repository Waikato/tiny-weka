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
 * UpdateableClusterer.java
 * Copyright (C) 2006-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.clusterers;

import weka.core.Instance;

/**
 * Interface to incremental cluster models that can learn using one instance 
 * at a time.
 * 
 * @author  FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 10829 $
 */
public interface UpdateableClusterer {

  /**
   * Adds an instance to the clusterer.
   *
   * @param newInstance the instance to be added
   * @throws Exception 	if something goes wrong
   */
  public void updateClusterer(Instance newInstance) throws Exception;

  /**
   * Signals the end of the updating.
   */
  public void updateFinished();
}
