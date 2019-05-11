package bme.mobillabor.concertone.helper;

import bme.mobillabor.concertone.model.ConcertDetailedData;

public class ConcertDetailedDataFactory {
    public static ConcertDetailedData Create(int id, String artist, String location, String date, double ticketPrice, String genre, int numberOfFreeSpaces, boolean isAccessible) {
        ConcertDetailedData concertDetailedData = new ConcertDetailedData();
        concertDetailedData.setId(id);
        concertDetailedData.setArtist(artist);
        concertDetailedData.setLocation(location);
        concertDetailedData.setDate(date);
        concertDetailedData.setTicketPrice(ticketPrice);
        concertDetailedData.setGenre(genre);
        concertDetailedData.setNumberOfFreeSpaces(numberOfFreeSpaces);
        concertDetailedData.setIsAccessible(isAccessible);
        return concertDetailedData;
    }
}
