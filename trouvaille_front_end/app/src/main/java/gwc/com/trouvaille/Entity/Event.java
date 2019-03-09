package gwc.com.trouvaille.Entity;

import android.media.Image;

import java.io.Serializable;

public class Event implements Serializable {

    private String id;
    private String title;
    private String description;
    private Venue venue;
    private User owner;

    public Event(String id, String title, String description, Venue venue, User owner){
        this.id = id;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


}
