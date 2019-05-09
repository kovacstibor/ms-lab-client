package bme.mobillabor.concertone.stub;

import java.util.ArrayList;
import java.util.List;

import bme.mobillabor.concertone.data.ConcertApi;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class ConcertApiStub implements ConcertApi {

    @Override
    public Call<List<ConcertBaseData>> getConcerts(String filter) {
        List<ConcertBaseData> concerts = new ArrayList<>();

        ConcertBaseData concert1 = new ConcertBaseData();
        concert1.setArtist("Queen");
        concert1.setLocation("London");
        concert1.setDate("1978-12-31T00:00:00");
        concert1.setGenre("rock");
        concert1.setId(1);
        concert1.setTicketPrice(3000.0d);
        concerts.add(concert1);

        if (filter == null || filter.equals("")) {
            ConcertBaseData concert2 = new ConcertBaseData();
            concert2.setArtist("ABBA");
            concert2.setLocation("New York");
            concert2.setDate("1988-02-12T00:00:00");
            concert2.setGenre("disco");
            concert2.setId(2);
            concert2.setTicketPrice(5400.0d);
            concerts.add(concert2);
        }

        return new NetworkResultCallStub<>(200, concerts, false);
    }

    @Override
    public Call<ResponseBody> createConcert(ConcertDetailedData newConcert) {
        return new NetworkEmptyCallStub(500, false);
    }

    @Override
    public Call<ConcertDetailedData> getConcertById(int id) {
        return new NetworkResultCallStub<>(500, new ConcertDetailedData(), false);
    }

    @Override
    public Call<ResponseBody> updateConcert(int id, ConcertDetailedData modifiedConcert) {
        return new NetworkEmptyCallStub(500, false);
    }

    @Override
    public Call<ResponseBody> deleteConcertById(int id) {
        return new NetworkEmptyCallStub(500, false);
    }
}
