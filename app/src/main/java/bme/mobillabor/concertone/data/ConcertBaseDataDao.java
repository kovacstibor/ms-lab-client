package bme.mobillabor.concertone.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import bme.mobillabor.concertone.model.ConcertBaseData;

@Dao
public interface ConcertBaseDataDao {
    @Query("SELECT * FROM concert_base_data")
    List<ConcertBaseData> getConcerts();

    @Query("SELECT * FROM concert_base_data WHERE artist LIKE :filter")
    List<ConcertBaseData> getFilteredConcerts(String filter);

    @Query("SELECT * FROM concert_base_data WHERE id = :id")
    ConcertBaseData getConcertById(int id);

    @Insert
    void insertConcert(ConcertBaseData... concerts);

    @Update
    void updateConcert(ConcertBaseData modifiedConcertData);

    @Delete
    void deleteConcert(ConcertBaseData concert);
}
