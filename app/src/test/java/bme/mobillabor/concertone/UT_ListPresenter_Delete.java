package bme.mobillabor.concertone;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import bme.mobillabor.concertone.data.ConcertBaseDataDao;
import bme.mobillabor.concertone.data.ConcertDb;
import bme.mobillabor.concertone.helper.ConcertBaseDataFactory;
import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.stub.ConcertListScreenObserver;
import bme.mobillabor.concertone.ui.list.ConcertListPresenter;
import bme.mobillabor.concertone.ui.list.ConcertListScreen;
import bme.mobillabor.concertone.utility.INetworkStateProvider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UT_ListPresenter_Delete {
    @Test
    public void screen_called() throws InterruptedException {
        // Arrange
        Collection<ConcertBaseData> concerts = new ArrayList<>();
        ConcertListScreen screen = mock(ConcertListScreen.class);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getFilteredConcerts("queen")).thenReturn(concerts);

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(mock(ConcertBaseDataDao.class));

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.delete(1);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).showConcerts(concerts);
    }

    @Test
    public void happy_path() throws InterruptedException {
        // Arrange
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(2, "ABBA", "Stockholm", "1988-12-14T00:00:00", 21000.0d, "disco"));

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenReturn(concerts);

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(concerts.get(0));

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.delete(1);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(concertApi, times(1)).deleteConcert(1);
        verify(concertApi, times(1)).getAllConcerts();
        verify(dao, times(1)).deleteConcert(concerts.get(0));

        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(1, screen.getConcerts().size());
        ConcertBaseData concert = (ConcertBaseData) screen.getConcerts().toArray()[0];
        Assert.assertEquals(2, (int)concert.getId());
        Assert.assertEquals("ABBA", concert.getArtist());
        Assert.assertEquals("Stockholm", concert.getLocation());
        Assert.assertEquals("1988-12-14T00:00:00", concert.getDate());
        Assert.assertEquals(21000.0d, concert.getTicketPrice(), 0.1d);
        Assert.assertEquals("disco", concert.getGenre());
    }
}
