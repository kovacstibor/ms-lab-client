package bme.mobillabor.concertone.ui.details;

import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.IScreen;

public interface ConcertDetailsScreen extends IScreen {
    void showConcertDetails(ConcertDetailedData concertDetails);
}
