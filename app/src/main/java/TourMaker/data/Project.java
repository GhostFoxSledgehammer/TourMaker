// License: GPL. For details, see LICENSE file.
package TourMaker.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class Project {
  private final String dir;
  private String description;
  private final List<Sequence> sequences;
  private String minimapImage = new String();

  public String getMinimapImage() {
    return minimapImage;
  }
  
  public Project(String dir) {
    this(dir, "Virtual Tour");
  }
  
  public Project(String directory, String des) {
    dir = directory;
    description = des;
    sequences = new ArrayList();
  }
  
  public String dir() {
    return dir;
  }
  
  public String description() {
    return description;
  }
  
  public List<Sequence> getSequences() {
    return sequences;
  }

  public void addSequence(Sequence seq) {
    sequences.add(seq);
  }

  public void setDescription(String text) {
    this.description = text;
  }

  public void setMinimap(String imageName) {
    this.minimapImage = imageName;
  }
}
