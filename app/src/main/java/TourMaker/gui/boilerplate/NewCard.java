// License: GPL. For details, see LICENSE file.
package TourMaker.gui.boilerplate;

import TourMaker.util.Resource;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Kishan Tripathi
 */
public abstract class NewCard extends JPanel {

  protected final JButton addButton;

  public NewCard() {
    this(200, 200);
  }

  public NewCard(int width, int height) {
    addButton = new ColoredButton(Color.GRAY, true);
    addButton.setIcon(Resource.getIcon("icons/plus.svg").derive(width, height));
    addButton.addActionListener(e -> {
      onClick();
    });

    add(addButton);
  }
  
  public abstract void onClick();

}
