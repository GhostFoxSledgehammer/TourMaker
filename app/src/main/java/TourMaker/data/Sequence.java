// License: GPL. For details, see LICENSE file.
package TourMaker.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishan Tripathi
 */
public class Sequence {
  private String name;
  private final List<Node> nodes;
  
  public Sequence(String name) {
    this.name = name;
    nodes = new ArrayList<>();
  }

  public String getName() {
    return name;
  }
  
  public void setName(String newName) {
    name = newName;
  }

  public List<Node> getNodes() {
    return nodes;
  }
  
  public void addNode(Node node) {
    nodes.add(node);
  }

  public int size() {
    return nodes.size();
  }

  public void removeNode(Node node) {
    nodes.remove(node);
  }
}
