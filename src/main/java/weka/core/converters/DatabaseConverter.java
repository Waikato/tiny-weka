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
 *    DatabaseConverter.java
 *    Copyright (C) 2004-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.converters;

/**
 * Marker interface for a loader/saver that uses a database
 *
 * @author <a href="mailto:mhall@cs.waikato.ac.nz">Mark Hall</a>
 * @version $Revision 1.0 $
 */
public interface DatabaseConverter {
    
    public String getUrl();
    
    public String getUser();
    
    public void setUrl(String url);
    
    public void setUser(String user);
    
    public void setPassword(String password);

}
