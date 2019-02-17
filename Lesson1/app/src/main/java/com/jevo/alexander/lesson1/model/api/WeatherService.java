package com.jevo.alexander.lesson1.model.api;


import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    final static String API_KEY = "99dc4b137e6f0023bc23e58289698119";

    @GET("weather")
    Observable<OneCity> getName(@Query("q") String city, @Query("appid") String app);

}
