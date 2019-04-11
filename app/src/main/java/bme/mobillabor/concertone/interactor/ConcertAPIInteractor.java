package bme.mobillabor.concertone.interactor;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.model.ConcertDetailedData;

public class ConcertAPIInteractor {

    @Inject
    public ConcertAPIInteractor() {
    }

    public Collection<ConcertBaseData> getAllConcerts() {
        // TODO: implement
        return new ArrayList<>();
    }

    public Collection<ConcertBaseData> getFilteredConcerts(String filterExpression) {
        // TODO: implement
        return new ArrayList<>();
    }

    public void deleteConcert(int id) {
        // TODO: implement
    }

    public ConcertDetailedData getConcertDetails(int id) {
        // TODO: implement
        return new ConcertDetailedData();
    }
}
