// License: GPL. For details, see LICENSE file.
package TourMaker.gui.card;

import TourMaker.data.AssetType;
import TourMaker.gui.boilerplate.ColoredButton;
import TourMaker.util.ImageUtil;
import TourMaker.util.Resource;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Kishan Tripathi
 */
public abstract class AssetCard {

  private final JButton viewButton;

  private final JButton deleteButton;

  private final JButton addButton;

  public AssetCard() {
    viewButton = new JButton();
    deleteButton = new JButton();
    addButton = new ColoredButton(Color.GREEN, true);
    addButton.setIcon(new ImageIcon(Resource.plusImage));
    addButton.addActionListener(e -> {

    });
  }

  public abstract AssetType assetType();

  public abstract void addNew();

  public abstract void deleteAsset();

  public abstract void viewAsset();
}
