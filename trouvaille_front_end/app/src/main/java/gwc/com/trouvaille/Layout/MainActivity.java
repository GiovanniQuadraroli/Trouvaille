package gwc.com.trouvaille.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Entity.Venue;
import gwc.com.trouvaille.R;
import gwc.com.trouvaille.client.Client;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
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
                    Toast.makeText(MainActivity.this, "Search clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.account:
                    Toast.makeText(MainActivity.this, "Account clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.settings:
                    Intent intent = new Intent(MainActivity.this, UserPreferenceActivity.class);
                    MainActivity.this.getApplicationContext().startActivity(intent);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button searchButton = findViewById(R.id.start_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ResultsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

}
