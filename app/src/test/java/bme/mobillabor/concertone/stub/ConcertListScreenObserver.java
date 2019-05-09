package bme.mobillabor.concertone.stub;

import java.util.Collection;

import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.list.ConcertListScreen;

public class ConcertListScreenObserver implements ConcertListScreen {

    private Collection<ConcertBaseData> concerts;

    @Override
    public void showConcerts(Collection<ConcertBaseData> concertsToShow) {
        concerts = concertsToShow;
    }

    public Collection<ConcertBaseData> getConcerts() {
        return concerts;
    }
}
