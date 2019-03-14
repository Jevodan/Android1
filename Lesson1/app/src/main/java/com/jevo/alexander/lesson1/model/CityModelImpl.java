package com.jevo.alexander.lesson1.model;

import android.os.Build;
import android.util.Log;

import com.jevo.alexander.lesson1.model.api.WeatherService;
import com.jevo.alexander.lesson1.model.entity.Cities;
import com.jevo.alexander.lesson1.model.entity.Coords;
import com.jevo.alexander.lesson1.model.entity.weather.Coord;
import com.jevo.alexander.lesson1.model.entity.weather.ListCities;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.RequiresApi;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class CityModelImpl implements CityModel {


    final private WeatherService api;
    private List<String> cities = new ArrayList<String>();

    public CityModelImpl(@NonNull WeatherService api) {
        this.api = api;
    }

    @Override
    public Observable<OneCity> retrieveInfo(String citySearch) {
        return api.getCity(citySearch, WeatherService.API_KEY).subscribeOn(Schedulers.io());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Observable<OneCity> retrieveInfoCoord(double lat, double lon) {
        return api.getCitiesCoord(lat, lon , WeatherService.API_KEY).subscribeOn(Schedulers.io());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Observable<ListCities> retrieveInfo() {
        return api.getCities(String.join(",", cities), WeatherService.API_KEY).subscribeOn(Schedulers.io());
    }

    @Override
    public void load(int start, int countItems) {
        cities = new ArrayList<>();
        for (int i = start; i < start + countItems; i++) {
            if (Cities.checkLength(i)) continue;
            cities.add(Cities.cities[i]);
        }
    }

}
