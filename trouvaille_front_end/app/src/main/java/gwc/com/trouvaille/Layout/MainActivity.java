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

import gwc.com.trouvaille.Entity.Tag;
import gwc.com.trouvaille.Entity.Venue;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Adapter.EventsAdapter;
import gwc.com.trouvaille.Entity.Event;
import gwc.com.trouvaille.Entity.User;
import gwc.com.trouvaille.R;
import gwc.com.trouvaille.client.Client;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Venue> venues = new ArrayList<>();
    private Client client;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startSearch();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button searchButton = findViewById(R.id.start_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(venues!=null){
                    Intent intent = new Intent(v.getContext(), ResultsActivity.class);
                    v.getContext().startActivity(intent);
                }
            }
        });

    }


    public void startSearch(){
        String url = Client.getInstance(this.getApplicationContext()).getUrl();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/events/venue_recommendation.json?user_id=1", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject venueJson = response.getJSONObject(i);
                        Venue venue = new Venue();
                        venue.setName(venueJson.getString("name"));
                        if(venueJson.getJSONArray("images").length()>0) {
                            venue.setImage(venueJson.getJSONArray("images").getString(0));
                        }
                        venues.add(venue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response: ", error.toString());
            }
        });
        Client.getInstance(this.getApplicationContext()).addToRequestQueue(request);
    }

}
