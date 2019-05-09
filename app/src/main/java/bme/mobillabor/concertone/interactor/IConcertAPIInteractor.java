package bme.mobillabor.concertone.interactor;

import java.util.Collection;

import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.model.ConcertDetailedData;

public interface IConcertAPIInteractor {
    Collection<ConcertBaseData> getAllConcerts();
    Collection<ConcertBaseData> getFilteredConcerts(String filterExpression);
    void deleteConcert(int id);
    ConcertDetailedData getConcertDetails(int id);
    boolean updateConcert(int id, ConcertDetailedData updatedConcertDetails);
    boolean createConcert(ConcertDetailedData newConcertDetails);
}
