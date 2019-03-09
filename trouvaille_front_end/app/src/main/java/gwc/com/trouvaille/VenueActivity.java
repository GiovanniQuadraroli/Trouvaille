package gwc.com.trouvaille;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import gwc.com.trouvaille.Entity.Venue;

public class VenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Venue venue = (Venue) getIntent().getSerializableExtra("venue");

        Log.e("Venue activity created", venue.getName());
    }

}
