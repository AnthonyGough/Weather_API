package com.cab302.weather_api;

import java.io.IOException;
import java.lang.module.Configuration;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;


import io.github.cdimascio.dotenv.Dotenv;


public class WeatherAPIClient {

    private String searchValue;

    public WeatherAPIClient(String searchValue) {
        this.searchValue = searchValue;
    }
    public void APIClient() throws IOException{
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIUrlForm()))
                .timeout(Duration.ofSeconds(20))
                .build();
    try {

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }   catch (InterruptedException ex) {
        ex.printStackTrace();
    }
    }


    private  String APIUrlForm() {
        Dotenv dotenv = Dotenv.configure().filename(".env.local").load();
        return generateQuery(dotenv).toString();

    }

    private StringBuilder generateQuery(Dotenv dotenv) {
        StringBuilder sb = new StringBuilder();
        sb.append(dotenv.get("WEATHER_URI"));
        sb.append(dotenv.get("WEATHER_API_KEY"));
        sb.append("&q=");
        sb.append(this.searchValue);
        sb.append("&date=");
        return sb.append(LocalDate.now());
    }

}