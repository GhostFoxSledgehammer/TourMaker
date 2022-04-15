// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Kishan Tripathi
 */
public class ImageUtil {

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private ImageUtil() {
  }

  public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
    Image img = icon.getImage();
    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImage);
  }
  
  public static Icon createIcon(Image img,  int width, int height) {
    Image resizedImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImage);
  }
}
