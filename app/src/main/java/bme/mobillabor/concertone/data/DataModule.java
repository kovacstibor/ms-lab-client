package bme.mobillabor.concertone.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {
    private Context context;

    public DataModule(Context context) {
        this.context = context;
    }

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

    @Provides
    @Singleton
    public ConcertDb provideConcertDbInteractor() {
        return Room.databaseBuilder(context.getApplicationContext(), AConcertDb.class, "concert-database")
                   .allowMainThreadQueries()
                   .build();
    }
}
