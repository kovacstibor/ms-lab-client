package bme.mobillabor.concertone.ui.upsert;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.PresenterBase;

public class ConcertUpsertPresenter extends PresenterBase<ConcertUpsertScreen> {
    private final IConcertAPIInteractor concertAPIInteractor;
    private final Executor networkExecutor;

    @Inject
    public ConcertUpsertPresenter(IConcertAPIInteractor concertAPIInteractor) {
        this.concertAPIInteractor = concertAPIInteractor;
        this.networkExecutor = Executors.newFixedThreadPool(1);
    }

    public void initializeForUpdate(final int id) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ConcertDetailedData concertDetails = concertAPIInteractor.getConcertDetails(id);
                screen.fillActualConcertDetails(concertDetails);
            }
        });
    }

    public void createConcert(final ConcertDetailedData newConcertDetails) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                boolean result = concertAPIInteractor.createConcert(newConcertDetails);
                screen.upsertCompleted(result);
            }
        });
    }

    public void updateConcert(final int id, final ConcertDetailedData updatedConcertDetails) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                updatedConcertDetails.setId(id);
                boolean result = concertAPIInteractor.updateConcert(id, updatedConcertDetails);
                screen.upsertCompleted(result);
            }
        });
    }
}
