package com.cab302.weather_api;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WeatherController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField searchCityTextField;
    @FXML
    private Button clearButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button exitButton;
    private Stage stage;

    public void setState(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void onClearButtonClick() {
        searchCityTextField.clear();
    }
    @FXML
    protected void onCheckSearchTextFieldStatus() {
        if (searchCityTextField.getText().isEmpty()) {
            clearButton.setDisable(true);
        } else {
            clearButton.setDisable(false);
        }

    }
    @FXML
    protected void onSearchExitTextField() {
        if (searchCityTextField.getText().isEmpty()) {
            clearButton.setDisable(true);
        } else {
            clearButton.setDisable(false);
        }
    }
    @FXML
    public void onSearchButtonClick() {

    }
    @FXML
    public void onExitButtonClick() {
        Platform.exit();
    }


}
