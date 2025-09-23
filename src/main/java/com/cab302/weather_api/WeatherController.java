package com.cab302.weather_api;

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

    private static final String EMPTY_NARRATION_ERROR = "Search Box cannot be empty - select a city";
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
    public void onSearchButtonClick() throws IOException {
        if (searchCityTextField.getText().isEmpty()) {
            createDialog(EMPTY_NARRATION_ERROR);
        } else {
            WeatherAPIClient client = new WeatherAPIClient(searchCityTextField.getText());
            client.APIClient();
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
