// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.Project;
import TourMaker.gui.MainPanel;
import TourMaker.gui.MainScreen;

/**
 *
 * @author Kishan Tripathi
 */
public class AppState {
  private static Project curProject;
  
  public static Project getCurrentProject() {
    return curProject;
  }
  
  
  public static void setCurrentProject(Project project) {
    curProject = project;
    MainScreen.getInstance().mainPanel().displayProjectScreen();
  }
}
