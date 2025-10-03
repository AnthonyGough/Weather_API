package com.softwaredev.weather_api.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastController {
    @FXML
    private Label hourlyModalTime;

    @FXML
    private ImageView hourlyModalImage;

    @FXML
    private Label hourlyModalTemperature;

    public void setInfo(String time, String imageName, String temperature) {
        hourlyModalTime.setText(time);
        hourlyModalImage.setImage(new Image(getClass().getResource("./com/softwaredev/weather_api/medium" + imageName).toExternalForm()));
        hourlyModalTemperature.setText(temperature + "°");
    }
}

