public class Properties {

    private String admin;
    private String iso_a3;
    private Geometry geometry;

    Properties(String admin, String iso_a3, Geometry geometry){
        this.admin = admin;
        this.iso_a3 = iso_a3;
        this.geometry = geometry;
    }
    public String getAdmin() {
        return admin;
    }

    public String getIso_a3() {
        return iso_a3;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
