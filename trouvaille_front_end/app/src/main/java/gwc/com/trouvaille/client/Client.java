package gwc.com.trouvaille.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Entity.Event;
import gwc.com.trouvaille.Entity.Venue;

public class Client {

    private RequestQueue queue;
    private String url;

    public Client(Context context){
        this.url = "https://trouvaille-rails.herokuapp.com";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
    }

    public ArrayList<Event> getEvents(){
        final ArrayList<Event> events = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/events.json", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonEvent = response.getJSONObject(i);
                        JSONObject jsonVenue = (JSONObject) jsonEvent.get("venue");
                        Venue venue = new Venue(jsonVenue.getString("id"),jsonVenue.getString("name"),jsonVenue.getString("address_1"),
                                jsonVenue.getString("city"),jsonVenue.getString("state"),jsonVenue.getString("country"),
                                jsonVenue.getString("location"),((String[])jsonVenue.get("images"))[0]);
                        Event event = new Event(jsonEvent.getString("id"), jsonEvent.getString("title"),
                                jsonEvent.getString("description"),venue,null);
                        events.add(event);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.e("Rest response: ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response: ", error.toString());
            }
        });
        return events.size()>0?events:null;
    }
}
