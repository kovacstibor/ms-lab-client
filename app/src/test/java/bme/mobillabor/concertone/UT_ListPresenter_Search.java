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

public class UT_ListPresenter_Search {
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
        listPresenter.search("queen");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).showConcerts(concerts);
    }

    @Test
    public void fromNetwork_existingConcert() throws InterruptedException {
        // Arrange
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rock"));

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getFilteredConcerts("queen")).thenReturn(concerts);

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(new ConcertBaseData());

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.search("queen");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(1, screen.getConcerts().size());
        ConcertBaseData concert = (ConcertBaseData) screen.getConcerts().toArray()[0];
        Assert.assertEquals(1, (int)concert.getId());
        Assert.assertEquals("Queen", concert.getArtist());
        Assert.assertEquals("London", concert.getLocation());
        Assert.assertEquals("1988-12-13T00:00:00", concert.getDate());
        Assert.assertEquals(23000.0d, concert.getTicketPrice(), 0.1d);
        Assert.assertEquals("rock", concert.getGenre());
    }

    @Test
    public void fromNetwork_newConcert() throws InterruptedException {
        // Arrange
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rock"));

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getFilteredConcerts("queen")).thenReturn(concerts);

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(null);

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.search("queen");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(1, screen.getConcerts().size());
        ConcertBaseData concert = (ConcertBaseData) screen.getConcerts().toArray()[0];
        Assert.assertEquals(1, (int)concert.getId());
        Assert.assertEquals("Queen", concert.getArtist());
        Assert.assertEquals("London", concert.getLocation());
        Assert.assertEquals("1988-12-13T00:00:00", concert.getDate());
        Assert.assertEquals(23000.0d, concert.getTicketPrice(), 0.1d);
        Assert.assertEquals("rock", concert.getGenre());
    }

    @Test
    public void fromNetwork_error() throws InterruptedException {
        // Arrange
        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getFilteredConcerts("queen")).thenThrow(new RuntimeException());

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(null);

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.search("queen");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(0, screen.getConcerts().size());
    }

    @Test
    public void fromDatabase_ok() throws InterruptedException {
        // Arrange
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rock"));

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenThrow(new RuntimeException());

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getFilteredConcerts("queen")).thenReturn(concerts);

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(false);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.search("queen");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(1, screen.getConcerts().size());
        ConcertBaseData concert = (ConcertBaseData) screen.getConcerts().toArray()[0];
        Assert.assertEquals(1, (int)concert.getId());
        Assert.assertEquals("Queen", concert.getArtist());
        Assert.assertEquals("London", concert.getLocation());
        Assert.assertEquals("1988-12-13T00:00:00", concert.getDate());
        Assert.assertEquals(23000.0d, concert.getTicketPrice(), 0.1d);
        Assert.assertEquals("rock", concert.getGenre());
    }

    @Test
    public void fromDatabase_error() throws InterruptedException {
        // Arrange
        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenThrow(new RuntimeException());

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getFilteredConcerts("queen")).thenThrow(new RuntimeException());

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(false);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.search("queen");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(0, screen.getConcerts().size());
    }

    @Test
    public void fromNetwork_emptyFilter() throws InterruptedException {
        // Arrange
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rock"));

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenReturn(concerts);
        when(concertApi.getFilteredConcerts("queen")).thenThrow(new RuntimeException());

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(new ConcertBaseData());

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListScreenObserver screen = new ConcertListScreenObserver();
        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.search("");
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcerts());
        Assert.assertEquals(1, screen.getConcerts().size());
        ConcertBaseData concert = (ConcertBaseData) screen.getConcerts().toArray()[0];
        Assert.assertEquals(1, (int)concert.getId());
        Assert.assertEquals("Queen", concert.getArtist());
        Assert.assertEquals("London", concert.getLocation());
        Assert.assertEquals("1988-12-13T00:00:00", concert.getDate());
        Assert.assertEquals(23000.0d, concert.getTicketPrice(), 0.1d);
        Assert.assertEquals("rock", concert.getGenre());
    }
}
