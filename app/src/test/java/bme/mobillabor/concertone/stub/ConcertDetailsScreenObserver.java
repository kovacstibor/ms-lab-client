package bme.mobillabor.concertone.stub;

import bme.mobillabor.concertone.model.ConcertDetailedData;
import bme.mobillabor.concertone.ui.details.ConcertDetailsScreen;

public class ConcertDetailsScreenObserver implements ConcertDetailsScreen {

    private ConcertDetailedData concert;

    @Override
    public void showConcertDetails(ConcertDetailedData concertDetails) {
        concert = concertDetails;
    }

    public ConcertDetailedData getConcert() {
        return concert;
    }
}
