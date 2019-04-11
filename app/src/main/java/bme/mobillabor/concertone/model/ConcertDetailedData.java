package bme.mobillabor.concertone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ConcertDetailedData {
    @SerializedName("Id")
    @Expose
    private int id;

    @SerializedName("Artist")
    @Expose
    private String artist;

    @SerializedName("Date")
    @Expose
    private Date date;

    @SerializedName("Location")
    @Expose
    private String location;

    @SerializedName("TicketPrice")
    @Expose
    private double ticketPrice;

    @SerializedName("NumberOfFreeSpaces")
    @Expose
    private int numberOfFreeSpaces;

    @SerializedName("IsAccessible")
    @Expose
    private boolean isAccessible;

    @SerializedName("Genre")
    @Expose
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getNumberOfFreeSpaces() {
        return numberOfFreeSpaces;
    }

    public void setNumberOfFreeSpaces(int numberOfFreeSpaces) {
        this.numberOfFreeSpaces = numberOfFreeSpaces;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
