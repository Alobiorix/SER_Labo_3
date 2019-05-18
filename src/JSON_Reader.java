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

    private static List<Country> countries = new ArrayList<Country>();

    public static void main(String[] args) {

        //JSON parser object pour lire le fichier
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("countries.geojson")) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //On parcourt les features
            JSONArray features = (JSONArray) jsonObject.get("features");

            for (Object feature : features) {
                JSONObject properties = (JSONObject) ((JSONObject) feature).get("properties");

                //On récupère le nom et le code du pays.
                String  countryName = (String) properties.get("ADMIN"),
                        countryIso = (String) properties.get("ISO_A3");

                Country country = new Country(countryIso, countryName);

                //On récupère l'objet geometry + coordonnées
                JSONObject geometry = (JSONObject) ((JSONObject) feature).get("geometry");

                //On récupère le type de polygone du pays
                String geometryType =  (String) geometry.get("type");

                //on récupère le tableau de coordonnées
                JSONArray coordinatesTab = (JSONArray) geometry.get("coordinates");

                //on récupère les coordonnées
                JSONArray coordinates = (JSONArray) coordinatesTab.get(0);

                if(geometryType.equals("Polygon"))
                {
                    List<Coordinate> c = new ArrayList<>();

                    for(Object coo : coordinates)
                    {
                        Coordinate newCoordinate = new Coordinate(Double.toString((double)((JSONArray)coo).get(0)),
                        (Double.toString((double)((JSONArray)coo).get(1))));

                        c.add(newCoordinate);
                    }

                    CountryPolygon cp = new CountryPolygon(c);
                    country.addPolygon(cp);
                }
                else //multipolygon
                {

                }
                countries.add(country);

            }
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
    }
}
