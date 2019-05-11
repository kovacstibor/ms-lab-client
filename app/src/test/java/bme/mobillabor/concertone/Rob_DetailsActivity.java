package bme.mobillabor.concertone;

import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import bme.mobillabor.concertone.helper.ConcertDetailedDataFactory;
import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.details.ConcertDetailsActivity;
import bme.mobillabor.concertone.ui.details.ConcertDetailsPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class Rob_DetailsActivity {

    @Test
    public void test_presenter() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "Iron Maiden", "London", "2015-02-03T00:00:00", 13000.0d, "metal", 6848, true);

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getConcertDetails(0)).thenReturn(concert);

        ConcertDetailsPresenter detailsPresenter = new ConcertDetailsPresenter(concertApi);

        // Act
        detailsPresenter.attachScreen(activity);
        detailsPresenter.initialize(0);
        Thread.sleep(1000);
        detailsPresenter.detachScreen();

        // Assert
        Assert.assertEquals("Iron Maiden", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_metal() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "Iron Maiden", "London", "2015-02-03T00:00:00", 13000.0d, "metal", 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("Iron Maiden", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_disco() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "ABBA", "London", "2015-02-03T00:00:00", 13000.0d, "disco", 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("ABBA", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_rap() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "Eminem", "London", "2015-02-03T00:00:00", 13000.0d, "rap", 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("Eminem", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_pop() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "Madonna", "London", "2015-02-03T00:00:00", 13000.0d, "pop", 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("Madonna", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_classical() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "Bach", "London", "2015-02-03T00:00:00", 13000.0d, "classical", 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("Bach", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_other() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "TranceX", "London", "2015-02-03T00:00:00", 13000.0d, "trance", 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("TranceX", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }

    @Test
    public void test_null_genre() throws InterruptedException {
        // Arrange
        ConcertDetailsActivity activity = Robolectric.setupActivity(ConcertDetailsActivity.class);

        ConcertDetailedData concert = ConcertDetailedDataFactory.Create(0, "TranceX", "London", "2015-02-03T00:00:00", 13000.0d, null, 6848, true);

        // Act
        activity.showConcertDetails(concert);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals("TranceX", ((TextView)activity.findViewById(R.id.tvDetailsArtist)).getText());
        Assert.assertEquals("London", ((TextView)activity.findViewById(R.id.tvDetailsLocation)).getText());
        Assert.assertEquals("2015-02-03", ((TextView)activity.findViewById(R.id.tvDetailsDate)).getText());
        Assert.assertEquals("igen", ((TextView)activity.findViewById(R.id.tvIsAccessible)).getText());
        Assert.assertEquals("6848", ((TextView)activity.findViewById(R.id.tvNumberOfFreeSpaces)).getText());
        Assert.assertEquals("13000.0 Ft", ((TextView)activity.findViewById(R.id.tvTicketPrice)).getText());
    }
}
