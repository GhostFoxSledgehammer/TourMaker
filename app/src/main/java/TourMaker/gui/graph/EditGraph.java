// License: GPL. For details, see LICENSE file.
package TourMaker.gui.graph;

import TourMaker.data.AssetType;
import TourMaker.data.Node;
import TourMaker.data.Point;
import TourMaker.data.Project;
import TourMaker.util.AssetUtil;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
public class EditGraph extends JPanel {

  int x = 700, y = 700;
  private BufferedImage image = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
  private final Project project;
  private final int seqIndex;
  private final int nodeIndex;
  private final Node node;

  public EditGraph(Project project, int seqIndex, int nodeIndex) {
    this.project = project;
    this.seqIndex = seqIndex;
    this.nodeIndex = nodeIndex;
    node = project.getSequences().get(seqIndex).getNodes().get(nodeIndex);
    setPreferredSize(new Dimension(x, y));
    try {
      image = ImageIO.read(AssetUtil.getAssset(project, AssetType.Image, project.getMinimapImage()));
      x = image.getWidth();
      y = image.getHeight();
    } catch (IOException ex) {
      Logger.getLogger(EditGraph.class.getName()).log(Level.SEVERE, null, ex);
    }
    setPreferredSize(new Dimension(x, y));
    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getX() <= x && e.getY() <= y) {
          Point point = new Point((e.getX() * 1d) / x, (e.getY() * 1d) / y);
          node.setPos(point);
          repaint();
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getX() <= x && e.getY() <= y) {
          Point point = new Point((e.getX() * 1d) / x, (e.getY() * 1d) / y);
          node.setPos(point);
          repaint();
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }
    });

    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if (e.getX() <= x && e.getY() <= y) {
          Point point = new Point((e.getX() * 1d) / x, (e.getY() * 1d) / y);
          node.setPos(point);
          repaint();
        }
      }

      @Override
      public void mouseMoved(MouseEvent e) {
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    render(g);
    g.dispose();
  }

  private void render(Graphics g) {
    g.drawImage(image, 0, 0, this);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    project.getSequences().forEach(seq -> {
      Node prev = null;
      g2d.setColor(Color.BLUE);
      for (Node node : seq.getNodes()) {
        if (prev == null) {
          prev = node;
        } else {
          g2d.draw(new Line2D.Double(x * prev.getPosition().x, y * prev.getPosition().y, x * node.getPosition().x, y * node.getPosition().y));
        }
        g2d.fill(new Rectangle2D.Double(x * node.getPosition().x, y * node.getPosition().y, 5, 5));
      }
      g2d.setStroke(new BasicStroke(3));
      g2d.setColor(Color.CYAN);
    });

    g2d.setColor(Color.GREEN);
    g2d.fill(new Rectangle2D.Double(x * node.getPosition().x, y * node.getPosition().y, 10, 10));

  }
}
