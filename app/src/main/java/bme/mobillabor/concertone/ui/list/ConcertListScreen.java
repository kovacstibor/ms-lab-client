package bme.mobillabor.concertone.ui.list;

import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.IScreen;

public interface ConcertListScreen extends IScreen {
    void showConcerts(Iterable<ConcertBaseData> concertsToShow);
}
