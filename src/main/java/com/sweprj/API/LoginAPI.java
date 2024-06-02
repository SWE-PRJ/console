package com.sweprj.API;

import com.sweprj.Class.User;

import java.util.HashMap;
import java.util.Map;

public class LoginAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    // send the signin request
    public static boolean requestSignin(User user) {
        Map<String, String> body = Map.of(
                "identifier", user.getIdentifier(),
                "password", user.getPassword());
        Map response;
        try {
            response = apiManager.post("/login", body).block();
        } catch (Exception e) {
            apiManager.handleException();
            return false;
        }
        Map<String, String> obj = new HashMap<String, String>();
        for (Object obj_Entry : response.entrySet()) {
            Map.Entry entry = (Map.Entry) obj_Entry;
            obj.put(entry.getKey().toString(), entry.getValue().toString());
        }
        apiManager.setToken(obj.get("token"));
        return true;
    }

    // send the register request
    public static void requestRegister(User user, String role, String adminIdentifier) throws InterruptedException {
        Map<String, String> body = Map.of(
                "identifier", user.getIdentifier(),
                "password", user.getPassword());
        Map response;
        try {
            response = apiManager
                    .post(String.format("/admin/register?role=%s&adminIdentifier=%s", role, adminIdentifier), body)
                    .block();
        } catch (Exception e) {
            apiManager.handleException();
        }
    }
}
