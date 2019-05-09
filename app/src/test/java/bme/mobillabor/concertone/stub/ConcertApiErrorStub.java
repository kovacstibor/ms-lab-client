package bme.mobillabor.concertone.stub;

import java.util.ArrayList;
import java.util.List;

import bme.mobillabor.concertone.data.ConcertApi;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class ConcertApiErrorStub implements ConcertApi {
    @Override
    public Call<List<ConcertBaseData>> getConcerts(String filter) {
        return new NetworkResultCallStub<>(500, new ArrayList<ConcertBaseData>(), true);
    }

    @Override
    public Call<ResponseBody> createConcert(ConcertDetailedData newConcert) {
        return new NetworkEmptyCallStub(500, true);
    }

    @Override
    public Call<ConcertDetailedData> getConcertById(int id) {
        return new NetworkResultCallStub<>(500, new ConcertDetailedData(), true);
    }

    @Override
    public Call<ResponseBody> updateConcert(int id, ConcertDetailedData modifiedConcert) {
        return new NetworkEmptyCallStub(500, true);
    }

    @Override
    public Call<ResponseBody> deleteConcertById(int id) {
        return new NetworkEmptyCallStub(500, true);
    }
}
