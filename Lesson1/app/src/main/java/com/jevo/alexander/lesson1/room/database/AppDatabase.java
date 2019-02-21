package com.jevo.alexander.lesson1.room.database;

import com.jevo.alexander.lesson1.room.WeatherDao;
import com.jevo.alexander.lesson1.room.model.WeatherBD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherBD.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

}
