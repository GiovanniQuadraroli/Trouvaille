package gwc.com.trouvaille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.appyvet.materialrangebar.RangeBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Entity.Tag;
import gwc.com.trouvaille.Entity.User;
import gwc.com.trouvaille.client.Client;

public class UserPreferenceActivity extends AppCompatActivity {

    private Button sendUserPrefsButton;

    private int minD;
    private int maxD;
    private int minP;
    private int maxP;

    private ListView tagsListView;

    private ArrayList<Tag> selectedTag = new ArrayList<>();


    private RangeBar distanceBar;
    private RangeBar priceBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);

        ArrayList tags = new ArrayList();
        for(Tag.Tags t:Tag.Tags.values()){
            tags.add(new Tag(t));
        }
        ListAdapter tagsListAdapter = new ArrayAdapter<Tag>(this, android.R.layout.simple_list_item_1, tags);
        tagsListView = (ListView) findViewById(R.id.tags_list);
        tagsListView.setAdapter(tagsListAdapter);
        tagsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag tag = (Tag) parent.getItemAtPosition(position);
                if(selectedTag.contains(tag)){
                    selectedTag.remove(tag);
                }
                else{
                    selectedTag.add(tag);
                }
            }
        });



        distanceBar = (RangeBar) findViewById(R.id.distance_bar);
        priceBar = (RangeBar) findViewById(R.id.price_bar);
        minD = distanceBar.getLeftIndex();
        maxD = distanceBar.getRightIndex();
        minP = priceBar.getLeftIndex();
        maxP = priceBar.getRightIndex();

        distanceBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                minD = leftPinIndex;
                maxD = rightPinIndex;
            }
        });

        priceBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                minP = leftPinIndex;
                maxP = rightPinIndex;
            }
        });


        sendUserPrefsButton = (Button) findViewById(R.id.send_user_pref);

        sendUserPrefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject pref = new JSONObject();
                if(minD>maxD) maxD = minD;
                if(minP>maxP) maxP = minP;
                try {
                    pref.put("min price", minP);
                    pref.put("max price", maxP);
                    pref.put("min distance", minD);
                    pref.put("max distance", maxD);
                    pref.put("tags", selectedTag);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                User.getInstance().setUserPreference(pref);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
