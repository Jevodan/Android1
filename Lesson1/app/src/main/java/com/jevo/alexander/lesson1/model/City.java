package com.jevo.alexander.lesson1.model;

public class City {

    public Weather weather;
    private String city;

    public City(String city, Weather weather) {
        this.weather = weather;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
