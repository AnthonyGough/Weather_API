module com.softwaredev.weather_api {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires io.github.cdimascio.dotenv.java;
    requires java.net.http;
    requires com.google.gson;


    opens com.softwaredev.weather_api to javafx.fxml;
    exports com.softwaredev.weather_api;
    exports com.softwaredev.weather_api.service;
    opens com.softwaredev.weather_api.service to javafx.fxml;
    exports com.softwaredev.weather_api.controller;
    opens com.softwaredev.weather_api.controller to javafx.fxml;
}