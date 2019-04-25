package bme.mobillabor.concertone;

import javax.inject.Singleton;

import bme.mobillabor.concertone.data.DataModule;
import bme.mobillabor.concertone.interactor.ConcertAPIInteractor;
import bme.mobillabor.concertone.ui.UIModule;
import bme.mobillabor.concertone.ui.details.ConcertDetailsActivity;
import bme.mobillabor.concertone.ui.list.ConcertListActivity;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertActivity;
import bme.mobillabor.concertone.utility.UtilityModule;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, DataModule.class, UtilityModule.class})
public interface ConcertOneApplicationComponent {
    void inject(ConcertListActivity listActivity);
    void inject(ConcertDetailsActivity detailsActivity);
    void inject(ConcertAPIInteractor concertAPIInteractor);
    void inject(ConcertUpsertActivity concertUpsertActivity);
}
