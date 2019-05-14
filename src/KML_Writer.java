import org.jdom2.Document;
import org.jdom2.transform.JDOMSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class KML_Writer {

    final Document document = new Document();
    /*document.addContent(xml_root);
    StreamResult sortie = new StreamResult(new File("src/geo_kml.kml"));
    JDOMSource source = new JDOMSource(document);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();

    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    transformer.transform(source, sortie);*/
}
