package gwc.com.trouvaille.Entity;

import org.json.JSONObject;

public class User {

    private static User user;

    private JSONObject userPreference;

    private User(){};

    public void setUserPreference(JSONObject preference){
        userPreference = preference;
    }

    public JSONObject getUserPreference() {
        return userPreference;
    }

    public static synchronized User getInstance(){
        if(user==null){
            user = new User();
        }
        return user;
    }

}
