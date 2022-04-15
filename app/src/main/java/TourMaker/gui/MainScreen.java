// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Kishan Tripathi
 */
public class MainScreen extends JFrame {

  private static MainScreen instance;
  private final JMenuBar menuBar;
  static final String APP_NAME = "Tour Maker";
  private final MainPanel mainPanel;

  private MainScreen() {
    setTitle(APP_NAME);
    menuBar = new MainMenuBar();
    setJMenuBar(menuBar);
    
    setLayout(new BorderLayout());
    
    mainPanel = new MainPanel();
    add(mainPanel, BorderLayout.CENTER);
    
    pack();
    setVisible(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static MainScreen getInstance() {
    if (instance == null) {
      instance = new MainScreen();
    }
    return instance;
  }
  
  public MainPanel mainPanel() {
    return mainPanel;
  }
}
