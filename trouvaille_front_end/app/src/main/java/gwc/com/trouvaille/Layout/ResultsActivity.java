package gwc.com.trouvaille.Layout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Adapter.VenueAdapter;
import gwc.com.trouvaille.Entity.Venue;
import gwc.com.trouvaille.R;
import gwc.com.trouvaille.client.Client;

public class ResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Venue> venues = new ArrayList<>();
    private VenueAdapter venuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        startSearch();
        setResultsView();



    }

    private void setResultsView() {
        recyclerView = findViewById(R.id.venues_recyclerView);
        venuesAdapter = new VenueAdapter(this,venues);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(venuesAdapter);

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
                        venuesAdapter.notifyDataSetChanged();
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
