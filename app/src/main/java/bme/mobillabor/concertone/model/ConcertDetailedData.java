/**
 * ConcertOne
 * This is a simple sample server for the basic CRUD operations about concert events. The server was made only for the homework of [Mobile Software Laboratory](https://www.aut.bme.hu/Course/VIAUMB02) course at BME.
 * <p>
 * OpenAPI spec version: 1.0.0
 * Contact: kovacs.tibor@outlook.hu
 * <p>
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package bme.mobillabor.concertone.model;

import com.google.gson.annotations.SerializedName;

public class ConcertDetailedData {
    @SerializedName("id")
    private Integer id = null;

    @SerializedName("artist")
    private String artist = null;

    @SerializedName("date")
    private String date = null;

    @SerializedName("location")
    private String location = null;

    @SerializedName("ticketPrice")
    private Double ticketPrice = null;

    @SerializedName("numberOfFreeSpaces")
    private Integer numberOfFreeSpaces = null;

    @SerializedName("isAccessible")
    private Boolean isAccessible = null;

    @SerializedName("genre")
    private String genre = null;

    /**
     * The unique identifier of the concert.
     * minimum: 0
     **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The artist of the concert.
     **/
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * The day of the concert.
     **/
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * The location of the concert.
     **/
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * The unit price of a ticket for the concert.
     * minimum: 0
     * maximum: 100000
     **/
    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * The number of available free spaces for the concert.
     * minimum: 1
     **/
    public Integer getNumberOfFreeSpaces() {
        return numberOfFreeSpaces;
    }

    public void setNumberOfFreeSpaces(Integer numberOfFreeSpaces) {
        this.numberOfFreeSpaces = numberOfFreeSpaces;
    }

    /**
     * Whether the concert location is accessible or not.
     **/
    public Boolean getIsAccessible() {
        return isAccessible;
    }

    public void setIsAccessible(Boolean isAccessible) {
        this.isAccessible = isAccessible;
    }

    /**
     * The genre of the music played on the concert.
     **/
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConcertDetailedData concertDetailedData = (ConcertDetailedData) o;
        return (this.id == null ? concertDetailedData.id == null : this.id.equals(concertDetailedData.id)) &&
                (this.artist == null ? concertDetailedData.artist == null : this.artist.equals(concertDetailedData.artist)) &&
                (this.date == null ? concertDetailedData.date == null : this.date.equals(concertDetailedData.date)) &&
                (this.location == null ? concertDetailedData.location == null : this.location.equals(concertDetailedData.location)) &&
                (this.ticketPrice == null ? concertDetailedData.ticketPrice == null : this.ticketPrice.equals(concertDetailedData.ticketPrice)) &&
                (this.numberOfFreeSpaces == null ? concertDetailedData.numberOfFreeSpaces == null : this.numberOfFreeSpaces.equals(concertDetailedData.numberOfFreeSpaces)) &&
                (this.isAccessible == null ? concertDetailedData.isAccessible == null : this.isAccessible.equals(concertDetailedData.isAccessible)) &&
                (this.genre == null ? concertDetailedData.genre == null : this.genre.equals(concertDetailedData.genre));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
        result = 31 * result + (this.artist == null ? 0 : this.artist.hashCode());
        result = 31 * result + (this.date == null ? 0 : this.date.hashCode());
        result = 31 * result + (this.location == null ? 0 : this.location.hashCode());
        result = 31 * result + (this.ticketPrice == null ? 0 : this.ticketPrice.hashCode());
        result = 31 * result + (this.numberOfFreeSpaces == null ? 0 : this.numberOfFreeSpaces.hashCode());
        result = 31 * result + (this.isAccessible == null ? 0 : this.isAccessible.hashCode());
        result = 31 * result + (this.genre == null ? 0 : this.genre.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConcertDetailedData {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  artist: ").append(artist).append("\n");
        sb.append("  date: ").append(date).append("\n");
        sb.append("  location: ").append(location).append("\n");
        sb.append("  ticketPrice: ").append(ticketPrice).append("\n");
        sb.append("  numberOfFreeSpaces: ").append(numberOfFreeSpaces).append("\n");
        sb.append("  isAccessible: ").append(isAccessible).append("\n");
        sb.append("  genre: ").append(genre).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
