package bme.mobillabor.concertone.ui.list;

import java.util.Collection;

import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.IScreen;

public interface ConcertListScreen extends IScreen {
    void showConcerts(Collection<ConcertBaseData> concertsToShow);
}
