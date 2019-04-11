package bme.mobillabor.concertone.interactor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
//        ConcertBaseData sampleConcert = new ConcertBaseData();
//        sampleConcert.setId(3);
//        sampleConcert.setArtist("Iron Maiden");
//        sampleConcert.setDate(new Date(2018, 8, 12));
//        sampleConcert.setLocation("Sopron, VOLT Fesztivál");
//        sampleConcert.setTicketPrice(10000.0d);
//
//
//        ArrayList<ConcertBaseData> concerts = new ArrayList<>();
//        concerts.add(sampleConcert);
//        return concerts;
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

    public boolean updateConcert(int id, ConcertDetailedData updatedConcertDetails) {
        // TODO: implement
        return true;
    }

    public boolean createConcert(ConcertDetailedData newConcertDetails) {
        // TODO: implement
        return true;
    }
}
