package Datas;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String code;
    private String name;
    private List<CountryPolygon> boundary;

    public Country(String newCode, String newName) {
        code = newCode;
        name = newName;
        boundary = new ArrayList<>();
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public List<CountryPolygon> getBoundary()
    {
        return boundary;
    }

    public void addPolygon(CountryPolygon newPolygon)
    {
        boundary.add(newPolygon);
    }

    public String boundaryString(List<CountryPolygon> boundary)
    {
        String s = "";
        for(CountryPolygon cp : boundary)
        {
            s += "\t- " + cp.getPolygoneSize() + " coordinates" + "\n";
        }
        return s;
    }

    public String toString()
    {
        return "(" + code + ") "
                + name + "\n"
                + boundaryString(boundary);
    }
}