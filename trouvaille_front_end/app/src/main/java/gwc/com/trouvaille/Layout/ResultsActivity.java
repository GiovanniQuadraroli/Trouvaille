package gwc.com.trouvaille.Layout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import gwc.com.trouvaille.Adapter.VenueAdapter;
import gwc.com.trouvaille.Entity.Venue;
import gwc.com.trouvaille.R;

public class ResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Venue> venues;
    private VenueAdapter venuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        venues = new ArrayList<>();
        venues = (ArrayList<Venue>) getIntent().getSerializableExtra("venues");

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

}
