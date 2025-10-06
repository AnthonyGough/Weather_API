package com.softwaredev.weather_api.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent weather information from API - Adjust the instance variables
 * based on the information requirement
 */
public class WeatherData {

    private double currentTemp;
    private String lastUpdateTime;
    private String region;
    private String country;
    private Map<String, Double> temperatureData = new HashMap<>();
    private boolean validData;
    private String errorMessage;
    private String dateOnlyPattern = "dd/MM/yyyy";
    private String hourMinPattern="HH:mm";

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
    public String getLastUpdateTime() {
        return dateConvert();
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

    /**
     * Helper method to format data into format of choice for date/time
     * @return
     */
    private String dateConvert() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.lastUpdateTime, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(dateOnlyPattern);
       return dateTime.format(outputFormatter);


    }
}
