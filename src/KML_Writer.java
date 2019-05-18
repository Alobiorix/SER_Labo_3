import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.transform.JDOMSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class KML_Writer {

    private final Document document = new Document();


    KML_Writer(){

        try {

            Element kml = new Element("kml", "http://www.opengis.net/kml/2.2");
            Element placemark = new Element("Placemark");
            kml.addContent(placemark);
            document.addContent(kml);




            StreamResult sortie = new StreamResult(new File("src/geo_kml.kml"));
            JDOMSource source = new JDOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, sortie);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}