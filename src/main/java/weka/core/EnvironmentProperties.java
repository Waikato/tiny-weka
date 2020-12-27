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
 * EnvironmentProperties.java
 * Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

import java.util.Properties;
import java.util.Set;

/**
 * Extends Properties to allow the value of a system property (if set) to
 * override that which has been loaded/set. This allows the user to override
 * from the command line any property that is loaded into a property file via
 * weka.core.Utils.readProperties().
 *
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 12232 $
 */
public class EnvironmentProperties extends Properties {

  protected transient Environment m_env = Environment.getSystemWide();

  public EnvironmentProperties() {
    super();
  }

  public EnvironmentProperties(Properties props) {
    super(props);
  }

  @Override
  public String getProperty(String key) {
    // allow system-wide properties to override
    if (m_env == null) {
      m_env = Environment.getSystemWide();
    }
    String result = m_env.getVariableValue(key);

    if (result == null) {
      result = super.getProperty(key);
    }

    return result;
  }
}
