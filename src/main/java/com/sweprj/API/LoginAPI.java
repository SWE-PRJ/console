package com.sweprj.API;

import com.sweprj.Class.User;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public class LoginAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    public static boolean requestSignin(User user) {
        Map<String, String> body = Map.of(
                "identifier", user.getUsername(),
                "password", user.getPassword());
        Mono<ClientResponse> responseMono;
        while (true) {
            try {
                responseMono = apiManager.webClient.post()
                        .uri("/login")
                        .bodyValue(body)
                        .exchangeToMono(Mono::just);
                break;
            } catch (Exception e) {
                apiManager.handleException();
            }
        }
        ClientResponse response = responseMono.block();
        if (!(response != null && response.statusCode().is2xxSuccessful()))
            return false;
        else {
            System.out.println(response.body(BodyExtractors.toMono(String.class)).block());
            return true;
        }
    }

    // send the signup request
    public static boolean requestSignup(User user) throws InterruptedException {
        return true;
    }
}
