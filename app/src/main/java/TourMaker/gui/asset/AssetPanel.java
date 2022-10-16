// License: GPL. For details, see LICENSE file.
package TourMaker.gui.asset;

import TourMaker.data.AssetType;
import TourMaker.gui.boilerplate.ColoredButton;
import TourMaker.gui.boilerplate.WrapLayout;
import TourMaker.util.Resource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
public abstract class AssetPanel extends JPanel {

  protected AssetPanel() {
    setBackground(Color.orange);
    setLayout(new WrapLayout(WrapLayout.LEFT));
    setMinimumSize(new Dimension(500, 500));
  }

  public abstract AssetType assetType();

  public abstract void importAsset();

  public abstract void addAsset(File asset);

  protected abstract void deleteAssetCard(AssetCard assetCard);

  public void removeAsset(File asset) {
    for (Component comp : getComponents()) {
      if (comp instanceof AssetCard) {
        if (((AssetCard) comp).asset.equals(asset)) {
          deleteAssetCard((AssetCard) comp);
          return;
        }
      }
    }
  }

  protected abstract class AssetCard extends JPanel {

    protected final JButton viewButton;

    protected final JButton deleteButton;
    protected File asset;

    public AssetCard() {
//      setBackground(Color.PINK);
      setPreferredSize(new Dimension(200, 200));
      viewButton = new ColoredButton(Color.GREEN, true);
      viewButton.setIcon(Resource.getIcon("icons/view.svg").derive(32, 32));
      viewButton.addActionListener(e -> {
        viewAsset();
      });
      deleteButton = new ColoredButton(Color.RED, true);
      deleteButton.setIcon(Resource.getIcon("icons/delete.svg").derive(32, 32));
      deleteButton.addActionListener(e -> {
        deleteAsset();
      });
    }

    public abstract void deleteAsset();

    public abstract void viewAsset();

    protected abstract void showAssetGUI();

    protected abstract void setAsset(File asset);
  }

}
