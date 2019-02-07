package com.jevo.alexander.lesson1.model;

public class Weather {

    private String temperature;
    private String pressure;
    private String humidity;
    private String wind;
    private String[] weatherWeek;

    public Weather(String temperature, String pressure, String humidity, String wind, String[] weatherWeek) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.weatherWeek = weatherWeek;
    }

    public String[] getWeatherWeek() {
        return weatherWeek;
    }

    public void setWeatherWeek(String[] weatherWeek) {
        this.weatherWeek = weatherWeek;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
