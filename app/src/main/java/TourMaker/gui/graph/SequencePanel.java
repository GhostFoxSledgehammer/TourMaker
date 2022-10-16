// License: GPL. For details, see LICENSE file.
package TourMaker.gui.graph;

import TourMaker.AssetTracker;
import TourMaker.data.AssetType;
import TourMaker.data.Node;
import TourMaker.data.Point;
import TourMaker.data.Project;
import TourMaker.data.Sequence;
import TourMaker.gui.MainScreen;
import TourMaker.gui.boilerplate.ColoredButton;
import TourMaker.gui.boilerplate.NewCard;
import TourMaker.hotspot.Hotspot;
import TourMaker.util.Resource;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Kishan Tripathi
 */
public class SequencePanel extends JPanel {

  private final Project project;
  private final NewCard newCard;
  private final List<SequenceCard> cards = new ArrayList();

  public SequencePanel(Project project) {
    this.project = project;

    newCard = new NewCard(50, 50) {
      @Override
      public void onClick() {
        addNewSequence();
      }
    };
//    setBackground(Color.orange);
    setLayout(new GridBagLayout());
//    setLayout(new WrapLayout(WrapLayout.LEFT));
//    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setMinimumSize(new Dimension(500, 500));

    if (project != null) {
      project.getSequences().forEach(seq -> cards.add(new SequenceCard(seq)));
    }

    refresh();
  }

  private void refresh() {
    removeAll();

    add(newCard, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
      new Insets(10, 10, 10, 10), 0, 0));

    for (int i = 0; i < cards.size(); i++) {
      SequenceCard sqc = cards.get(i);
      sqc.refresh();
      add(new JScrollPane(sqc), new GridBagConstraints(0, i + 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
        new Insets(10, 10, 10, 10), 0, 0));
    }
    revalidate();
    repaint();
  }

  private void deleteSequence(SequenceCard sequenceCard) {
    project.getSequences().remove(sequenceCard.seq);
    cards.remove(sequenceCard);
    SequencePanel.this.refresh();
  }

  private void addNewSequence() {
    Sequence seq = new Sequence("Sequence " + (project.getSequences().size() + 1));
    project.getSequences().add(seq);
    SequenceCard sequenceCard = new SequenceCard(seq);
    cards.add(sequenceCard);
    SequencePanel.this.refresh();

  }

  private class SequenceCard extends JPanel {

    private final JPanel root;
    private final JTextField seqNameLabel;
    private final NewCard newCard;
    private final Sequence seq;
    private final JButton deleteButton;

    private SequenceCard(Sequence seq) {
      this.seq = seq;
      deleteButton = new ColoredButton(Color.RED);
      deleteButton.setText("Delete Sequence");
      deleteButton.setIcon(Resource.getIcon("icons/delete.svg").derive(32, 32));
      deleteButton.addActionListener(e -> {
        deleteSequence(this);
      });
      root = new JPanel();
      root.setLayout(new BoxLayout(root, BoxLayout.X_AXIS));
      newCard = new NewCard(250, 250) {
        @Override
        public void onClick() {
          addNewNode();
        }
      };
      root.add(newCard);

      seq.getNodes().forEach(node -> root.add(new NodeCard(node)));
      seqNameLabel = new JTextField(seq.getName());

//      setBackground(Color.GREEN.darker());
      JPanel jp = new JPanel();
      jp.add(seqNameLabel);
      jp.add(deleteButton);

      JPanel jp2 = new JPanel();
      jp2.setLayout(new BorderLayout());
      jp2.add(root, BorderLayout.SOUTH);
      jp2.add(jp, BorderLayout.WEST);
      add(jp2);
      setMinimumSize(new Dimension(300, 500));
    }

    private void addNewNode() {
      Node node = new Node(new Point(0, 0), "Node " + (seq.size() + 1), "Image name");
      seq.addNode(node);
      NodeCard nodeCard = new NodeCard(node);
      root.add(nodeCard);
      SequencePanel.this.refresh();
    }

    private void refresh() {
      for (Component comp : root.getComponents()) {
        if (comp instanceof NodeCard) {
          ((NodeCard) comp).refresh();
        }
      }
      revalidate();
    }

    private void deleteNode(NodeCard nodeCard) {
      seq.removeNode(nodeCard.node);
      root.remove(nodeCard);
      SequencePanel.this.refresh();
    }

    private class NodeCard extends JPanel {

      private final JTextField nameField;
      private final JLabel positionLabel;
      private final JLabel positionValueLabel;
      private final JLabel imageLabel;
      private final JLabel hotspotLabel;

      private final JComboBox<String> selectImage;

      private final JList<Hotspot> hotspotList;

      private final JButton editPositioButton;
      private final JButton deleteButton;

      private final Node node;

      private NodeCard(Node node) {
        this.node = node;
        deleteButton = new ColoredButton(Color.RED);
        deleteButton.setText("Delete Node");
        deleteButton.setIcon(Resource.getIcon("icons/delete.svg").derive(32, 32));
        deleteButton.addActionListener(e -> {
          deleteNode(this);
        });
        nameField = new JTextField(20);
        nameField.setMinimumSize(nameField.getPreferredSize());
        positionLabel = new JLabel("Position :");
        positionValueLabel = new JLabel("(0.5,0.5)");
        imageLabel = new JLabel("Image :");
        hotspotLabel = new JLabel("Hotspots :");
        selectImage = new JComboBox<>();

        hotspotList = new JList<>();

        editPositioButton = new JButton("Edit");
        editPositioButton.addActionListener(e -> {
          JOptionPane.showConfirmDialog(MainScreen.getInstance(), new EditGraph(project, project.getSequences().indexOf(SequenceCard.this.seq), seq.getNodes().indexOf(node)), "Edit Node Position", JOptionPane.OK_OPTION);
          refresh();
        });

        nameField.getDocument().addDocumentListener(new DocumentListener() {
          public void changedUpdate(DocumentEvent e) {
            warn();
          }

          public void removeUpdate(DocumentEvent e) {
            warn();
          }

          public void insertUpdate(DocumentEvent e) {
            warn();
          }
        });
        refresh();

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.anchor = GridBagConstraints.CENTER;

//      gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(nameField, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridy++;
        add(positionLabel, gbc);
        gbc.gridy++;
        add(imageLabel, gbc);
        gbc.gridy++;
        add(hotspotLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        add(deleteButton, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx++;
        gbc.gridy = 1;
        add(positionValueLabel, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(selectImage, gbc);
        gbc.gridy++;
        add(hotspotList, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx++;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(editPositioButton, gbc);

        setMinimumSize(new Dimension(300, 500));
//        setBackground(Color.red);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        setBorder(raisedbevel);
      }

      private void refresh() {
        nameField.setText(node.getName());
        positionValueLabel.setText(node.getPosition().toString());
        selectImage.removeAllItems();
        for (String pano : AssetTracker.getInstance().getAssets(AssetType.Panorama)) {
          selectImage.addItem(pano);
        }
      }

      public void warn() {
        node.setName(nameField.getText());
      }
    }
  }

  public static void main(String args[]) {
    JFrame jd = new JFrame();
    jd.setLayout(new BorderLayout());
    jd.add(new SequencePanel(new Project(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)), BorderLayout.CENTER);
//jd.add(new PanoPanel(),BorderLayout.CENTER);
    jd.setVisible(true);
    jd.setMinimumSize(new Dimension(500, 500));
    jd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
