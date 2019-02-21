package com.jevo.alexander.lesson1.room.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeatherBD {

    @PrimaryKey
    public long id;

    public String name;
    public Double tempMin;
    public Double tempMax;
    public String desc;
    public String icon;
    public Float pressure;
    public int humidity;
    public Float wind;

}