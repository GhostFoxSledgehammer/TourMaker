// License: GPL. For details, see LICENSE file.
package TourMaker.gui.asset;

import TourMaker.AppState;
import TourMaker.data.AssetType;
import TourMaker.gui.boilerplate.NewCard;
import TourMaker.util.AssetUtil;
import TourMaker.util.IOImport;
import TourMaker.util.Resource;
import TourMaker.util.Utils;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Kishan Tripathi
 */
public class PdfPanel extends AssetPanel {

  private FlatSVGIcon thumbnail = null;

  public PdfPanel() {
    addNewCard();
  }

  @Override
  public AssetType assetType() {
    return AssetType.Pdf;
  }

  private AssetCard addAssetCard() {
    ImageCard card = new ImageCard();
    int count = this.getComponentCount();
    add(card, Math.min(0, count - 1));
    revalidate();
    return card;
  }

  private NewCard addNewCard() {
    NewCard newCard = new NewCard() {
      @Override
      public void onClick() {
        importAsset();
      }
    };
    add(newCard);
    revalidate();
    return newCard;
  }

  @Override
  protected void deleteAssetCard(AssetCard assetCard) {
    remove(assetCard);
    revalidate();
  }

  @Override
  public void addAsset(File asset) {
    AssetCard card = addAssetCard();
    card.setAsset(asset);
  }

  @Override
  public void importAsset() {
    List<FileFilter> filterList = Utils.getFilterList(assetType());
    JFileChooser jfc = new JFileChooser();
    jfc.setAcceptAllFileFilterUsed(false);
    jfc.setMultiSelectionEnabled(false);
    for (FileFilter filter : filterList) {
      jfc.setFileFilter(filter);
    }
    int response = jfc.showDialog(this, "Import Pdf");
    if (response == JFileChooser.APPROVE_OPTION) {
      File imageFile = jfc.getSelectedFile();
      File copiedFile = IOImport.copyFile(imageFile, assetType());
      if (copiedFile != null) {
        AppState.fireAssetAdded(AssetType.Pdf, copiedFile);
      }
    }
  }

  private class ImageCard extends AssetCard {

    @Override
    public void deleteAsset() {
     AppState.fireAssetDeleted(AssetType.Pdf, asset);
      AssetUtil.deleteAsset(asset);
    }

    @Override
    public void viewAsset() {
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop desktop = Desktop.getDesktop();
          desktop.open(asset);
        } catch (IOException ex) {
        }
      }
    }

    @Override
    protected void showAssetGUI() {
      removeAll();
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.weightx = 1;
      gbc.weighty = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.gridwidth = 2;
      JLabel nameLabel = new JLabel(asset.getName());
      add(nameLabel, gbc);

      gbc.gridwidth = 2;
      gbc.gridy = 1;
      JLabel thumbnailLabel = new JLabel(thumbnail);
      add(thumbnailLabel, gbc);

      gbc.fill = GridBagConstraints.NONE;
      gbc.gridwidth = 1;
      gbc.gridy = 2;
      add(viewButton, gbc);
      gbc.gridx = 1;
      add(deleteButton, gbc);
      revalidate();
    }

    protected void setAsset(File asset) {
      this.asset = asset;
      thumbnail = Resource.getIcon("icons/pdf.svg").derive(150, 150);
      showAssetGUI();
    }

  }
}
