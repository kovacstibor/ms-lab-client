package bme.mobillabor.concertone.ui;

import android.content.Context;

import bme.mobillabor.concertone.data.ConcertApi;
import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.interactor.IConcertAPIInteractor;
import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public IConcertAPIInteractor provideConcertApiInteractor(ConcertApi concertApi) {
        return new ConcertAPIInteractor(concertApi);
    }
}
