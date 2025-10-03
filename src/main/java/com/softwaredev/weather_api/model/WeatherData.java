package com.softwaredev.weather_api.model;


import java.util.HashMap;
import java.util.Map;

public class WeatherData {

    private double currentTemp;
    private String lastUpdateTime;
    private String region;
    private String country;
    private Map<String, Double> temperatureData = new HashMap<>();

    public WeatherData(){}
    public WeatherData(double currentTemp, String lastUpdateTime, String region, String country) {
        this.currentTemp=currentTemp;
        this.lastUpdateTime=lastUpdateTime;
        this.region=region;
        this.country=country;
    }

    public void setTemperatureData(String hour, Double currentTemp) {
        temperatureData.put(hour, currentTemp);
    }
    public void setCurrentTemp(double value) {
        this.currentTemp = value;
    }
    public double getCurrentTemp() {
        return this.currentTemp ;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public void setRegion(String value) {
        this.region = value;
    }
    public void setLastUpdateTime(String value) {
        this.lastUpdateTime=value;
    }

    public String getCountry() {
        return this.country;
    }
    public String getRegion() {
        return this.region;
    }
}
