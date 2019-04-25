package bme.mobillabor.concertone.ui.details;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.PresenterBase;

public class ConcertDetailsPresenter extends PresenterBase<ConcertDetailsScreen> {

    private final ConcertAPIInteractor concertAPIInteractor;
    private final Executor networkExecutor;

    @Inject
    public ConcertDetailsPresenter(ConcertAPIInteractor concertAPIInteractor) {
        this.concertAPIInteractor = concertAPIInteractor;
        this.networkExecutor = Executors.newFixedThreadPool(1);
    }

    public void initialize(final int id) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ConcertDetailedData concertDetails = concertAPIInteractor.getConcertDetails(id);
                screen.showConcertDetails(concertDetails);
            }
        });
    }
}
