package bme.mobillabor.concertone.ui.upsert;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.PresenterBase;

public class ConcertUpsertPresenter extends PresenterBase<ConcertUpsertScreen> {
    private final ConcertAPIInteractor concertAPIInteractor;

    @Inject
    public ConcertUpsertPresenter(ConcertAPIInteractor concertAPIInteractor) {
        this.concertAPIInteractor = concertAPIInteractor;
    }

    public void initializeForUpdate(int id) {
        ConcertDetailedData concertDetails = concertAPIInteractor.getConcertDetails(id);
        screen.fillActualConcertDetails(concertDetails);
    }

    public void createConcert(ConcertDetailedData newConcertDetails) {
        boolean result = concertAPIInteractor.createConcert(newConcertDetails);
        screen.upsertCompleted(result);
    }

    public void updateConcert(int id, ConcertDetailedData updatedConcertDetails) {
        updatedConcertDetails.setId(id);
        boolean result = concertAPIInteractor.updateConcert(id, updatedConcertDetails);
        screen.upsertCompleted(result);
    }
}
