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

        requestTags();

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

    }


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

    private void requestTags() {
        String url = Client.getInstance(this).getUrl();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + "/users/1/preference.json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
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
