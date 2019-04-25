package bme.mobillabor.concertone.ui.list;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.data.ConcertDb;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.PresenterBase;
import bme.mobillabor.concertone.utility.INetworkStateProvider;

public final class ConcertListPresenter extends PresenterBase<ConcertListScreen> {

    private final ConcertAPIInteractor concertAPIInteractor;
    private final ConcertDb concertDb;
    private final Executor networkExecutor;
    private final INetworkStateProvider networkStateProvider;

    @Inject
    public ConcertListPresenter(ConcertAPIInteractor concertAPIInteractor, ConcertDb concertDb, INetworkStateProvider networkStateProvider) {
        this.networkExecutor = Executors.newFixedThreadPool(1);
        this.concertAPIInteractor = concertAPIInteractor;
        this.concertDb = concertDb;
        this.networkStateProvider = networkStateProvider;
    }

    public void initialize(){
        if (networkStateProvider.isNetworkAvailable()) {
            networkExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Collection<ConcertBaseData> concerts = concertAPIInteractor.getAllConcerts();
                    for (ConcertBaseData concert : concerts) {
                        try {
                            ConcertBaseData entity = concertDb.concertBaseDataDao().getConcertById(concert.getId());
                            if (entity == null) {
                                concertDb.concertBaseDataDao().insertConcert(concert);
                            }
                            else {
                                concertDb.concertBaseDataDao().updateConcert(concert);
                            }
                        }
                        catch (Exception exception) {
                            // Nothing to do.
                        }
                    }
                    screen.showConcerts(concerts);
                }
            });
        }
        else {
            Collection<ConcertBaseData> concerts = concertDb.concertBaseDataDao().getConcerts();
            screen.showConcerts(concerts);
        }
    }

    public void search(final String searchExpression) {
        if (!searchExpression.isEmpty()) {
            if (networkStateProvider.isNetworkAvailable()) {
                networkExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Collection<ConcertBaseData> filteredConcerts = concertAPIInteractor.getFilteredConcerts(searchExpression);
                        screen.showConcerts(filteredConcerts);
                    }
                });
            }
            else {
                Collection<ConcertBaseData> filteredConcerts = concertDb.concertBaseDataDao().getFilteredConcerts(searchExpression);
                screen.showConcerts(filteredConcerts);
            }
        }
        else  {
            initialize();
        }
    }

    public void delete(final int id) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                concertAPIInteractor.deleteConcert(id);
            }
        });
        try {
            ConcertBaseData concert = concertDb.concertBaseDataDao().getConcertById(id);
            if (concert != null) {
                concertDb.concertBaseDataDao().deleteConcert(concert);
            }
        }
        catch (Exception exception) {
            // Nothing to do.
        }
        initialize();
    }
}
