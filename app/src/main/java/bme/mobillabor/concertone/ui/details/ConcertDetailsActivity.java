package bme.mobillabor.concertone.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertDetailedData;

public class ConcertDetailsActivity extends AppCompatActivity implements ConcertDetailsScreen {
    public static final String ID_KEY = "CONCERT_ID_KEY";

    private FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    public ConcertDetailsPresenter concertDetailsPresenter;

    private ImageView ivGenre;
    private TextView tvArtist;
    private TextView tvLocation;
    private TextView tvTicketPrice;
    private TextView tvDate;
    private TextView tvFreeSpaces;
    private TextView tvIsAccessible;

    private int concertId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_details);
        ConcertOneApplication.injector.inject(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ivGenre = findViewById(R.id.ivDetailsGenre);
        tvArtist = findViewById(R.id.tvDetailsArtist);
        tvLocation = findViewById(R.id.tvDetailsLocation);
        tvTicketPrice = findViewById(R.id.tvTicketPrice);
        tvDate = findViewById(R.id.tvDetailsDate);
        tvFreeSpaces = findViewById(R.id.tvNumberOfFreeSpaces);
        tvIsAccessible = findViewById(R.id.tvIsAccessible);
        concertId = getIntent().getIntExtra(ID_KEY, 0);
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
    public void showConcertDetails(final ConcertDetailedData concertDetails) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (concertDetails == null) {
                    return;
                }

                String dateString = concertDetails.getDate().substring(0, 10);

                ivGenre.setImageResource(getImageResourceId(concertDetails.getGenre()));
                tvArtist.setText(concertDetails.getArtist());
                tvLocation.setText(concertDetails.getLocation());
                tvTicketPrice.setText(concertDetails.getTicketPrice() + " Ft");
                tvDate.setText(dateString);
                tvFreeSpaces.setText(concertDetails.getNumberOfFreeSpaces().toString());
                tvIsAccessible.setText(concertDetails.getIsAccessible() ? "igen" : "nem");
            }
        });
    }

    private int getImageResourceId(String genre) {
        if (genre == null) {
            return R.drawable.img_music_general;
        }
        if (genre.toUpperCase().contains("POP")) {
            return R.drawable.img_music_pop;
        }
        if (genre.toUpperCase().contains("RAP")) {
            return R.drawable.img_music_rap;
        }
        if (genre.toUpperCase().contains("DISCO")) {
            return R.drawable.img_music_disco;
        }
        if (genre.toUpperCase().contains("ROCK") || genre.toUpperCase().contains("METAL")) {
            return R.drawable.img_musis_rock;
        }
        if (genre.toUpperCase().contains("CLASSICAL")) {
            return R.drawable.img_music_clasical;
        }

        return R.drawable.img_music_general;
    }
}
