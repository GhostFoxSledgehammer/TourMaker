// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import TourMaker.AppState;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

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

  public static boolean deleteAsset(File asset) {
    if (asset == null) {
      throw new IllegalArgumentException("File cannot be null");
    }
    if (!asset.exists()) {
      return false;
    }
    if (!IOUtil.isFileInDirectory(asset, new File(AppState.getCurrentProject().dir()))) {
      Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, "File not in project directory : {0}", asset.getAbsolutePath());
      return false;
    }
    return asset.delete();
  }
}
