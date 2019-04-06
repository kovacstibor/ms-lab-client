package bme.mobillabor.concertone;

import android.app.Application;

import bme.mobillabor.concertone.ui.UIModule;

public class ConcertOneApplication extends Application {
    public static ConcertOneApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerConcertOneApplicationComponent.builder()
                        .uIModule(new UIModule(this))
                        .build();
    }
}
