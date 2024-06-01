package com.sweprj.API;

import com.sweprj.util;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;

public class ApiManager {
    private String token;
    private final String baseUrl = "http://localhost:8080/";
    private static final com.sweprj.API.ApiManager instance = new com.sweprj.API.ApiManager();
    final WebClient webClient = WebClient.
            builder().
            baseUrl(baseUrl).
            build();
    HttpClient client = HttpClient.newHttpClient();
    private ApiManager() {
    }

    public static com.sweprj.API.ApiManager getInstance() {
        return instance;
    }

    private void setupRequest() {
        util.clearConsole();
        System.out.println("Waiting for response...");
    }

    private void clearRequest() {
        System.out.println("Process was done.");
        System.out.println("Press enter to continue...");
        util.waitForEnter();
    }

    void handleException() {
        System.out.println("An error occurred. Please try again.");
        System.out.println("Press enter to continue...");
        util.waitForEnter();
    }
}
