package com.softwaredev.weather_api;

import com.softwaredev.weather_api.controller.WeatherController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("weather-view.fxml"));
            Parent root = fxmlLoader.load();
            WeatherController weatherController = fxmlLoader.getController();
            weatherController.setState(stage);
            Scene scene = new Scene(root, 900, 600);

            stage.setTitle("Temperature History");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {

        System.out.println("Application is stopping gracefully.");
        super.stop(); // Call the superclass's stop method
    }
}
