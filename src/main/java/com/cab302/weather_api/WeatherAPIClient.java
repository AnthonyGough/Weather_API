package com.cab302.weather_api;

import java.io.IOException;
import java.lang.module.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherAPIClient {


    private static final ObjectMapper mapper = new ObjectMapper();
    public static void APIClient() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(APIUrlForm())
                .build(); // defaults to GET
        Response response = client.newCall(request).execute();


        System.out.println(response);
    }


    private static String APIUrlForm() {
        Dotenv dotenv = Dotenv.configure().filename(".env.local").load();
        return dotenv.get("WEATHER_URI") + dotenv.get("WEATHER_API_KEY");

    }

}