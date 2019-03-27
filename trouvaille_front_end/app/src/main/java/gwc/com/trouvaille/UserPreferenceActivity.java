package gwc.com.trouvaille;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.appyvet.materialrangebar.RangeBar;

import org.json.JSONException;
import org.json.JSONObject;

import gwc.com.trouvaille.client.Client;

public class UserPreferenceActivity extends AppCompatActivity {

    private Button sendUserPrefsButton;

    private int minD;
    private int maxD;
    private int minP;
    private int maxP;


    private RangeBar distanceBar;
    private RangeBar priceBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent = new Intent(UserPreferenceActivity.this, MainActivity.class);
                    UserPreferenceActivity.this.getApplicationContext().startActivity(intent);
                    break;
                case R.id.search:
                    Toast.makeText(UserPreferenceActivity.this, "Search clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.account:
                    Toast.makeText(UserPreferenceActivity.this, "Account clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.settings:
                    Toast.makeText(UserPreferenceActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

    private View.OnClickListener sendButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);

        distanceBar = (RangeBar) findViewById(R.id.distance_bar);
        priceBar = (RangeBar) findViewById(R.id.price_bar);


        distanceBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                Toast.makeText(getApplicationContext(), leftPinIndex + "-" + rightPinIndex, Toast.LENGTH_SHORT).show();
                minD = leftPinIndex;
                maxD = rightPinIndex;
            }
        });

        priceBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                Toast.makeText(getApplicationContext(), leftPinIndex + "-" + rightPinIndex, Toast.LENGTH_SHORT).show();
                minP = leftPinIndex;
                maxP = rightPinIndex;
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        sendUserPrefsButton = (Button) findViewById(R.id.send_user_pref);

        final String url = Client.getInstance(this.getApplicationContext()).getUrl()+"/users/954a1a48-8a05-4414-ac48-827b0dd3b2d3/preference";

        sendUserPrefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject body = new JSONObject();


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url, body, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Response", String.valueOf(response));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });

                Client.getInstance(v.getContext()).addToRequestQueue(request);
            }
        });
    }
}
