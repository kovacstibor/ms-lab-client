package bme.mobillabor.concertone.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bme.mobillabor.concertone.R;

public class ConcertDetailsActivity extends AppCompatActivity {
    public static final String ID_KEY = "CONCERT_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_details);
    }
}
