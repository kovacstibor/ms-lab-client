package bme.mobillabor.concertone.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import bme.mobillabor.concertone.model.ConcertBaseData;

@Database(entities = {ConcertBaseData.class}, version = 1, exportSchema = false)
public abstract class AConcertDb extends RoomDatabase implements ConcertDb {
    public abstract ConcertBaseDataDao concertBaseDataDao();
}