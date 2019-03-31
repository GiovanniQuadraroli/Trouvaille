package gwc.com.trouvaille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.appyvet.materialrangebar.RangeBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gwc.com.trouvaille.Adapter.MainActivity;
import gwc.com.trouvaille.Adapter.TagsAdapter;
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
    private ArrayList<Tag> tags = new ArrayList<>();


    private RangeBar distanceBar;
    private RangeBar priceBar;
    private RecyclerView recyclerView;
    private TagsAdapter tagsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);

//        tags = requestTags();


        setTagsView();

        for(Tag.Tags t:Tag.Tags.values()){
            tags.add(new Tag(t));
        }

        distanceBar = findViewById(R.id.distance_bar);
        priceBar = findViewById(R.id.price_bar);
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


        sendUserPrefsButton = findViewById(R.id.send_user_pref);

        sendUserPrefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject pref = new JSONObject();
                selectedTag = tagsAdapter.getSelectedTags();
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

    private void setTagsView() {
        recyclerView = findViewById(R.id.tags_list);

        tagsAdapter = new TagsAdapter(this, tags);
        linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(tagsAdapter);
    }

    private ArrayList<Tag> requestTags() {
        String url = Client.getInstance(this).getUrl();
        final ArrayList<Tag> tmp = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + "/users/1/preference", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String[] requestTags = (String[])response.getJSONObject("display_options").get("tag_lists");
                    for(String tag:requestTags){
                        tmp.add(new Tag(Tag.Tags.valueOf(tag)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response: ", error.toString());
            }
        });

        Client.getInstance(this.getApplicationContext()).addToRequestQueue(request);
        return tmp;
    }
}
