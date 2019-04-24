package bme.mobillabor.concertone.ui.list;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.PresenterBase;

public final class ConcertListPresenter extends PresenterBase<ConcertListScreen> {

    private final ConcertAPIInteractor concertAPIInteractor;
    private final Executor networkExecutor;

    @Inject
    public ConcertListPresenter(ConcertAPIInteractor concertAPIInteractor) {
        this.networkExecutor = Executors.newFixedThreadPool(1);
        this.concertAPIInteractor = concertAPIInteractor;
    }

    public void initialize(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Collection<ConcertBaseData> concerts = concertAPIInteractor.getAllConcerts();
                screen.showConcerts(concerts);
            }
        });
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
