package bme.mobillabor.concertone.ui.details;

import android.util.Log;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.PresenterBase;

public class ConcertDetailsPresenter extends PresenterBase<ConcertDetailsScreen> {

    private final ConcertAPIInteractor concertAPIInteractor;

    @Inject
    public ConcertDetailsPresenter(ConcertAPIInteractor concertAPIInteractor) {
        this.concertAPIInteractor = concertAPIInteractor;
    }

    public void initialize(int id) {
        Log.d("MyTag", Integer.toString(id));
        ConcertDetailedData concertDetails = concertAPIInteractor.getConcertDetails(id);
        screen.showConcertDetails(concertDetails);
    }
}
