package com.softwaredev.weather_api.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwaredev.weather_api.model.WeatherData;
import io.github.cdimascio.dotenv.Dotenv;


public class WeatherAPIClient {



    private WeatherAPIClient() {}

    private static class Holder {
        private static final WeatherAPIClient INSTANCE = new WeatherAPIClient();
    }

    public static WeatherAPIClient getInstance() {
        return Holder.INSTANCE;
    }

    public void APIClient(String search) throws IOException {


        MakeAsyncHttpRequest(search)
                .thenAccept(responseBody-> {
                    JsonObject root = JsonParser.parseString(responseBody).getAsJsonObject();

                    // Extract current.temp_c and current.last_updated
                    JsonObject current = root.getAsJsonObject("current");
                    double currentTemp = current.get("temp_c").getAsDouble();
                    String currentTime = current.get("last_updated").getAsString();

                    JsonObject location = root.getAsJsonObject("location");
                    String region = location.get("region").getAsString();
                    String country = location.get("country").getAsString();

                    System.out.println("Current temp_c: " + currentTemp);
                    System.out.println("Current time: " + currentTime);
                    System.out.println("Current region: " + region);
                    System.out.println("Current country: " + country);
                    WeatherData data = new WeatherData(currentTemp, currentTime, region, country);

                    // Extract forecast.hour[].temp_c and time
                    JsonArray forecastDays = root.getAsJsonObject("forecast").getAsJsonArray("forecastday");
                    JsonArray hours = forecastDays.get(0).getAsJsonObject().getAsJsonArray("hour");

                    for (JsonElement hourElement : hours) {
                        JsonObject hour = hourElement.getAsJsonObject();
                        double hourTemp = hour.get("temp_c").getAsDouble();
                        String hourTime = hour.get("time").getAsString();
                        System.out.println("Forecast hour temp_c: " + hourTemp + ", time: " + hourTime);
                        data.setTemperatureData(hourTime, hourTemp);
                    }
                }).join(); // Wait for completion
    }





    public static CompletableFuture<String> MakeAsyncHttpRequest(String search) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIUrlForm(search)))
                .timeout(Duration.ofSeconds(20))
                .build();
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);

    }


    private  static String APIUrlForm(String search) {
        Dotenv dotenv = Dotenv.configure().filename(".env.local").load();
        return generateQuery(dotenv, search).toString();

    }

    private static StringBuilder generateQuery(Dotenv dotenv, String searchValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(dotenv.get("WEATHER_URI"));
        sb.append(dotenv.get("WEATHER_API_KEY"));
        sb.append("&q=");
        sb.append(searchValue);
        return sb.append(dotenv.get("WEATHER_API_PARAMS"));

    }

}