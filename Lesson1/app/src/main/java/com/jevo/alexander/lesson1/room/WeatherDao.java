package com.jevo.alexander.lesson1.room;

import com.jevo.alexander.lesson1.room.model.WeatherBD;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WeatherDao {
    @Query("SELECT * FROM WeatherBD")
    List<WeatherBD> getAll();

    @Query("SELECT * FROM WeatherBD WHERE id = :id")
    WeatherBD getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherBD weather);

    @Update
    void update(WeatherBD weather);

    @Delete
    void delete(WeatherBD weather);

}
