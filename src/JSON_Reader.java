import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_Reader {

    private static List<Country> countries = new ArrayList<>();

    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();

        //On essaye de lire le fichier geojson
        try (FileReader reader = new FileReader("countries.geojson")) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //On parcourt les features
            JSONArray features = (JSONArray) jsonObject.get("features");
            for (Object feature : features) {
                JSONObject properties = (JSONObject) ((JSONObject) feature).get("properties");

                //On récupère le nom et le code du pays
                Country country = new Country((String) properties.get("ISO_A3"), (String) properties.get("ADMIN"));

                //On récupère l'objet geometry + coordonnées
                JSONObject geometry = (JSONObject) ((JSONObject) feature).get("geometry");

                //On récupère le type de polygone du pays
                String geometryType =  (String) geometry.get("type");

                //on récupère le tableau de coordonnées
                JSONArray coordinatesTab = (JSONArray) geometry.get("coordinates");

                //on récupère les coordonnées
                JSONArray coordinates;
                List<Coordinate> c = new ArrayList<>();

                if(geometryType.equals("MultiPolygon"))
                {
                    //On parcourt le tableau de coordonnées
                    for(Object tabCoo : coordinatesTab)
                    {
                        //Pour chaque tableau, on créé un polygone différent.
                        coordinates = (JSONArray) ((JSONArray)tabCoo).get(0);
                        CountryPolygon cp = new CountryPolygon();
                        c = new ArrayList<>();

                        //On parcourt les coordonnées et on les ajoute dans
                        //leur polygone correspondant.
                        for(Object coordinate : coordinates)
                        {
                            Coordinate newCoordinate =
                                    new Coordinate( Double.toString((double)((JSONArray)coordinate).get(0)),
                                            Double.toString((double)((JSONArray)coordinate).get(1)));

                            c.add(newCoordinate);
                            cp = new CountryPolygon(c);
                        }
                        //on ajoute le polygone au pays correspondant.
                        country.addPolygon(cp);
                    }
                }
                //Concerne : les pays qui ne contiennent pas plusieurs polygones
                else
                {
                    coordinates = (JSONArray) coordinatesTab.get(0);

                    //On parcourt simplement les coordonnées et on les met dans un polygone.
                    for(Object coo : coordinates)
                    {
                        Coordinate newCoordinate = new Coordinate(Double.toString((double)((JSONArray)coo).get(0)),
                                (Double.toString((double)((JSONArray)coo).get(1))));

                        c.add(newCoordinate);
                    }

                    CountryPolygon cp = new CountryPolygon(c);
                    country.addPolygon(cp);
                }
                countries.add(country);
            }

            //on affiche les différents pays avec leurs données.
            for(Country country : countries)
            {
                System.out.println(country);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        KML_Writer kml_writer = new KML_Writer((ArrayList<Country>)countries);
    }
}
