// License: GPL. For details, see LICENSE file.
package TourMaker.gui.asset;

import TourMaker.data.AssetType;
import TourMaker.gui.MainScreen;
import TourMaker.util.AssetUtil;
import TourMaker.util.Utils;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Kishan Tripathi
 */
public class ImagePanel extends AssetPanel {

  private ImageIcon thumbnail = new ImageIcon("Thumbnail");

  public ImagePanel() {
    addAssetCard();
  }

  private void addAssetCard() {
    add(new ImageCard());
    revalidate();
  }

  private void deleteAssetCard(AssetCard assetCard) {
    remove(assetCard);
    revalidate();
  }

  private class ImageCard extends AssetCard {

    @Override
    public AssetType assetType() {
      return AssetType.Image;
    }

    @Override
    public void addAsset() {
      List<FileFilter> filterList = Utils.getFilterList(assetType());
      JFileChooser jfc = new JFileChooser();
      jfc.setAcceptAllFileFilterUsed(false);
      jfc.setMultiSelectionEnabled(false);
      for (FileFilter filter : filterList) {
        jfc.setFileFilter(filter);
      }
      int response = jfc.showDialog(this, "Import Images");
      if (response == JFileChooser.APPROVE_OPTION) {
        File imageFile = jfc.getSelectedFile();
        File copiedFile = AssetUtil.copyFile(imageFile, AssetType.Image);
        if (copiedFile != null) {
          asset = copiedFile;
          BufferedImage temp = null;
          try {
            temp = Thumbnails.of(copiedFile).size(150, 150).asBufferedImage();
          } catch (IOException ex) {
            String message = "Error creating thumbnail : " + copiedFile.getAbsolutePath();
            JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
            JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
            if (temp != null) {
              thumbnail = new ImageIcon(temp);
              showAssetGUI();
              addAssetCard();
            }
          }
        }
      }
    }

    @Override
    public void deleteAsset() {
    }

    @Override
    public void viewAsset() {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = (int) (screenSize.getWidth() * 0.5);
      int hieght = (int) (screenSize.getWidth() * 0.5);
      try {
        BufferedImage temp = Thumbnails.of(asset).size(width, hieght).asBufferedImage();
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle(asset.getName());
        dialog.add(new JLabel(new ImageIcon(temp)));
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
      } catch (IOException ex) {
        String message = "Error creating thumbnail : " + asset.getAbsolutePath();
        JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
        JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    protected void showAssetGUI() {
      removeAll();
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

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

  }
}