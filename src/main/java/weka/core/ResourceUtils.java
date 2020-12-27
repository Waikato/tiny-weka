/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * ResourceUtils.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package weka.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Helper for resources.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class ResourceUtils {

  /**
   * Creates a new instance of an object given it's class name and (optional)
   * arguments to pass to it's setOptions method. If the object implements
   * OptionHandler and the options parameter is non-null, the object will have
   * it's options set. Example use:
   * <p>
   *
   * <code> <pre>
   * String classifierName = Utils.getOption('W', options);
   * Classifier c = (Classifier)Utils.forName(Classifier.class,
   *                                          classifierName,
   *                                          options);
   * setClassifier(c);
   * </pre></code>
   *
   * @param classType the class that the instantiated object should be
   *          assignable to -- an exception is thrown if this is not the case
   * @param className the fully qualified class name of the object
   * @param options an array of options suitable for passing to setOptions. May
   *          be null. Any options accepted by the object will be removed from
   *          the array.
   * @return the newly created object, ready for use (if it is an array, it will
   *         have size zero).
   * @exception Exception if the class name is invalid, or if the class is not
   *              assignable to the desired class type, or the options supplied
   *              are not acceptable to the object
   */
  public static Object forName(Class<?> classType, String className,
    String[] options) throws Exception {

    if (System.getProperty("weka.test.maventest", "").equalsIgnoreCase("true")) {
      return forNameNoSchemeMatch(classType, className, options);
    }

    Class<?> c = null;
    try {
      // c = Class.forName(className);
      c = Class.forName(className);
    } catch (Exception ex) {
      throw new Exception("Can't find a permissible class called: " + className);
    }

    Object o = c.newInstance();
    if ((o instanceof OptionHandler) && (options != null)) {
      ((OptionHandler) o).setOptions(options);
      Utils.checkForRemainingOptions(options);
    }
    return o;
  }

  /**
   * Creates a new instance of an object given it's class name and (optional)
   * arguments to pass to it's setOptions method. If the object implements
   * OptionHandler and the options parameter is non-null, the object will have
   * it's options set. Example use:
   * <p>
   *
   * <code> <pre>
   * String classifierName = Utils.getOption('W', options);
   * Classifier c = (Classifier)Utils.forName(Classifier.class,
   *                                          classifierName,
   *                                          options);
   * setClassifier(c);
   * </pre></code>
   *
   * @param classType the class that the instantiated object should be
   *          assignable to -- an exception is thrown if this is not the case
   * @param className the fully qualified class name of the object
   * @param options an array of options suitable for passing to setOptions. May
   *          be null. Any options accepted by the object will be removed from
   *          the array.
   * @return the newly created object, ready for use.
   * @exception Exception if the class name is invalid, or if the class is not
   *              assignable to the desired class type, or the options supplied
   *              are not acceptable to the object
   */
  @SuppressWarnings("unchecked")
  protected static Object forNameNoSchemeMatch(Class classType,
    String className, String[] options) throws Exception {

    Class c = null;
    try {
      // c = Class.forName(className);
      c = Class.forName(className);
    } catch (Exception ex) {
      throw new Exception("Can't find class called: " + className);
    }
    if (classType != null && !classType.isAssignableFrom(c)) {
      throw new Exception(classType.getName() + " is not assignable from "
        + className);
    }
    Object o = c.newInstance();
    if ((o instanceof OptionHandler) && (options != null)) {
      ((OptionHandler) o).setOptions(options);
      Utils.checkForRemainingOptions(options);
    }
    return o;
  }

  /**
   * Reads properties that inherit from three locations. Properties are first
   * defined in the system resource location (i.e. in the CLASSPATH). These
   * default properties must exist. Properties optionally defined in the user
   * properties location (WekaPackageManager.PROPERTIES_DIR) override default
   * settings. Properties defined in the current directory (optional) override
   * all these settings.
   *
   * @param resourceName the location of the resource that should be loaded.
   *          e.g.: "weka/core/Utils.props". (The use of hardcoded forward
   *          slashes here is OK - see jdk1.1/docs/guide/misc/resources.html)
   *          This routine will also look for the file (in this case)
   *          "Utils.props" in the users home directory and the current
   *          directory.
   * @return the Properties
   * @exception Exception if no default properties are defined, or if an error
   *              occurs reading the properties files.
   */
  public static Properties readProperties(String resourceName) throws Exception {
    Utils utils = new Utils();
    return readProperties(resourceName, utils.getClass().getClassLoader());
  }

  /**
   * Reads properties that inherit from three locations. Properties are first
   * defined in the system resource location (i.e. in the CLASSPATH). These
   * default properties must exist. Properties optionally defined in the user
   * properties location (WekaPackageManager.PROPERTIES_DIR) override default
   * settings. Properties defined in the current directory (optional) override
   * all these settings.
   *
   * @param resourceName the location of the resource that should be loaded.
   *          e.g.: "weka/core/Utils.props". (The use of hardcoded forward
   *          slashes here is OK - see jdk1.1/docs/guide/misc/resources.html)
   *          This routine will also look for the file (in this case)
   *          "Utils.props" in the users home directory and the current
   *          directory.
   * @param loader the class loader to use when loading properties
   * @return the Properties
   * @exception Exception if no default properties are defined, or if an error
   *              occurs reading the properties files.
   */
  public static Properties readProperties(String resourceName,
    ClassLoader loader) throws Exception {

    Properties defaultProps = new Properties();
    try {
      // Apparently hardcoded slashes are OK here
      // jdk1.1/docs/guide/misc/resources.html
      Enumeration<URL> urls = loader.getResources(resourceName);
      boolean first = true;
      while (urls.hasMoreElements()) {
        URL url = urls.nextElement();
        if (first) {
          defaultProps.load(url.openStream());
          first = false;
        } else {
          Properties props = new Properties(defaultProps);
          props.load(url.openStream());
          defaultProps = props;
        }
      }
    } catch (Exception ex) {
      System.err.println("Warning, unable to load properties file(s) from "
        + "system resource (Utils.java): " + resourceName);
    }

    // Hardcoded slash is OK here
    // eg: see jdk1.1/docs/guide/misc/resources.html
    int slInd = resourceName.lastIndexOf('/');
    if (slInd != -1) {
      resourceName = resourceName.substring(slInd + 1);
    }

    // Allow a properties file in the WekaPackageManager.PROPERTIES_DIR to
    // override
    Properties userProps = new Properties(defaultProps);
    File propFile =
      new File(System.getProperty("user.home") + File.separator
        + resourceName);

    if (propFile.exists()) {
      try {
        userProps.load(new FileInputStream(propFile));
      } catch (Exception ex) {
        throw new Exception("Problem reading user properties: " + propFile);
      }
    }

    // Allow a properties file in the current directory to override
    Properties localProps = new Properties(userProps);
    propFile = new File(resourceName);
    if (propFile.exists()) {
      try {
        localProps.load(new FileInputStream(propFile));
      } catch (Exception ex) {
        throw new Exception("Problem reading local properties: " + propFile);
      }
    }

    return new EnvironmentProperties(localProps);
  }
}
