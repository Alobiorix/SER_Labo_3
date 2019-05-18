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
import java.util.ArrayList;
import java.util.List;

public class KML_Writer {

    private final Document document = new Document();

    KML_Writer(ArrayList<Country> countries){
        try {
            Element kml = new Element("kml", "http://www.opengis.net/kml/2.2");
            document.setRootElement(kml);
            Element placemark = new Element("Placemark");
            placemark.addContent(new Element("name").setText(countries.iterator().next().getName()));
            placemark.addContent(new Element("code").setText(countries.iterator().next().getCode()));
            Element polygon = new Element("Polygon");
            placemark.addContent(polygon);
            polygon.addContent(new Element("altitudeMode").setText("relativeToGround"));
            Element outerBoundaryIs = new Element("outerBoundaryIs");
            polygon.addContent(outerBoundaryIs);
            Element linearRing = new Element("LinearRing");
            outerBoundaryIs.addContent(linearRing);
            Element coordinates = new Element("coordinates");
            linearRing.addContent(coordinates);
            for(int i = 0; i < countries.iterator().next().getBoundary().size(); i++) {
                for(int j = 0; j < countries.iterator().next().getBoundary().get(i).getPolygone().size(); j++){
                    coordinates.addContent(countries.iterator().next().getBoundary().get(i).getPolygone().get(j).toString());
                }

            }


            kml.addContent(placemark);

            /*
            <extrude>1</extrude>
            <outerBoundaryIs>
                <LinearRing>
                    <coordinates>
                        -77.05788457660967,38.87253259892824,100
                        -77.05465973756702,38.87291016281703,100
                        -77.05315536854791,38.87053267794386,100
                        -77.05552622493516,38.868757801256,100
                        -77.05844056290393,38.86996206506943,100
                        -77.05788457660967,38.87253259892824,100
                      </coordinates>
                    </LinearRing>
                  </outerBoundaryIs>*/



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