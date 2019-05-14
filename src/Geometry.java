import java.util.ArrayList;
import java.util.List;

public class Geometry {

    private String type;
    private List<Double> coordinates = new ArrayList();

    Geometry(String type, ArrayList<Double> coordinates){
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
}
