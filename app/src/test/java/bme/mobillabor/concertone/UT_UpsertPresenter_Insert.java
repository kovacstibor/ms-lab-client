package bme.mobillabor.concertone;

import org.junit.Test;

import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.details.ConcertDetailsPresenter;
import bme.mobillabor.concertone.ui.details.ConcertDetailsScreen;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertPresenter;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertScreen;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UT_UpsertPresenter_Insert {
    @Test
    public void screen_called_ok() throws InterruptedException {
        // Arrange
        ConcertDetailedData concert = new ConcertDetailedData();
        ConcertUpsertScreen screen = mock(ConcertUpsertScreen.class);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.createConcert(concert)).thenReturn(true);

        ConcertUpsertPresenter listPresenter = new ConcertUpsertPresenter(concertApi);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.createConcert(concert);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).upsertCompleted(true);
    }

    @Test
    public void screen_called_fail() throws InterruptedException {
        // Arrange
        ConcertDetailedData concert = new ConcertDetailedData();
        ConcertUpsertScreen screen = mock(ConcertUpsertScreen.class);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.createConcert(concert)).thenReturn(false);

        ConcertUpsertPresenter listPresenter = new ConcertUpsertPresenter(concertApi);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.createConcert(concert);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).upsertCompleted(false);
    }
}
