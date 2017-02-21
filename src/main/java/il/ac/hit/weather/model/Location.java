package il.ac.hit.weather.model;

/**
 * Created by galya on 12/30/2016.
 */
public class Location {
    private long id;
    private String city;
    private String country;

    public Location() {
    }


    public Location(long id, String city, String country) {
        this.id = id;
        this.city = city;
        this.country = country;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
