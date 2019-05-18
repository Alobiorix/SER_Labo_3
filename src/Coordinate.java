public class Coordinate {
    private String x;
    private String y;

    public Coordinate(String newX, String newY)
    {
        x = newX;
        y = newY;
    }

    public String toString()
    {
        return x + ", " + y;
    }
}
