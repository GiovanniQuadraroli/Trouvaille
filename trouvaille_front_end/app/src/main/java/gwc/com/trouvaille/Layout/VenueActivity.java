package gwc.com.trouvaille.Layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gwc.com.trouvaille.Entity.Venue;
import gwc.com.trouvaille.R;

public class VenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Venue venue = (Venue) getIntent().getSerializableExtra("venue");

        Log.e("Venue activity created", venue.getName());

        bindVenueToView(venue);
    }

    public void bindVenueToView(Venue venue){
        ImageView imageView = findViewById(R.id.venue_image);
        TextView name = findViewById(R.id.venue_name);
        TextView address = findViewById(R.id.venue_address);

        name.setText(venue.getName());
        address.setText(venue.getAddress());
        Picasso.get().load(venue.getImage()).into(imageView);
    }

}
