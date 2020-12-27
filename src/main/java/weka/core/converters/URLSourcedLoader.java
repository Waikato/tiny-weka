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
 *    URLSourcedLoader.java
 *    Copyright (C) 2006-2012 University of Waikato, Hamilton, New Zealand
 *
 */
package weka.core.converters;


/**
 * Interface to a loader that can load from a http url
 *
 * @author Mark Hall
 * @version $Revision 1.0 $
 */
public interface URLSourcedLoader {

  /**
   * Set the url to load from
   *
   * @param url the url to load from
   * @exception Exception if the url can't be set.
   */
  void setURL(String url) throws Exception;

  /**
   * Return the current url
   *
   * @return the current url
   */
  String retrieveURL();
}
