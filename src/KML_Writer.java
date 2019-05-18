import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.transform.JDOMSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class KML_Writer {

    private final Document document = new Document();

    KML_Writer(ArrayList<Country> countries){
        try {
            Element kml = new Element("Document");
            document.addContent(kml);
            Element style = new Element("Style");
            style.setAttribute("id","style");
            Element lineStyle = new Element("LineStyle");
            style.addContent(lineStyle);
            lineStyle.addContent(new Element("color").setText("ffffffff"));
            lineStyle.addContent(new Element("width").setText("3"));
            Element polyStyle = new Element("PolyStyle");
            style.addContent(polyStyle);
            polyStyle.addContent(new Element("fill").setText("0"));
            kml.addContent(style);

            for(Country country : countries) {
                Element placemark = new Element("Placemark");
                placemark.addContent(new Element("name").setText(country.getName()));
                placemark.addContent(new Element("code").setText(country.getCode()));
                placemark.addContent(new Element("styleUrl").setText("style"));
                Element multiGeometry = new Element("MultiGeometry");
                placemark.addContent(multiGeometry);

                for (int i = 0; i < country.getBoundary().size(); i++) {
                    Element polygon = new Element("Polygon");
                    multiGeometry.addContent(polygon);
                    Element outerBoundaryIs = new Element("outerBoundaryIs");
                    polygon.addContent(outerBoundaryIs);
                    Element linearRing = new Element("LinearRing");
                    outerBoundaryIs.addContent(linearRing);

                    Element coordinates = new Element("coordinates");
                    linearRing.addContent(coordinates);
                    for (int j = 0; j < country.getBoundary().get(i).getPolygone().size(); j++) {
                        coordinates.addContent(country.getBoundary().get(i).getPolygone().get(j).toString());
                    }
                }
                kml.addContent(placemark);
            }

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