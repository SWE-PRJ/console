package com.sweprj.API;

import com.sweprj.util;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class ApiManager {
    private String token;
    private final String baseUrl = "http://localhost:8080";
    private static final com.sweprj.API.ApiManager instance = new com.sweprj.API.ApiManager();
    final WebClient webClient = WebClient.
            builder()
            .baseUrl(baseUrl)
            .filter(this::addAuthorizationHeader)
            .build();

    public void setToken(String token) {
        this.token = token;
    }

    private Mono<ClientResponse> addAuthorizationHeader(ClientRequest request, ExchangeFunction next) {
        if (token == null) {
            return next.exchange(request);
        }
        ClientRequest authorizedRequest = ClientRequest.from(request)
                .header("Authorization", "Bearer " + token)
                .build();
        return next.exchange(authorizedRequest);
    }

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
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Map.class);
                    } else {
                        // Turn to error
                        return response.createError();
                    }
                });
    }

    //get request
    public Mono<?> get(String uri,boolean isList) {
        return webClient.get()
                .uri(uri)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(isList ? List.class :Map.class);
                    } else {
                        // Turn to error
                        return response.createError();
                    }
                });
    }

    //patch request
    public Mono<Map> patch(String uri, Map<String, String> body) {
        return webClient.patch()
                .uri(uri)
                .bodyValue(body)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Map.class);
                    } else {
                        // Turn to error
                        return response.createError();
                    }
                });
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
        util.waitForEnter();
    }

    void responseProcessing() {

    }
}
