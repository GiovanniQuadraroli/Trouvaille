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

    private static Client client;
    private static Context context;
    private RequestQueue queue;

    public String getUrl() {
        return url;
    }

    private String url = "https://trouvaille-rails.herokuapp.com/api/v1";

    private Client(Context context){
        this.context = context;
        queue = getRequestQueue();
    }

    public static synchronized Client getInstance(Context context){
        if(client==null){
            client = new Client(context);
        }
        return client;
    }

    public RequestQueue getRequestQueue(){
        if(queue==null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }


    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

}
