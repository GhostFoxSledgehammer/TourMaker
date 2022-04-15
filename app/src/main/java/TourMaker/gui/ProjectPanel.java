// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Kishan Tripathi
 */
public class ProjectPanel extends JPanel {

  private final JTabbedPane leftPane;
  private final JPanel panoramaPanel;
  private final JPanel hotspotPanel;
  private final JPanel imagePanel;
  private final JPanel videoPanel;
  private final JPanel audioPanel;
  private final JPanel pdfPanel;
  private final JPanel settingPanel;

  public ProjectPanel() {
    leftPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

    panoramaPanel = new JPanel();
    hotspotPanel = new JPanel();
    imagePanel = new JPanel();
    videoPanel = new JPanel();
    audioPanel = new JPanel();
    pdfPanel = new JPanel();
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
  }
}
