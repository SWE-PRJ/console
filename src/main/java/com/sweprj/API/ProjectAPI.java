package com.sweprj.API;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectAPI {
    static ApiManager apiManager = ApiManager.getInstance();

    public static int requestCreateProject(String projectName) throws InterruptedException {
        Map response;
        try {
            response = (Map) apiManager
                    .post(String.format("/api/projects?name=%s", projectName), new HashMap<>(), false)
                    .block();
        } catch (Exception e) {
            throw new InterruptedException();
        }
        return (int) ((Map<String, Object>) response).get("id");
    }

    public static void requestInviteMember(int projectId, String memberIdentifier) throws InterruptedException {
        try {
            apiManager
                    .post(String.format("/api/projects/%d/%s", projectId, memberIdentifier), new HashMap<>(), true)
                    .block();
        } catch (Exception e) {
            apiManager.handleException();
            throw new InterruptedException();
        }
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

    public static List<Map<String, Object>> browseEntireProjects() throws InterruptedException {
        List<Map<String, Object>> response;
        try {
            response = (List<Map<String, Object>>) apiManager.get("/api/projects", true).block();
        } catch (Exception e) {
            apiManager.handleException();
            throw new InterruptedException();
        }
        return response;
    }
}
