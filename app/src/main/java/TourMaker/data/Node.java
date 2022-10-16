// License: GPL. For details, see LICENSE file.
package TourMaker.data;

import TourMaker.hotspot.Hotspot;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class Node {

  private Point pos;
  private String name;
  private final String imageName;
  private final List<Hotspot> hotspots;

  public void setPos(Point pos) {
    this.pos = pos;
  }

  public Node(Point p, String name, String imageName) {
    pos = p;
    this.name = name;
    this.imageName = imageName;
    hotspots = new ArrayList();
  }
  
  public Point getPosition() {
    return pos;
  }
  
  public String getName() {
    return name;
  }
  
  public String getImageName() {
    return imageName;
  }
  
  public List<Hotspot> getHostspots() {
    return hotspots;
  }
  
  public void addHotspot(Hotspot hotspot) {
    hotspots.add(hotspot);
  }
  
  public void deleteHotspot(Hotspot hotspot) {
    hotspots.remove(hotspot);
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
