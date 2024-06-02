package com.sweprj.API;

import com.sweprj.util;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static com.sweprj.Constant.textColor.exit;
import static com.sweprj.Constant.textColor.red;

public class ApiManager {
    @Getter
    private static final com.sweprj.API.ApiManager instance = new com.sweprj.API.ApiManager();
    private final String baseUrl = "http://localhost:8080";
    @Setter
    private String token;
    final WebClient webClient = WebClient.
            builder()
            .baseUrl(baseUrl)
            .filter(this::addAuthorizationHeader)
            .build();

    private ApiManager() {
    }

    public static void handleException() {
        System.out.println();
        System.out.println(red + "An error occurred. Please try again." + exit);
        util.waitForEnter();
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

    //post request
    public Mono<?> post(String uri, Map<String, String> body, boolean isText) {
        return webClient.post()
                .uri(uri)
                .bodyValue(body)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(isText ? String.class : Map.class);
                    } else {
                        // Turn to error
                        return response.createError();
                    }
                });
    }

    //get request
    public Mono<?> get(String uri, boolean isList) {
        return webClient.get()
                .uri(uri)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(isList ? List.class : Map.class);
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

    //delete request
    public Mono<Map> delete(String uri) {
        return webClient.delete()
                .uri(uri)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Map.class);
                    } else {
                        // Turn to error
                        return response.createError();
                    }
                });
    }
}
