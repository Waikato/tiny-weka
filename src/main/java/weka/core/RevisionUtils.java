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
 * RevisionUtils.java
 * Copyright (C) 2008-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

/**
 * Contains utility functions for handling revisions.
 * 
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 8034 $
 */
public class RevisionUtils {
  
  /**
   * Enumeration of source control types.
   * 
   * @author  fracpete (fracpete at waikato dot ac dot nz)
   * @version $Revision: 8034 $
   */
  public enum Type {
    /** unknown source control revision. */
    UNKNOWN,
    /** CVS. */
    CVS,
    /** Subversion. */
    SUBVERSION;
  }
  
  /**
   * Extracts the revision string returned by the RevisionHandler.
   * 
   * @param handler	the RevisionHandler to get the revision for
   * @return		the actual revision string
   */
  public static String extract(RevisionHandler handler) {
    return extract(handler.getRevision());
  }
  
  /**
   * Extracts the revision string.
   * 
   * @param s		the string to get the revision string from
   * @return		the actual revision string
   */
  public static String extract(String s) {
    String	result;
    
    result = s;
    result = result.replaceAll("\\$Revision:", "");
    result = result.replaceAll("\\$", "");
    result = result.replaceAll(" ", "");
    
    return result;
  }
  
  /**
   * Determines the type of a (sanitized) revision string returned by the 
   * RevisionHandler.
   * 
   * @param handler	the RevisionHandler to determine the type for
   * @return		the type, UNKNOWN if it cannot be determined
   */
  public static Type getType(RevisionHandler handler) {
    return getType(extract(handler));
  }
  
  /**
   * Determines the type of a (sanitized) revision string. Use extract(String)
   * method to extract the revision first before calling this method.
   * 
   * @param revision	the revision to get the type for
   * @return		the type, UNKNOWN if it cannot be determined
   * @see #extract(String)
   */
  public static Type getType(String revision) {
    Type	result;
    String[]	parts;
    int		i;
    
    result = Type.UNKNOWN;
    
    // subversion?
    try {
      Integer.parseInt(revision);
      result = Type.SUBVERSION;
    }
    catch (Exception e) {
      // ignored
    }
    
    // CVS?
    if (result == Type.UNKNOWN) {
      try {
	// must contain at least ONE dot
	if (revision.indexOf('.') == -1)
	  throw new Exception("invalid CVS revision - not dots!");
	
	parts = revision.split("\\.");

	// must consist of at least TWO parts/integers
	if (parts.length < 2)
	  throw new Exception("invalid CVS revision - not enough parts separated by dots!");

	// try parsing parts of revision string - must be ALL integers
	for (i = 0; i < parts.length; i++)
	  Integer.parseInt(parts[i]);
	
	result = Type.CVS;
      }
      catch (Exception e) {
	// ignored
      }
    }
    
    return result;
  }
  
  /**
   * For testing only. The first parameter must be a classname of a
   * class implementing the weka.core.RevisionHandler interface.
   * 
   * @param args	the commandline arguments
   * @throws Exception	if something goes wrong
   */
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("\nUsage: " + RevisionUtils.class.getName() + " <classname>\n");
      System.exit(1);
    }
    
    RevisionHandler handler = (RevisionHandler) Class.forName(args[0]).newInstance();
    System.out.println("Type: " + getType(handler));
    System.out.println("Revision: " + extract(handler));
  }
}
