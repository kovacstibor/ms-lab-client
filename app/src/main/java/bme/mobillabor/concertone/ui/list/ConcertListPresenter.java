package bme.mobillabor.concertone.ui.list;

import java.util.Collection;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.PresenterBase;

public final class ConcertListPresenter extends PresenterBase<ConcertListScreen> {

    private final ConcertAPIInteractor concertAPIInteractor;

    @Inject
    public ConcertListPresenter(ConcertAPIInteractor concertAPIInteractor) {
        this.concertAPIInteractor = concertAPIInteractor;
    }

    public void initialize(){
        Collection<ConcertBaseData> concerts = concertAPIInteractor.getAllConcerts();
        screen.showConcerts(concerts);
    }

    public void search(String searchExpression) {
        if (!searchExpression.isEmpty()) {
            Collection<ConcertBaseData> filteredConcerts = concertAPIInteractor.getFilteredConcerts(searchExpression);
            screen.showConcerts(filteredConcerts);
        }
        else  {
            initialize();
        }
    }

    public void delete(int id) {
        concertAPIInteractor.deleteConcert(id);
        initialize();
    }
}
