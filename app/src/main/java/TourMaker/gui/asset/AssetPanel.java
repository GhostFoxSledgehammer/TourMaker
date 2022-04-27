// License: GPL. For details, see LICENSE file.
package TourMaker.gui.asset;

import TourMaker.data.AssetType;
import TourMaker.gui.boilerplate.ColoredButton;
import TourMaker.util.ImageUtil;
import TourMaker.util.Resource;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
public class AssetPanel extends JPanel {

  protected File asset;

  protected AssetPanel() {
    setMaximumSize(new Dimension(200,200));
  }

  protected abstract class AssetCard extends JPanel {

    protected final JButton viewButton;

    protected final JButton deleteButton;

    protected final JButton addButton;

    public AssetCard() {
      viewButton = new ColoredButton(Color.GREEN, true);
      viewButton.setIcon(Resource.getIcon("icons/view.svg").derive(32,32));
      viewButton.addActionListener(e -> {
        viewAsset();
      });
      deleteButton = new ColoredButton(Color.RED, true);
      deleteButton.setIcon(Resource.getIcon("icons/delete.svg").derive(32,32));
      deleteButton.addActionListener(e -> {
        deleteAsset();
      });
      addButton = new ColoredButton(Color.GRAY, true);
      addButton.setIcon(Resource.getIcon("icons/plus.svg").derive(200,200));
      addButton.addActionListener(e -> {
        addAsset();
      });

      add(addButton);
    }

    public abstract AssetType assetType();

    public abstract void addAsset();

    public abstract void deleteAsset();

    public abstract void viewAsset();

    protected abstract void showAssetGUI();
  }
}
