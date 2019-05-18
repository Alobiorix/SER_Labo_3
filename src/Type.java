public class Type
{

    private String type;
    private Properties properties;

    Type(String type, Properties properties){
        this.type = type;
        this.properties = properties;
    }
    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }
}
