package bme.mobillabor.concertone.api;

import java.util.List;

import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.model.ConcertDetailedData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConcertApi {
    @GET("Concerts")
    Call<List<ConcertBaseData>> getConcerts(@Query("filter") String filter);

    @POST("Concerts")
    Call<ResponseBody> createConcert(@Body ConcertDetailedData newConcert);

    @GET("Concerts/{id}")
    Call<ConcertDetailedData> getConcertById(@Path("id") int id);

    @PUT("Concerts/{id}")
    Call<ResponseBody> updateConcert(@Path("id") int id, @Body ConcertDetailedData modifiedConcert);

    @DELETE("Concerts/{id}")
    Call<ResponseBody> deleteConcertById(@Path("id") int id);
}
