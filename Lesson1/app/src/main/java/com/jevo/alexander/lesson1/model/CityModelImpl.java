package com.jevo.alexander.lesson1.model;

import com.google.gson.Gson;
import com.jevo.alexander.lesson1.model.api.WeatherService;
import com.jevo.alexander.lesson1.model.entity.Cities;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CityModelImpl implements CityModel {

    final private Gson gson = new Gson();
    final private WeatherService api;

    public CityModelImpl(@NonNull WeatherService api) {
        this.api = api;
    }

    @Override
    public Observable<OneCity> retrieveInfo(String city) {
        return api.getName(city, WeatherService.API_KEY).subscribeOn(Schedulers.io());
    }

}
