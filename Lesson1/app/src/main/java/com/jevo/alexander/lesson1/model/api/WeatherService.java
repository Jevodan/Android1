package com.jevo.alexander.lesson1.model.api;


import com.jevo.alexander.lesson1.model.entity.weather.ListCities;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    final static String API_KEY = "99dc4b137e6f0023bc23e58289698119";
    final static String baseUrl = "https://api.openweathermap.org/data/2.5/";

    @GET("weather?units=metric")
    Observable<OneCity> getCity(
            @Query("q") String city,
            @Query("appid") String app);

    @GET("weather?units=metric")
    Observable<OneCity> getCitiesCoord(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String app);

    @GET("group?units=metric")
    Observable<ListCities> getCities(
            @Query("id") String cities,
            @Query("appid") String app);

}
