// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import TourMaker.AppState;
import TourMaker.AppStateListener;
import TourMaker.AssetListener;
import TourMaker.data.AssetType;
import TourMaker.data.Project;
import TourMaker.gui.asset.AssetPanel;
import TourMaker.gui.asset.AudioPanel;
import TourMaker.gui.asset.ImagePanel;
import TourMaker.gui.asset.PanoPanel;
import TourMaker.gui.asset.PdfPanel;
import TourMaker.gui.asset.VideoPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Kishan Tripathi
 */
public class ProjectPanel extends JPanel implements AssetListener{

  private final JTabbedPane leftPane;
  private final AssetPanel panoramaPanel;
  private final JPanel hotspotPanel;
  private final AssetPanel imagePanel;
  private final AssetPanel videoPanel;
  private final AssetPanel audioPanel;
  private final AssetPanel pdfPanel;
  private final JPanel settingPanel;

  public ProjectPanel() {
    leftPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

    panoramaPanel = new PanoPanel();
    hotspotPanel = new JPanel();
    imagePanel = new ImagePanel();
    videoPanel = new VideoPanel();
    audioPanel = new AudioPanel();
    pdfPanel = new PdfPanel();
    settingPanel = new JPanel();

    leftPane.addTab("Panoramas", panoramaPanel);
    leftPane.addTab("Hotspots", hotspotPanel);
    leftPane.addTab("Image Assets", imagePanel);
    leftPane.addTab("Video Files", videoPanel);
    leftPane.addTab("Audio Files", audioPanel);
    leftPane.addTab("PDF Documents", pdfPanel);
    leftPane.addTab("Settings", settingPanel);

    setPreferredSize(new Dimension(600, 600));

    setLayout(new BorderLayout());
    add(leftPane, BorderLayout.WEST);
    AppState.addAssetListener(this);
  }

  public AssetPanel getPanel(AssetType type) {
    switch (type) {
      case Panorama:
        return panoramaPanel;
      case Audio:
        return audioPanel;
      case Image:
        return imagePanel;
      case Pdf:
        return pdfPanel;
      case Video:
        return videoPanel;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public void assetAdded(AssetType type, File Asset) {
    getPanel(type).addAsset(Asset);
  }
}
