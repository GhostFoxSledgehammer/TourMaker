// License: GPL. For details, see LICENSE file.
package TourMaker.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
public final class MainPanel extends JPanel {

  private final WelcomeScreen welcomeScreen;
  private final ProjectPanel projectPanel;

  public MainPanel() {
    welcomeScreen = new WelcomeScreen();
    projectPanel = new ProjectPanel();
    displayWelcomeScreen();
  }

  public void displayWelcomeScreen() {
    removeAll();
    setLayout(new BorderLayout());
    add(welcomeScreen, BorderLayout.CENTER);
    revalidate();
  }

  public void displayProjectScreen() {
    removeAll();
    setLayout(new BorderLayout());
    add(projectPanel, BorderLayout.CENTER);
    revalidate();
  }
}
