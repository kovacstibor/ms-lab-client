package bme.mobillabor.concertone.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertDetailedData;

public class ConcertDetailsActivity extends AppCompatActivity implements ConcertDetailsScreen {
    public static final String ID_KEY = "CONCERT_ID_KEY";

    @Inject
    public ConcertDetailsPresenter concertDetailsPresenter;

    private int concertId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_details);
        ConcertOneApplication.injector.inject(this);

        concertId = savedInstanceState.getInt(ID_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        concertDetailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        concertDetailsPresenter.detachScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        concertDetailsPresenter.initialize(concertId);
    }

    @Override
    public void showConcertDetails(ConcertDetailedData concertDetails) {
        // TODO: implement
    }
}
