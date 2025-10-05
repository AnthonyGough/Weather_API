package com.softwaredev.weather_api.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwaredev.weather_api.model.WeatherData;
import io.github.cdimascio.dotenv.Dotenv;



/**
 * Class is responsible for the creation of a HTTP Request to the https://www.weatherapi.com/ collection of
 * Weather API's. The request is made asynchronously (doesn’t wait for the response, non-blocking).
 * A CompletableFeature<HttpResponse>  is returned to process a request asynchronously.
 * Initializes the instance on the first calling of getInstance() and INSTANCE is final
 **/

public class WeatherAPIClient {


    /**
     * Private constructor to enforce Initialization-on-Demand Holder Idiom -
     * Thread-safe way to lazily initialize a singleton using a static inner class
     *
     */
    private WeatherAPIClient() {}

    /**
     * The Holder class                                                                                                                                                                                     oaded only when getInstance() is called
     * Lazy initialization. The internal implementation of the JVM guarantees
     * that the initialization of the SingletonHolder class is done synchronously.
     * After the initialization, no synchronization is needed because the instance variable
     * is static final
     */
    private static class Holder {
        private static final WeatherAPIClient INSTANCE = new WeatherAPIClient();
    }

    /**
     * Gets the instance of the WeatherAPI Client as a Singleton
     * @return WeatherAPIClient singleton instance
     */
    public static WeatherAPIClient getInstance() {
            return Holder.INSTANCE;
    }



    public WeatherData APIClient(String search)  {
        AtomicReference<WeatherData> data = new AtomicReference<>(new WeatherData());
        try {
            CompletableFuture<String> future = MakeAsyncHttpRequest(search);
                    future.thenAccept(responseBody -> {
                        JsonObject root = JsonParser.parseString(responseBody).getAsJsonObject();
                        if (root.has("error")) {
                            JsonObject errorObj = root.getAsJsonObject("error");
                            int errorCode = errorObj.get("code").getAsInt();
                            String errorMessage = "Error Code: " + errorObj.get("code").getAsInt() +"\nError Message: " + errorObj.get("message").getAsString();
                            data.set(new WeatherData(false, errorMessage));

                        } else {

                            JsonObject current = root.getAsJsonObject("current");
                            double currentTemp = current.get("temp_c").getAsDouble();
                            String currentTime = current.get("last_updated").getAsString();

                            JsonObject location = root.getAsJsonObject("location");
                            String region = location.get("region").getAsString();
                            String country = location.get("country").getAsString();

                            data.set(new WeatherData(currentTemp, currentTime, region, country,true));


                            System.out.println("Country = " + country + "\nRegion: " + region + "\nValid: " + data.get().getValidData());
                            // Extract forecast.hour[].temp_c and time
                            JsonArray forecastDays = root.getAsJsonObject("forecast").getAsJsonArray("forecastday");
                            JsonArray hours = forecastDays.get(0).getAsJsonObject().getAsJsonArray("hour");

                            for (JsonElement hourElement : hours) {
                                JsonObject hour = hourElement.getAsJsonObject();
                                double hourTemp = hour.get("temp_c").getAsDouble();
                                String hourTime = hour.get("time").getAsString();
                                System.out.println("Forecast hour temp_c: " + hourTemp + ", time: " + hourTime);
                                data.get().setTemperatureData(hourTime, hourTemp);
                            }

                        }
                    }).join();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return data.get();
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