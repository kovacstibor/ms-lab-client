package bme.mobillabor.concertone;

import javax.inject.Singleton;

import bme.mobillabor.concertone.api.ApiModule;
import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.ui.UIModule;
import bme.mobillabor.concertone.ui.details.ConcertDetailsActivity;
import bme.mobillabor.concertone.ui.list.ConcertListActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, ApiModule.class})
public interface ConcertOneApplicationComponent {
    void inject(ConcertListActivity listActivity);
    void inject(ConcertDetailsActivity detailsActivity);
    void inject(ConcertAPIInteractor concertAPIInteractor);

}
