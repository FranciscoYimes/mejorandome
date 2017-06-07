package mejorandome.mejorandome;

/**
 * Created by franciscoyimesinostroza on 06-06-17.
 */

public class UserLocations {

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public UserLocations(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserLocations(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.name = name;
        this.longitude = longitude;
    }

    double latitude;
    double longitude;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
