package gwc.com.trouvaille.Layout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import gwc.com.trouvaille.Entity.Venue;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

import gwc.com.trouvaille.Adapter.EventsAdapter;
import gwc.com.trouvaille.Entity.Event;
import gwc.com.trouvaille.Entity.User;
import gwc.com.trouvaille.R;
import gwc.com.trouvaille.client.Client;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home:
                    Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.search:
                    Intent intent = new Intent(MainActivity.this, UserPreferenceActivity.class);
                    MainActivity.this.getApplicationContext().startActivity(intent);
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
    };

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private Client client;
    private ArrayList<Event> events;
    private EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("User Pref:", User.getInstance().getUserPreference().toString());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button searchButton = findViewById(R.id.start_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Venue> venues = startSearch();
                if(venues!=null){
                    Intent intent = new Intent(v.getContext(), ResultsActivity.class);
                    intent.putExtra("venues", venues);
                    v.getContext().startActivity(intent);
                }
            }
        });

//        setView();
    }

//    public void setView(){
//        recyclerView = findViewById(R.id.recyclerView);
//
//        events = new ArrayList<Event>();
//        eventsAdapter = new EventsAdapter(this, events);
//
//        linearLayoutManager = new LinearLayoutManager(this);
//
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//        recyclerView.setAdapter(eventsAdapter);
//
//        getEvents();
//
//    }

    public ArrayList<Venue> startSearch(){
        String url = Client.getInstance(this.getApplicationContext()).getUrl();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Venues Suggested:", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response: ", error.toString());
            }
        });
        return null;
    }

//    public void getEvents(){
//
//        String url = Client.getInstance(this.getApplicationContext()).getUrl();
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/events.json", null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for(int i=0; i<response.length(); i++){
//                    try {
//                        JSONObject jsonEvent = response.getJSONObject(i);
//
//                        JSONObject jsonVenue = (JSONObject) jsonEvent.get("venue");
//                        Venue venue = new Venue(jsonVenue.getString("id"),jsonVenue.getString("name"),jsonVenue.getString("address_1"),
//                                jsonVenue.getString("city"),jsonVenue.getString("state"),jsonVenue.getString("country"),
//                                jsonVenue.getString("location"),((JSONArray)jsonVenue.get("images")).get(0).toString());
//                        Event event = new Event(jsonEvent.getString("id"), jsonEvent.getString("title"),
//                                jsonEvent.getString("description"),venue,null);
//                        events.add(event);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                eventsAdapter.notifyDataSetChanged();
//                Log.e("Rest response: ", response.toString());
//                Log.e("ArrayList:", ""+events.size());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Rest response: ", error.toString());
//            }
//        });
//
//        Client.getInstance(this.getApplicationContext()).addToRequestQueue(request);
//    }

}
