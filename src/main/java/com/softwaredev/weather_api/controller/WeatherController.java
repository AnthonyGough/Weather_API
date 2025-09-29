package com.softwaredev.weather_api.controller;

import com.softwaredev.weather_api.service.WeatherAPIClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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

    @FXML
    private List<Label> labelTempList ;
    @FXML
    private List<Label> labelTimeList ;

    private static final String EMPTY_NARRATION_ERROR = "Search Box cannot be empty - select a city";
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
        String input = searchCityTextField.getText();
        if (validInput(input)) {
            invokeSearch(input);
        }
    }

    private boolean validInput(String value) {
        if (value.isEmpty() || value==null) {
            createDialog(EMPTY_NARRATION_ERROR);
            return false;
        }
        return true;
    }

    private void invokeSearch(String search) throws IOException {
        client =  WeatherAPIClient.getInstance();
        client.APIClient(search);
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
