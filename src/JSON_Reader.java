import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import org.jdom2.*;


public class JSON_Reader {

    public static void main(String[] args) {

        //JSON parser object pour lire le fichier
        JSONParser jsonParser = new JSONParser();


        try (FileReader reader = new FileReader("countries.geojson")) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            Element xml_root = new Element((String) jsonObject.get("type"));
            JSONArray features = (JSONArray) jsonObject.get("features");
            Iterator<JSONObject> iterator = features.iterator();
            while (iterator.hasNext()){
                JSONObject inType = iterator.next();
                xml_root.addContent(new Element((String) inType.get("type")));
                //xml_root.addContent(new Element())
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
