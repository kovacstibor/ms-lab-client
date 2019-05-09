package bme.mobillabor.concertone.helper;

import bme.mobillabor.concertone.model.ConcertBaseData;

public class ConcertBaseDataFactory {
    public static ConcertBaseData Create(int id, String artist, String location, String date, double ticketPrice, String genre) {
        ConcertBaseData concertBaseData = new ConcertBaseData();
        concertBaseData.setId(id);
        concertBaseData.setArtist(artist);
        concertBaseData.setLocation(location);
        concertBaseData.setDate(date);
        concertBaseData.setTicketPrice(ticketPrice);
        concertBaseData.setGenre(genre);
        return concertBaseData;
    }
}
