// License: GPL. For details, see LICENSE file.
package TourMaker.data;

/**
 *
 * @author Kishan Tripathi
 */
public class Project {
  String dir;
  String description;
  
  public Project(String dir) {
    this(dir, "Virtual Tour");
  }
  
  public Project(String directory, String des) {
    dir = directory;
    description = des;
  }
}
