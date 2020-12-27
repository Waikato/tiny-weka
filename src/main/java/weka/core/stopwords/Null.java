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
 * Null.java
 * Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 */

package weka.core.stopwords;

/**
 <!-- globalinfo-start -->
 * Dummy stopwords scheme, always returns false.
 * <p/>
 <!-- globalinfo-end -->
 *
 <!-- options-start -->
 * Valid options are: <p/>
 *
 * <pre> -D
 *  If set, stopword scheme is run in debug mode and
 *  may output additional info to the console</pre>
 *
 <!-- options-end -->
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 10978 $
 */
public class Null
  extends AbstractStopwords {

  /** for serialization. */
  private static final long serialVersionUID = -3319681866579617385L;

  /**
   * Returns a string describing the stopwords scheme.
   *
   * @return a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return
	"Dummy stopwords scheme, always returns false.";
  }

  /**
   * Returns true if the given string is a stop word.
   *
   * @param word the word to test
   * @return always false
   */
  @Override
  protected boolean is(String word) {
    return false;
  }
}
