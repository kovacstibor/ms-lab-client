package bme.mobillabor.concertone.ui.upsert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertDetailedData;

public class ConcertUpsertActivity extends AppCompatActivity implements ConcertUpsertScreen{
    public static final String ID_KEY = "CONCERT_ID_KEY";
    public static final String IS_EDIT_KEY = "IS_EDIT_KEY";

    private FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    public ConcertUpsertPresenter concertUpsertPresenter;

    private int concertId;
    private boolean isEditing;

    private EditText etArtist;
    private EditText etLocation;
    private DatePicker dpDate;
    private EditText etPrice;
    private EditText etFreeSpaces;
    private CheckBox cbIsAccessible;
    private EditText etGenre;
    private Button btnUpsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_upsert);
        ConcertOneApplication.injector.inject(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        isEditing = getIntent().getBooleanExtra(IS_EDIT_KEY, false);
        if (isEditing) {
            concertId = getIntent().getIntExtra(ID_KEY, 0);
        }

        etArtist = findViewById(R.id.etUpsertArtist);
        etLocation = findViewById(R.id.etUpsertLocation);
        dpDate = findViewById(R.id.dpUpsertDate);
        etPrice = findViewById(R.id.etUpsertPrice);
        etFreeSpaces = findViewById(R.id.etUpsertPlaces);
        cbIsAccessible = findViewById(R.id.cbUpsertIsAccessible);
        etGenre = findViewById(R.id.etUpsertGenre);
        btnUpsert = findViewById(R.id.btnDoUpsert);

        btnUpsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(dpDate.getYear(), dpDate.getMonth(), dpDate.getDayOfMonth());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                ConcertDetailedData concertDetails = new ConcertDetailedData();
                concertDetails.setArtist(etArtist.getText().toString());
                concertDetails.setLocation(etLocation.getText().toString());
                concertDetails.setDate(dateFormat.format(calendar.getTime()));
                concertDetails.setTicketPrice(Double.parseDouble(etPrice.getText().toString()));
                concertDetails.setNumberOfFreeSpaces(Integer.parseInt(etFreeSpaces.getText().toString()));
                concertDetails.setIsAccessible(cbIsAccessible.isChecked());
                concertDetails.setGenre(etGenre.getText().toString());

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
        if (isEditing) {
            concertUpsertPresenter.initializeForUpdate(concertId);
        }
    }

    @Override
    public void fillActualConcertDetails(final ConcertDetailedData concertDetails) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (concertDetails == null) {
                    return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date concertDate;
                try {
                    concertDate = dateFormat.parse(concertDetails.getDate());
                } catch (ParseException e) {
                    concertDate = new Date();
                }

                etArtist.setText(concertDetails.getArtist());
                etLocation.setText(concertDetails.getLocation());
                dpDate.updateDate(concertDate.getYear() + 1900, concertDate.getMonth(), concertDate.getDate());
                etPrice.setText(concertDetails.getTicketPrice().toString());
                etFreeSpaces.setText(concertDetails.getNumberOfFreeSpaces().toString());
                cbIsAccessible.setChecked(concertDetails.getIsAccessible());
                etGenre.setText(concertDetails.getGenre());
            }
        });
    }

    @Override
    public void upsertCompleted(boolean succeeded) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }
}
