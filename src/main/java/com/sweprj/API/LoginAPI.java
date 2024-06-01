package com.sweprj.API;

import com.sweprj.Class.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    public static boolean requestSignin(User user) {
        Map<String, String> body = Map.of(
                "identifier", user.getUsername(),
                "password", user.getPassword());
        Map response;
        while (true) {
            try {
                response = apiManager.post("/login", body).block();
                break;
            } catch (Exception e) {
                apiManager.handleException();
            }
        }
        Map<String, String> obj = new HashMap<String,String>();
        for (Object obj_Entry : response.entrySet()) {
            Map.Entry entry = (Map.Entry) obj_Entry; // This will Work Fine all Time.
            obj.put(entry.getKey().toString(), entry.getValue().toString());
        }
        apiManager.token= obj.get("token");
        return true;
    }

    // send the signup request
    public static boolean requestSignup(User user) throws InterruptedException {
        return true;
    }
}
