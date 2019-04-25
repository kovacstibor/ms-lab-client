package bme.mobillabor.concertone;

import android.app.Application;

import bme.mobillabor.concertone.data.DataModule;
import bme.mobillabor.concertone.ui.UIModule;
import bme.mobillabor.concertone.utility.UtilityModule;

public class ConcertOneApplication extends Application {
    public static ConcertOneApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerConcertOneApplicationComponent.builder()
                        .uIModule(new UIModule(this))
                        .dataModule(new DataModule(this))
                        .utilityModule(new UtilityModule(this))
                        .build();
    }
}
