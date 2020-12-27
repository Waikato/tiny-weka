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
 * SimpleFilter.java
 * Copyright (C) 2005-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.filters;

import java.util.Enumeration;
import java.util.Vector;

import weka.core.Capabilities;
import weka.core.Instances;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.Utils;

/**
 * This filter contains common behavior of the SimpleBatchFilter and the
 * SimpleStreamFilter.
 * 
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 11247 $
 * @see SimpleBatchFilter
 * @see SimpleStreamFilter
 */
public abstract class SimpleFilter extends Filter {

  /** for serialization */
  private static final long serialVersionUID = 5702974949137433141L;

  /**
   * Returns a string describing this filter.
   * 
   * @return a description of the filter suitable for displaying in the
   *         explorer/experimenter gui
   */
  public abstract String globalInfo();

  /**
   * resets the filter, i.e., m_NewBatch to true and m_FirstBatchDone to false.
   * 
   * @see #m_NewBatch
   * @see #m_FirstBatchDone
   */
  protected void reset() {
    m_NewBatch = true;
    m_FirstBatchDone = false;
  }

  /**
   * returns true if the output format is immediately available after the input
   * format has been set and not only after all the data has been seen (see
   * batchFinished())
   * 
   * @return true if the output format is immediately available
   * @see #batchFinished()
   * @see #setInputFormat(Instances)
   */
  protected abstract boolean hasImmediateOutputFormat();

  /**
   * Determines the output format based on the input format and returns this. In
   * case the output format cannot be returned immediately, i.e.,
   * immediateOutputFormat() returns false, then this method will be called from
   * batchFinished().
   * 
   * @param inputFormat the input format to base the output format on
   * @return the output format
   * @throws Exception in case the determination goes wrong
   * @see #hasImmediateOutputFormat()
   * @see #batchFinished()
   */
  protected abstract Instances determineOutputFormat(Instances inputFormat)
    throws Exception;

  /**
   * Processes the given data (may change the provided dataset) and returns the
   * modified version. This method is called in batchFinished().
   * 
   * @param instances the data to process
   * @return the modified data
   * @throws Exception in case the processing goes wrong
   * @see #batchFinished()
   */
  protected abstract Instances process(Instances instances) throws Exception;

  /**
   * Sets the format of the input instances. Also resets the state of the filter
   * (this reset doesn't affect the options).
   * 
   * @param instanceInfo an Instances object containing the input instance
   *          structure (any instances contained in the object are ignored -
   *          only the structure is required).
   * @return true if the outputFormat may be collected immediately
   * @see #reset()
   */
  @Override
  public boolean setInputFormat(Instances instanceInfo) throws Exception {
    super.setInputFormat(instanceInfo);

    reset();

    if (hasImmediateOutputFormat()) {
      setOutputFormat(determineOutputFormat(instanceInfo));
    }

    return hasImmediateOutputFormat();
  }
}
