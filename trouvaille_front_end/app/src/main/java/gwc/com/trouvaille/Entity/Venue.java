package gwc.com.trouvaille.Entity;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class Venue implements Serializable {

    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String location;
    private String image;
    private boolean hasImage;

    public Venue(){};

    public Venue(int id, String name, String address, String city, String state, String country, String location, String image){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.location = location;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        hasImage = true;
    }

    public boolean hasImage(){return hasImage;}
}
