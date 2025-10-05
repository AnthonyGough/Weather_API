package com.softwaredev.weather_api.controller;

import com.softwaredev.weather_api.model.WeatherData;
import com.softwaredev.weather_api.service.WeatherAPIClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import javafx.scene.shape.Sphere;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;






public class WeatherController  {

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
    @FXML
    private Label region;
    @FXML
    private Label country;

    @FXML

    private static final String INVALID_NARRATION_ERROR = "Search must be letters only and cannot be empty";
    private static final String INVALID_RESULT = "Location Unknown";

    @FXML
    public void initialize() {
        country.setWrapText(true);
        region.setWrapText(true);
        searchCityTextField.setFocusTraversable(false);

    }

    @FXML
    public void searchWeather(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER) {

           processQuery();

        }
    }
    public void searchCity()  {
        try {
            processQuery();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void displayWeather(WeatherData weatherData) {
        country.setText(weatherData.getCountry());
        region.setText(weatherData.getRegion());
    }


    public void setState(Stage stage) {
        this.stage = stage;
    }
    private static WeatherAPIClient client;
    @FXML
    protected void onClearButtonClick() {
        searchCityTextField.clear();
    }



    @FXML
    public void onSearchButtonClick() throws IOException {
        processQuery();
    }

    private void processQuery() throws IOException {
        String input = searchCityTextField.getText();
        if (validInput(input)) {
            invokeSearch(input);
        }
    }





    private boolean validInput(String value) {
        if (value.isEmpty() || !isLettersOnly(value) ) {
            createDialog(INVALID_NARRATION_ERROR);
            return false;
        }
        return true;
    }

    private void invokeSearch(String search) throws IOException {
        client =  WeatherAPIClient.getInstance();
        WeatherData data =client.APIClient(search);

        displayWeather(data);
    }

    public static boolean isLettersOnly(String input) {
        return input.matches("^[A-Za-z]+$");
    }



    @FXML
    private void updateBasicInfo(WeatherData data) {
        System.out.println("Data is " + data.getValidData());
        if (data.getValidData()) {
            country.setText(data.getCountry());
            region.setText(data.getRegion());
        } else {
            country.setText(INVALID_RESULT);
            region.setText("");
        }
    }

    @FXML
    public void onExitButtonClick() {
        Platform.exit();
    }

    public static void createDialog(String text) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(text);

        VBox layout = new VBox(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 100);

        stage.setTitle("Dialog");
        stage.setScene(scene);
        stage.show();
    }




}
