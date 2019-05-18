import java.util.ArrayList;
import java.util.List;

public class Geometry {

    private String type;
    private List<Coordinate> coordinates = new ArrayList<Coordinate>();

    Geometry(String type, ArrayList<Coordinate> coordinates){
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}
