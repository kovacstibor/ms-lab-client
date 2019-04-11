package bme.mobillabor.concertone;

import javax.inject.Singleton;

import bme.mobillabor.concertone.ui.UIModule;
import bme.mobillabor.concertone.ui.details.ConcertDetailsActivity;
import bme.mobillabor.concertone.ui.list.ConcertListActivity;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface ConcertOneApplicationComponent {
    void inject(ConcertListActivity listActivity);
    void inject(ConcertDetailsActivity detailsActivity);
    void inject(ConcertUpsertActivity concertUpsertActivity);
}
