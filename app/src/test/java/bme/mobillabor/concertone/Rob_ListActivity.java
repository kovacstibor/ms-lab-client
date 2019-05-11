package bme.mobillabor.concertone;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import bme.mobillabor.concertone.data.ConcertBaseDataDao;
import bme.mobillabor.concertone.data.ConcertDb;
import bme.mobillabor.concertone.helper.ConcertBaseDataFactory;
import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.list.ConcertListActivity;
import bme.mobillabor.concertone.ui.list.ConcertListPresenter;
import bme.mobillabor.concertone.utility.INetworkStateProvider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class Rob_ListActivity {
    @Test
    public void test_empty() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenReturn(concerts);

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(new ConcertBaseData());

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(activity);
        listPresenter.initialize();
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(0, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_metal() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rock"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_pop() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "pop"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_rap() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "rap"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_disco() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "disco"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_classical() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "classical"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_other() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "trance"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_null_genre() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, null));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_long_location() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London Buckingham Palace", "1988-12-13T00:00:00", 23000.0d, null));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(1, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }






    @Test
    public void test_search() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenReturn(concerts);

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(new ConcertBaseData());

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(activity);
        activity.findViewById(R.id.btSearch).callOnClick();
        Thread.sleep(1000);

        // Assert
        Assert.assertEquals(0, ((RecyclerView)activity.findViewById(R.id.rvConcertList)).getAdapter().getItemCount());
    }

    @Test
    public void test_create() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);

        ArrayList<ConcertBaseData> concerts = new ArrayList<>();

        IConcertAPIInteractor concertApi = mock(IConcertAPIInteractor.class);
        when(concertApi.getAllConcerts()).thenReturn(concerts);

        ConcertBaseDataDao dao = mock(ConcertBaseDataDao.class);
        when(dao.getConcertById(1)).thenReturn(new ConcertBaseData());

        ConcertDb concertDb = mock(ConcertDb.class);
        when(concertDb.concertBaseDataDao()).thenReturn(dao);

        INetworkStateProvider networkStateProvider = mock(INetworkStateProvider.class);
        when(networkStateProvider.isNetworkAvailable()).thenReturn(true);

        ConcertListPresenter listPresenter = new ConcertListPresenter(concertApi, concertDb, networkStateProvider);

        // Act
        listPresenter.attachScreen(activity);
        activity.findViewById(R.id.btAddConcert).callOnClick();
        Thread.sleep(1000);
    }

    @Test
    public void test_item_details()throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "trance"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);
        RecyclerView list = activity.findViewById(R.id.rvConcertList);
        View itemView = list.findViewHolderForAdapterPosition(0).itemView;
        itemView.findViewById(R.id.list_item_artist).callOnClick();
    }

    @Test
    public void test_item_edit() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "trance"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);
        RecyclerView list = activity.findViewById(R.id.rvConcertList);
        View itemView = list.findViewHolderForAdapterPosition(0).itemView;
        itemView.findViewById(R.id.list_item_edit).callOnClick();
    }

    @Test
    public void test_item_delete() throws InterruptedException {
        // Arrange
        ConcertListActivity activity = Robolectric.setupActivity(ConcertListActivity.class);
        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
        concerts.add(ConcertBaseDataFactory.Create(1, "Queen", "London", "1988-12-13T00:00:00", 23000.0d, "trance"));

        // Act
        activity.showConcerts(concerts);
        Thread.sleep(1000);
        RecyclerView list = activity.findViewById(R.id.rvConcertList);
        View itemView = list.findViewHolderForAdapterPosition(0).itemView;
        itemView.findViewById(R.id.list_item_delete).callOnClick();
    }
}
