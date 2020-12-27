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

/**
 * InheritanceUtils.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package weka.core;

/**
 * Helper class for inheritance related operations.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class InheritanceUtils {

  /**
   * Checks whether the "otherclass" is a subclass of the given "superclass".
   *
   * @param superclass the superclass to check against
   * @param otherclass this class is checked whether it is a subclass of the the
   *          superclass
   * @return TRUE if "otherclass" is a true subclass
   */
  public static boolean isSubclass(String superclass, String otherclass) {
    try {
      // return isSubclass(Class.forName(superclass), Class.forName(otherclass));
      return isSubclass(Class.forName(superclass), Class.forName(otherclass));
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks whether the "otherclass" is a subclass of the given "superclass".
   *
   * @param superclass the superclass to check against
   * @param otherclass this class is checked whether it is a subclass of the the
   *          superclass
   * @return TRUE if "otherclass" is a true subclass
   */
  public static boolean isSubclass(Class<?> superclass, Class<?> otherclass) {
    Class<?> currentclass;
    boolean result;

    result = false;
    currentclass = otherclass;
    do {
      result = currentclass.equals(superclass);

      // topmost class reached?
      if (currentclass.equals(Object.class)) {
        break;
      }

      if (!result) {
        currentclass = currentclass.getSuperclass();
      }
    } while (!result);

    return result;
  }

  /**
   * Checks whether the given class implements the given interface.
   *
   * @param intf the interface to look for in the given class
   * @param cls the class to check for the interface
   * @return TRUE if the class contains the interface
   */
  public static boolean hasInterface(String intf, String cls) {
    try {
      // return hasInterface(Class.forName(intf), Class.forName(cls));
      return hasInterface(Class.forName(intf), Class.forName(cls));
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks whether the given class implements the given interface.
   *
   * @param intf the interface to look for in the given class
   * @param cls the class to check for the interface
   * @return TRUE if the class contains the interface
   */
  public static boolean hasInterface(Class<?> intf, Class<?> cls) {
    Class<?>[] intfs;
    int i;
    boolean result;
    Class<?> currentclass;

    result = false;
    currentclass = cls;
    do {
      // check all the interfaces, this class implements
      intfs = currentclass.getInterfaces();
      for (i = 0; i < intfs.length; i++) {
        if (intfs[i].equals(intf)) {
          result = true;
          break;
        }
      }

      // get parent class
      if (!result) {
        currentclass = currentclass.getSuperclass();

        // topmost class reached or no superclass?
        if ((currentclass == null) || (currentclass.equals(Object.class))) {
          break;
        }
      }
    } while (!result);

    return result;
  }
}
