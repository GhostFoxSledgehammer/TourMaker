// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.AssetType;
import TourMaker.data.Project;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class AssetTracker implements AppStateListener, AssetListener{
  
  private final  HashMap<Enum, List<String>> assets;
  private AssetTracker() {
    assets = new HashMap();
    for(AssetType type : AssetType.values()) {
      assets.put(type, new ArrayList<>());
    }
    AppState.addAssetListener(this);
    AppState.addStateListener(this);
  }
  
  public static AssetTracker getInstance() {
    return AssetrackerHolder.INSTANCE;
  }
  
  private static class AssetrackerHolder {

    private static final AssetTracker INSTANCE = new AssetTracker();
  }
  
  @Override
  public void assetAdded(AssetType type, File Asset) {
    assets.get(type).add(Asset.getName());
  }

  @Override
  public void stateChanged(Project newProject) {
    for (AssetType type : AssetType.values()) {
      assets.get(type).clear();
    }
  }

  @Override
  public void assetRemoved(AssetType type, File Asset) {
    assets.get(type).remove(Asset.getName());
  }
  
  public List<String> getAssets(AssetType type) {
    return assets.get(type);
  }
}
