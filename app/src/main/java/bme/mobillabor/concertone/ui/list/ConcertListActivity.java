package bme.mobillabor.concertone.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertActivity;

public class ConcertListActivity extends AppCompatActivity implements ConcertListScreen {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    public ConcertListPresenter concertListPresenter;
    private final List<ConcertBaseData> concertList = new ArrayList<>();
    private ConcertBaseDataAdapter concertListAdapter;

    private EditText etSearchExpression;
    private ImageView btSearch;
    private RecyclerView rvConcertList;
    private Toolbar tbToolbar;
    private ImageView ibAddConcert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_list);
        ConcertOneApplication.injector.inject(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        concertListAdapter = new ConcertBaseDataAdapter(concertList, this);

        etSearchExpression = findViewById(R.id.etSearchExpression);

        btSearch = findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ITEM_1");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Search");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                concertListPresenter.search(etSearchExpression.getText().toString());
            }
        });

        rvConcertList = findViewById(R.id.rvConcertList);
        LinearLayoutManager listLayoutManager = new LinearLayoutManager(this);
        listLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvConcertList.setLayoutManager(listLayoutManager);
        rvConcertList.setAdapter(concertListAdapter);

        tbToolbar = findViewById(R.id.list_toolbar);
        setSupportActionBar(tbToolbar);

        ibAddConcert = findViewById(R.id.btAddConcert);
        ibAddConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ITEM_2");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Create");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                Intent intent = new Intent(ConcertListActivity.this, ConcertUpsertActivity.class);
                intent.putExtra(ConcertUpsertActivity.IS_EDIT_KEY, false);
                startActivity(intent);
            }
        });
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
    protected void onResume() {
        super.onResume();
        concertListPresenter.initialize();
    }

    @Override
    public void showConcerts(final Collection<ConcertBaseData> concertsToShow) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                concertList.clear();
                concertList.addAll(concertsToShow);
                concertListAdapter.notifyDataSetChanged();
            }
        });
    }
}
