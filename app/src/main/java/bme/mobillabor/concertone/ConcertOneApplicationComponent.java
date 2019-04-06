package bme.mobillabor.concertone;

import javax.inject.Singleton;

import bme.mobillabor.concertone.ui.UIModule;
import bme.mobillabor.concertone.ui.list.ConcertListActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface ConcertOneApplicationComponent {
    void inject(ConcertListActivity listActivity);
}
