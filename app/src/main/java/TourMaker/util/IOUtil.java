// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kishan Tripathi
 */
public class IOUtil {

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private IOUtil() {
  }

  public static InputStream getFileFromResourceAsStream(String fileName) {
    Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    ClassLoader classLoader = currentClass.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);
    return inputStream;
  }

  public static File getFileFromResourceAsFile(String fileName) {
    Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    ClassLoader classLoader = currentClass.getClassLoader();
    URL resource = classLoader.getResource(fileName);
    File file = null;
    try {
      file = new File(resource.toURI());
    } catch (URISyntaxException ex) {
      Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, "Error Opening File : " + fileName);
    }
    return file;
  }
}
