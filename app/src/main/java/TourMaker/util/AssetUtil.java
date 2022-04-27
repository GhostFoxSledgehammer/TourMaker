// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import TourMaker.AppState;
import TourMaker.data.AssetType;
import TourMaker.gui.MainScreen;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Kishan Tripathi
 */
public class AssetUtil {

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private AssetUtil() {
  }

  public static File copyFile(File orignal, AssetType type) {
    String projectDir = AppState.getCurrentProject().dir();
    String assetDir = projectDir.concat("/").concat(type.name());
    String fileName = orignal.getName();
    String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);
    String fileExt = FilenameUtils.getExtension(fileName);
    int index = 0;
    File destination = new File(assetDir.concat("/").concat(fileName));
    while (destination.exists()) {
      destination = new File(assetDir.concat("/").concat(fileNameWithOutExt).concat(index + ".").concat(fileExt));
      index++;
    }
    try {
      FileUtils.copyFile(orignal, destination);
      return destination;
    } catch (IOException e) {
      String message = "Error copying file : " + orignal.getAbsolutePath();
      JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
      JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }
}
