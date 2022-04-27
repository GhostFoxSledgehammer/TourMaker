// License: GPL. For details, see LICENSE file.
package TourMaker.util;

import TourMaker.data.Node;
import TourMaker.data.Project;
import TourMaker.data.Sequence;
import TourMaker.hotspot.Hotspot;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Kishan Tripathi
 */
public class IOExport {

  /**
   * Private no-args constructor for ensuring against instantiation.
   */
  private IOExport() {
  }

  public static void saveProject(Project project) {
    try {
      String dir = project.dir();
      File projectFile = new File(dir.concat("/tour.xml"));
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();

      Element tour = doc.createElement("tour");
      doc.appendChild(tour);

      Element name = doc.createElement("Name");
      name.appendChild(doc.createTextNode(project.description()));
      tour.appendChild(name);

      for (int i = 0; i < project.getSequences().size(); i++) {
        Sequence seq = project.getSequences().get(i);

        Element sequenceElement = doc.createElement("Sequence");
        sequenceElement.setAttribute("id", Integer.toString(i));

        Element sequenceNameElement = doc.createElement("Name");
        sequenceNameElement.appendChild(doc.createTextNode(seq.getName()));
        sequenceElement.appendChild(sequenceNameElement);

        for (int j = 0; j < seq.getNodes().size(); j++) {
          Node node = seq.getNodes().get(j);

          Element nodeElement = doc.createElement("Sequence");
          sequenceElement.setAttribute("id", Integer.toString(j));

          Element nodeNameElement = doc.createElement("Name");
          nodeNameElement.appendChild(doc.createTextNode(node.getImageName()));
          nodeElement.appendChild(nodeNameElement);

          Element nodePosElement = doc.createElement("Position");

          Element xCoordinateElement = doc.createElement("xCoordinate");
          xCoordinateElement.appendChild(doc.createTextNode(String.format("%.2f", node.getPosition().x)));
          nodePosElement.appendChild(xCoordinateElement);

          Element yCoordinateElement = doc.createElement("yCoordinate");
          yCoordinateElement.appendChild(doc.createTextNode(String.format("%.2f", node.getPosition().y)));
          nodePosElement.appendChild(yCoordinateElement);

          nodeElement.appendChild(nodePosElement);

          for (Hotspot hotspot : node.getHostspots()) {
            Element hotspotElement = doc.createElement("Hotspot");

            Element azimuthalElement = doc.createElement("Azimuthal");
            azimuthalElement.appendChild(doc.createTextNode(String.format("%.2f", hotspot.azimuthal)));
            hotspotElement.appendChild(azimuthalElement);

            Element polarElement = doc.createElement("Polar");
            polarElement.appendChild(doc.createTextNode(String.format("%.2f", hotspot.polar)));
            hotspotElement.appendChild(polarElement);

            Element typeElement = doc.createElement("Type");
            typeElement.appendChild(doc.createTextNode(hotspot.getType().name()));
            hotspotElement.appendChild(typeElement);

            Element valueElement = doc.createElement("Value");
            valueElement.appendChild(doc.createTextNode(hotspot.getValue()));
            hotspotElement.appendChild(valueElement);

            nodeElement.appendChild(hotspotElement);
          }
          sequenceElement.appendChild(nodeElement);
        }
        tour.appendChild(sequenceElement);
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);

      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

      StreamResult result = new StreamResult(projectFile);
      transformer.transform(source, result);
      Logger.getLogger(IOUtil.class.getName()).log(Level.INFO, "File Saved : ".concat(projectFile.getAbsolutePath()));
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException ex) {
      Logger.getLogger(IOExport.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
