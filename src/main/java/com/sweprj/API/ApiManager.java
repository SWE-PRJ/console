package com.sweprj.API;

import com.sweprj.util;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;
import java.util.Map;

public class ApiManager {
    public String token;
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

    //post request
    public Mono<Map> post(String uri, Map<String, String> body) {
        return webClient.post()
                .uri(uri)
                .bodyValue(body)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Map.class);
                    }
                    else {
                        // Turn to error
                        return response.createError();
                    }
                });
    }

    //get request
    public Mono<ClientResponse> get(String uri) {
        return webClient.get()
                .uri(uri)
                .exchangeToMono(Mono::just);
    }

    //patch request
    public Mono<ClientResponse> patch(String uri, Map<String, String> body) {
        return webClient.patch()
                .uri(uri)
                .bodyValue(body)
                .exchangeToMono(Mono::just);
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

    void responseProcessing() {

    }
}
