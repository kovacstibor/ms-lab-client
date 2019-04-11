package bme.mobillabor.concertone.ui.upsert;

import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.IScreen;

public interface ConcertUpsertScreen extends IScreen {
    void fillActualConcertDetails(ConcertDetailedData concertDetails);
    void upsertCompleted(boolean succeeded);
}
