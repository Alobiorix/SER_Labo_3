import java.util.ArrayList;
import java.util.List;

public class CountryPolygon {
    private List<Coordinate> polygone;

    public CountryPolygon()
    {
        polygone = new ArrayList<>();
    }

    public CountryPolygon(List<Coordinate> newPolygone)
    {
        polygone = newPolygone;
    }

    public List<Coordinate> getPolygone() {
        return polygone;
    }

    public int getPolygoneSize()
    {
        return polygone.size();
    }

    public void addCoordinate(Coordinate newCoordinate) {
            polygone.add(newCoordinate);

    }
}
