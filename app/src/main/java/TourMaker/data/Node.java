// License: GPL. For details, see LICENSE file.
package TourMaker.data;

import TourMaker.hotspot.Hotspot;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class Node {

  private Point pos;
  private final String imageName;
  private final List<Hotspot> hotspots;

  public Node(Point p, String name) {
    pos = p;
    imageName = name;
    hotspots = new ArrayList();
  }
  
  public Point getPosition() {
    return pos;
  }
  
  public String getImageName() {
    return imageName;
  }
  
  public List<Hotspot> getHostspots() {
    return hotspots;
  }
}
