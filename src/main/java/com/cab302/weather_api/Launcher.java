package com.cab302.weather_api;

import javafx.application.Application;
import io.github.cdimascio.dotenv.*;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {

    WeatherAPIClient.APIClient();


        Application.launch(WeatherApplication.class, args);
    }
}