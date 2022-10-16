// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import TourMaker.AppState;
import TourMaker.AssetListener;
import TourMaker.AssetTracker;
import TourMaker.data.AssetType;
import TourMaker.gui.boilerplate.ColoredButton;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Kishan Tripathi
 */
public class SettingsPanel extends JPanel implements AssetListener {

  private final JLabel descriptionLabel;
  private final JTextField descriptionField;
  private final JButton saveButton;

  private final JLabel imageName;
  private final JComboBox<String> selectImage;

  public SettingsPanel() {
    descriptionLabel = new JLabel("Project Description");
    descriptionField = new JTextField(AppState.getCurrentProject().description(), 20);
    saveButton = new ColoredButton(Color.green);
    saveButton.setText("Save");
    saveButton.addActionListener(e -> {
      save();
    });

    imageName = new JLabel("MiniMap Image :");
    selectImage = new JComboBox<>();

    selectImage.removeAllItems();
    for (String pano : AssetTracker.getInstance().getAssets(AssetType.Panorama)) {
      selectImage.addItem(pano);
    }
    JPanel root = new JPanel();
    root.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.gridx = 0;
    gbc.gridy = 0;

    root.add(descriptionLabel, gbc);

    gbc.gridy = 1;
    root.add(descriptionField, gbc);

    gbc.gridy = 2;
    root.add(saveButton, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    root.add(imageName, gbc);
    gbc.gridy++;
    root.add(selectImage, gbc);

    add(root);
    AppState.addAssetListener(this);
  }

  @Override
  public void assetAdded(AssetType type, File Asset) {
    selectImage.removeAllItems();
    for (String pano : AssetTracker.getInstance().getAssets(AssetType.Image)) {
      selectImage.addItem(pano);
    }
  }

  @Override
  public void assetRemoved(AssetType type, File Asset) {
    selectImage.removeAllItems();
    for (String pano : AssetTracker.getInstance().getAssets(AssetType.Image)) {
      selectImage.addItem(pano);
    }
  }

  private void save() {
    AppState.getCurrentProject().setDescription(descriptionField.getText());
     AppState.getCurrentProject().setMinimap(selectImage.getSelectedItem().toString());
  }

}
