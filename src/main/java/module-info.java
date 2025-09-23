module com.cab302.weather_api {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires io.github.cdimascio.dotenv.java;
    requires java.net.http;


    opens com.cab302.weather_api to javafx.fxml;
    exports com.cab302.weather_api;
}