package com.softwaredev.weather_api.model;


import java.util.HashMap;
import java.util.Map;

public class WeatherData {

    private double currentTemp;
    private String lastUpdateTime;
    private String region;
    private String country;
    private Map<String, Double> temperatureData = new HashMap<>();
    private boolean validData;
    private String errorMessage;

    public WeatherData(){}

    public WeatherData(boolean validData, String errorMessage) {
        this.validData = validData;
        this.errorMessage = errorMessage;
    }
    public WeatherData(double currentTemp, String lastUpdateTime, String region, String country, boolean validData) {
        this.currentTemp=currentTemp;
        this.lastUpdateTime=lastUpdateTime;
        this.region=region;
        this.country=country;
        this.validData=validData;
    }

    public void setTemperatureData(String hour, Double currentTemp) {
        temperatureData.put(hour, currentTemp);
    }
    public Map<String, Double> getTemperatureData() {
        return this.temperatureData;
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
    public boolean getValidData() { return this.validData; }
    public void setValidData(boolean value) {
        this.validData = value;
    }
}
