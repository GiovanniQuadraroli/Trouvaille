package gwc.com.trouvaille;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Entity.Event;
import gwc.com.trouvaille.Entity.Venue;
import gwc.com.trouvaille.client.Client;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private Client client;
    private ArrayList<Event> events;
    private EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        events = new ArrayList<Event>();
        eventsAdapter = new EventsAdapter(this, events);

        linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(eventsAdapter);

        getEvents();

        recyclerView = findViewById(R.id.recyclerView);

    }

    public void getEvents(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://trouvaille-rails.herokuapp.com";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/events.json", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonEvent = response.getJSONObject(i);

                        JSONObject jsonVenue = (JSONObject) jsonEvent.get("venue");
                        Venue venue = new Venue(jsonVenue.getString("id"),jsonVenue.getString("name"),jsonVenue.getString("address_1"),
                                jsonVenue.getString("city"),jsonVenue.getString("state"),jsonVenue.getString("country"),
                                jsonVenue.getString("location"),((JSONArray)jsonVenue.get("images")).get(0).toString());
                        Event event = new Event(jsonEvent.getString("id"), jsonEvent.getString("title"),
                                jsonEvent.getString("description"),venue,null);
                        events.add(event);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                eventsAdapter.notifyDataSetChanged();
                Log.e("Rest response: ", response.toString());
                Log.e("ArrayList:", ""+events.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response: ", error.toString());
            }
        });
        requestQueue.add(request);
    }



    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationView bottomView = findViewById(R.id.bottom_navigation);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        Toast.makeText(MainActivity.this, "Search clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "Account clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
