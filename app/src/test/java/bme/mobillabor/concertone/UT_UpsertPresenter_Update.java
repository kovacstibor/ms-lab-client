package bme.mobillabor.concertone;

import org.junit.Test;

import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertPresenter;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertScreen;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UT_UpsertPresenter_Update {
    @Test
    public void screen_initialized() throws InterruptedException {
        // Arrange
        ConcertDetailedData concert = new ConcertDetailedData();
        ConcertUpsertScreen screen = mock(ConcertUpsertScreen.class);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getConcertDetails(1)).thenReturn(concert);

        ConcertUpsertPresenter listPresenter = new ConcertUpsertPresenter(concertApi);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.initializeForUpdate(1);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).fillActualConcertDetails(concert);
    }

    @Test
    public void screen_called_ok() throws InterruptedException {
        // Arrange
        ConcertDetailedData concert = new ConcertDetailedData();
        ConcertUpsertScreen screen = mock(ConcertUpsertScreen.class);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.updateConcert(1, concert)).thenReturn(true);

        ConcertUpsertPresenter listPresenter = new ConcertUpsertPresenter(concertApi);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.updateConcert(1, concert);
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
        when(concertApi.updateConcert(1, concert)).thenReturn(false);

        ConcertUpsertPresenter listPresenter = new ConcertUpsertPresenter(concertApi);

        // Act
        listPresenter.attachScreen(screen);
        listPresenter.updateConcert(1, concert);
        Thread.sleep(100);
        listPresenter.detachScreen();

        // Assert
        verify(screen, times(1)).upsertCompleted(false);
    }
}
