package bme.mobillabor.concertone.interactor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import bme.mobillabor.concertone.ConcertOneApplication;
import bme.mobillabor.concertone.data.ConcertApi;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ConcertAPIInteractor {

    private ConcertApi concertApi;

    @Inject
    public ConcertAPIInteractor(ConcertApi concertApi) {
        this.concertApi = concertApi;
        ConcertOneApplication.injector.inject(this);
    }

    public Collection<ConcertBaseData> getAllConcerts() {
        try {
            Call<List<ConcertBaseData>> concertsCall = concertApi.getConcerts(null);
            Response<List<ConcertBaseData>> concertsResponse = concertsCall.execute();
            if (concertsResponse.code() != 200) {
                throw new Exception("Unexpected error! The response status code is not 200.");
            }
            return concertsResponse.body();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public Collection<ConcertBaseData> getFilteredConcerts(String filterExpression) {
        try {
            Call<List<ConcertBaseData>> concertsCall = concertApi.getConcerts(filterExpression);
            Response<List<ConcertBaseData>> concertsResponse = concertsCall.execute();
            if (concertsResponse.code() != 200) {
                throw new Exception("Unexpected error! The response status code is not 200.");
            }
            return concertsResponse.body();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public void deleteConcert(int id) {
        try {
            Call<ResponseBody> deleteConcertCall = concertApi.deleteConcertById(id);
            Response<ResponseBody> deleteConcertResponse = deleteConcertCall.execute();
            if (deleteConcertResponse.code() != 200) {
                throw new Exception("Unexpected error! The response status code is not 200.");
            }
        } catch (Exception exception) {

        }
    }

    public ConcertDetailedData getConcertDetails(int id) {
        try {
            Call<ConcertDetailedData> concertDetailsCall = concertApi.getConcertById(id);
            Response<ConcertDetailedData> concertDetailsResponse = concertDetailsCall.execute();
            if (concertDetailsResponse.code() != 200) {
                throw new Exception("Unexpected error! The response status code is not 200.");
            }
            return concertDetailsResponse.body();
        } catch (Exception exception) {
            return new ConcertDetailedData();
        }
    }

    public boolean updateConcert(int id, ConcertDetailedData updatedConcertDetails) {
        try {
            Call<ResponseBody> updateConcertCall = concertApi.updateConcert(id, updatedConcertDetails);
            Response<ResponseBody> updateConcertResponse = updateConcertCall.execute();
            if (updateConcertResponse.code() != 200) {
                throw new Exception("Unexpected error! The response status code is not 200.");
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean createConcert(ConcertDetailedData newConcertDetails) {
        try {
            Call<ResponseBody> createConcertCall = concertApi.createConcert(newConcertDetails);
            Response<ResponseBody> createConcertResponse = createConcertCall.execute();
            if (createConcertResponse.code() != 200) {
                throw new Exception("Unexpected error! The response status code is not 200.");
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
