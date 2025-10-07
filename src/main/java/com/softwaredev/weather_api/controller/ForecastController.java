package com.softwaredev.weather_api.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the individual hourly data display
 */
public class ForecastController {
    @FXML
    private Label hourlyModalTime;

    @FXML
    private ImageView hourlyModalImage;

    @FXML
    private Label hourlyModalTemperature;

    public void setInfo(String time,  Image image, String temperature) {
        hourlyModalTime.setText(time);
        hourlyModalImage.setImage(image);
        hourlyModalTemperature.setText(temperature + "°");
    }
}

