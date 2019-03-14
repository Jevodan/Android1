package com.jevo.alexander.lesson1.model;

import com.jevo.alexander.lesson1.model.entity.weather.ListCities;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;

import io.reactivex.Observable;

public interface CityModel {

    Observable<OneCity> retrieveInfo(String city);
    Observable<ListCities> retrieveInfo();
    Observable<OneCity> retrieveInfoCoord(double lat, double lon);
    void load(int count, int countItems);
}
