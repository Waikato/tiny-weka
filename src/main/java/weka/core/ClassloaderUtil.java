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
 *    ClassloaderUtil.java
 *    Copyright (C) 2008-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Utility class that can add jar files to the classpath dynamically.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}org
 * @version $Revision: 10203 $
 */
public class ClassloaderUtil implements RevisionHandler {

  // Parameters
  private static final Class<?>[] parameters = new Class[] { URL.class };

  /**
   * Add file to CLASSPATH
   * 
   * @param s File name
   * @throws IOException if something goes wrong when adding a file
   */
  public static void addFile(String s) throws IOException {
    File f = new File(s);
    addFile(f);
  }

  /**
   * Add file to CLASSPATH
   * 
   * @param f File object
   * @throws IOException if something goes wrong when adding a file
   */
  public static void addFile(File f) throws IOException {
    addURL(f.toURI().toURL());
  }

  /**
   * Add URL to CLASSPATH
   * 
   * @param u URL
   * @throws IOException if something goes wrong when adding a url
   */
  public static void addURL(URL u) throws IOException {
    ClassloaderUtil clu = new ClassloaderUtil();
    // URLClassLoader sysLoader = (URLClassLoader)
    // ClassLoader.getSystemClassLoader();
    URLClassLoader sysLoader = (URLClassLoader) clu.getClass().getClassLoader();
    URL urls[] = sysLoader.getURLs();
    for (URL url : urls) {
      if (url.toString().toLowerCase().equals(u.toString().toLowerCase())) {
        System.err.println("URL " + u + " is already in the CLASSPATH");
        return;
      }
    }
    Class<?> sysclass = URLClassLoader.class;
    try {
      Method method = sysclass.getDeclaredMethod("addURL", parameters);
      method.setAccessible(true);
      method.invoke(sysLoader, new Object[] { u });
    } catch (Throwable t) {
      t.printStackTrace();
      throw new IOException("Error, could not add URL to system classloader");
    }
  }

  /**
   * Returns the revision string.
   * 
   * @return the revision
   */
  @Override
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 10203 $");
  }
}
