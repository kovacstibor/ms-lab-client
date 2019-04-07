package bme.mobillabor.concertone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ConcertBaseData {
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
}
