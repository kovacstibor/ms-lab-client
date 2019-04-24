package bme.mobillabor.concertone.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
    }

    @Provides
    @Singleton
    public ConcertApi provideConcertApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(ConcertApiConfig.ENDPOINT_ADDRESS).build().create(ConcertApi.class);
    }
}
