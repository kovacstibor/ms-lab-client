package bme.mobillabor.concertone.ui.upsert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bme.mobillabor.concertone.R;

public class ConcertUpsertActivity extends AppCompatActivity {
    public static final String ID_KEY = "CONCERT_ID_KEY";
    public static final String IS_EDIT_KEY = "IS_EDIT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_upsert);
    }
}
