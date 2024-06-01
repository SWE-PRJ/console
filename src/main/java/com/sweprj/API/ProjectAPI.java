package com.sweprj.API;

import com.sweprj.Class.User;

import java.util.List;
import java.util.Map;

public class ProjectAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    public static boolean requestCreateProject(User user) throws InterruptedException {
        return true;
    }

    public static boolean requestInviteMember(String memberName) throws InterruptedException {
        return false;
    }

    public static List<Integer> requestListOfProject() throws InterruptedException {
        List<Integer> projects;
        Map response;
        try {
            response = (Map) apiManager.get("/me", false).block();
        } catch (Exception e) {
            apiManager.handleException();
            throw new InterruptedException();
        }
        projects = (List<Integer>) response.get("projects");
        return projects;
    }

    public static List<Map<String,Object>> browseEntireProjects() throws InterruptedException {
        List<Map<String,Object>> response;
        try {
            response = (List<Map<String, Object>>) apiManager.get("/api/projects", true).block();
        } catch (Exception e) {
            apiManager.handleException();
            throw new InterruptedException();
        }
        return response;
    }
}
