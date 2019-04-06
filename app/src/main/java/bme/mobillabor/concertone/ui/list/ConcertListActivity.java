package bme.mobillabor.concertone.ui.list;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertBaseData;

public class ConcertListActivity extends Activity implements ConcertListScreen {

    @Inject
    public ConcertListPresenter concertListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_list);
        ConcertOneApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        concertListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        concertListPresenter.detachScreen();
    }

    @Override
    public void showConcerts(Iterable<ConcertBaseData> concertsToShow) {
        // TODO: implement
    }
}
