// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.Project;
import TourMaker.util.IOExport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class AppState {

  private static Project curProject;

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private AppState() {
  }
  private static final List<AppStateListener> listeners = new ArrayList();

  public static Project getCurrentProject() {
    return curProject;
  }

  public static void setCurrentProject(Project project) {
    curProject = project;
    notifyListeners();
    IOExport.saveProject(project);
  }

  public static void addListener(AppStateListener listener) {
    listeners.add(listener);
  }

  public static boolean removeListener(AppStateListener listener) {
    return listeners.remove(listener);
  }

  private static void notifyListeners() {
    for (AppStateListener listener : listeners) {
      listener.stateChanged(curProject);
    }
  }
}
