package bme.mobillabor.concertone.ui.upsert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertDetailedData;

public class ConcertUpsertActivity extends AppCompatActivity implements ConcertUpsertScreen{
    public static final String ID_KEY = "CONCERT_ID_KEY";
    public static final String IS_EDIT_KEY = "IS_EDIT_KEY";

    @Inject
    public ConcertUpsertPresenter concertUpsertPresenter;

    private int concertId;
    private boolean isEditing;

    private Button btnUpsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_upsert);
        ConcertOneApplication.injector.inject(this);

        isEditing = getIntent().getBooleanExtra(IS_EDIT_KEY, false);
        if (isEditing) {
            concertId = getIntent().getIntExtra(ID_KEY, 0);
        }

        // TODO: initialize UI
        btnUpsert = findViewById(R.id.btnDoUpsert);
        btnUpsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: get actual values from UI
                ConcertDetailedData concertDetails = new ConcertDetailedData();

                if (isEditing) {
                    concertUpsertPresenter.updateConcert(concertId, concertDetails);
                }
                else {
                    concertUpsertPresenter.createConcert(concertDetails);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        concertUpsertPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        concertUpsertPresenter.detachScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isEditing) {
            concertUpsertPresenter.initializeForUpdate(concertId);
        }
    }

    @Override
    public void fillActualConcertDetails(ConcertDetailedData concertDetails) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: implement
            }
        });
    }

    @Override
    public void upsertCompleted(boolean succeeded) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: implement
            }
        });
    }
}
