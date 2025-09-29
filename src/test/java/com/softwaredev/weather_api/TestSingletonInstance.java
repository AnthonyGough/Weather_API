package com.softwaredev.weather_api;

import com.softwaredev.weather_api.service.WeatherAPIClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSingletonInstance {

    @Test
    public void testSingletonInstance() throws InterruptedException {
        // Create multiple threads to access the singleton instance concurrently
        Thread thread1 = new Thread(() -> {
            WeatherAPIClient instance1 = WeatherAPIClient.getInstance();
            try {
                Thread.sleep(100); // Simulate some work in the thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WeatherAPIClient instance2 = WeatherAPIClient.getInstance();

            // Assert that both instances obtained in the same thread are the same
            assertEquals(instance1, instance2);
        });

        Thread thread2 = new Thread(() -> {
            WeatherAPIClient instance3 = WeatherAPIClient.getInstance();
            try {
                Thread.sleep(100); // Simulate some work in the thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WeatherAPIClient instance4 = WeatherAPIClient.getInstance();

            // Assert that both instances obtained in the same thread are the same
            assertEquals(instance3, instance4);
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();
    }
}
