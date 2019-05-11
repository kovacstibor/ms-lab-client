package bme.mobillabor.concertone;

import org.junit.Assert;
import org.junit.Test;

import bme.mobillabor.concertone.helper.ConcertDetailedDataFactory;
import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.stub.ConcertDetailsScreenObserver;
import bme.mobillabor.concertone.ui.details.ConcertDetailsPresenter;
import bme.mobillabor.concertone.ui.details.ConcertDetailsScreen;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UT_DetailsPresenter_Details {
    @Test
    public void screen_called() throws InterruptedException {
        // Arrange
        ConcertDetailedData concert = new ConcertDetailedData();
        ConcertDetailsScreen screen = mock(ConcertDetailsScreen.class);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getConcertDetails(1)).thenReturn(concert);

        ConcertDetailsPresenter listPresenter = new ConcertDetailsPresenter(concertApi);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.initialize(1);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).showConcertDetails(concert);
    }

    @Test
    public void fromNetwork_existingConcert() throws InterruptedException {
        // Arrange
        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rock", 200, true);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getConcertDetails(1)).thenReturn(concert);

        ConcertDetailsScreenObserver screen = new ConcertDetailsScreenObserver();
        ConcertDetailsPresenter detailsPresenter = new ConcertDetailsPresenter(concertApi);

        // Act
        detailsPresenter.attachScreen(screen);
        detailsPresenter.initialize(1);
        Thread.sleep(100);
        detailsPresenter.detachScreen();

        // Assert
        Assert.assertNotNull(screen.getConcert());
        Assert.assertEquals(1, (int)concert.getId());
        Assert.assertEquals("Queen", concert.getArtist());
        Assert.assertEquals("London", concert.getLocation());
        Assert.assertEquals("1988-12-13T00:00:00", concert.getDate());
        Assert.assertEquals(23000.0d, concert.getTicketPrice(), 0.1d);
        Assert.assertEquals("rock", concert.getGenre());
        Assert.assertEquals(200, (int)concert.getNumberOfFreeSpaces());
        Assert.assertTrue(concert.getIsAccessible());
    }
}
