// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Kishan Tripathi
 */
public class Resource {

  public static final Image viewImage;
  public static final Image plusImage;

  static {
    Image temp = null;
    Image plus = null;
    try {
       temp = ImageIO.read(IOUtil.getFileFromResourceAsFile("icons/view.png"));
       plus = ImageIO.read(IOUtil.getFileFromResourceAsFile("icons/plus.png"));
    } catch (IOException ex) {
      Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      viewImage = temp;
      plusImage = plus;
    }
  }

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private Resource() {
  }
}
