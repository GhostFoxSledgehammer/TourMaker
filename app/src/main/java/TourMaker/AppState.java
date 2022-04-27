// License: GPL. For details, see LICENSE file.
package TourMaker;

import TourMaker.data.Project;
import TourMaker.gui.CreateProject;
import TourMaker.gui.MainScreen;
import TourMaker.util.IOExport;
import TourMaker.util.IOImport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Kishan Tripathi
 */
public class AppState {

  private static Project curProject;

  private static final Integer lock = 1;

  public static void save() {
    synchronized (lock) {
      if (curProject != null) {
        IOExport.saveProject(curProject);
      }
    }
  }

  public static void openProject() {
    Project project = null;
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fc.setMultiSelectionEnabled(false);
    int res = fc.showDialog(MainScreen.getInstance(), "Open");
    if (res == JFileChooser.APPROVE_OPTION) {
      File projectFile = fc.getSelectedFile();
      project = IOImport.openProject(projectFile);
      if (project == null) {
        String message = "Error opening project file : " + projectFile.getAbsolutePath();
        JOptionPane.showMessageDialog(MainScreen.getInstance(), message, "IO Error",
        JOptionPane.ERROR_MESSAGE);
        return;
      }
      setCurrentProject(project);
    }
  }

  public static void newProject() {
    JDialog jd = new JDialog(MainScreen.getInstance(), "New Project", true);
    CreateProject createProject = new CreateProject(jd);
    jd.add(createProject);
    jd.pack();
    jd.setLocationRelativeTo(MainScreen.getInstance());
    jd.setVisible(true);
  }

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
    synchronized (lock) {
      curProject = project;
      notifyListeners();
      IOExport.saveProject(project);
    }
  }

  public static void addListener(AppStateListener listener) {
    listeners.add(listener);
  }

  public static boolean removeListener(AppStateListener listener) {
    return listeners.remove(listener);
  }

  private static void notifyListeners() {
    synchronized (lock) {
      for (AppStateListener listener : listeners) {
        listener.stateChanged(curProject);
      }
    }
  }
}
