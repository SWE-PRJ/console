package com.sweprj;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiManager {
    private String token;
    private String baseUrl = "http://localhost:8080/";
    private static final ApiManager instance = new ApiManager();

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        return instance;
    }

    private void setupRequest() {
        util.clearConsole();
        System.out.println("Waiting for response...");
    }

    // create a client
    HttpClient client = HttpClient.newHttpClient();

    // send the signin request
    // 로그인 확인 로직 안들어있음 로그인 된 경우에만 true 반환
    boolean requestSignin(User user) throws InterruptedException, IOException {
        //json 생성
//        setupRequest();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(baseUrl + "login"))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString("{\"identifier\":\"" + user.getUsername() + "\",\"password\":\"" + user.getPassword() + "\"}" ))
//                .build();
//        HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response);
//        token=response.body();
//        if(response.statusCode() == 200) {
//            return true;
//        }
//        return false;
        return true;
    }

    // send the signup request
    // 로그인 확인 로직 안들어있음 로그인 된 경우에만 true 반환
    boolean requestSignup(User user) throws InterruptedException {
//        setupRequest();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:3000/User"))
//                .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .join();
        return true;
    }

    boolean requestCreateProject(User user) throws InterruptedException {
//        setupRequest();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:3000/Project"))
//                //.POST(HttpRequest.BodyPublishers.ofString(json))
//                .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .join();
        return true;
    }

    List<Project> requestListOfProject() throws InterruptedException {
//        setupRequest();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:3000/Project"))
//                //.POST(HttpRequest.BodyPublishers.ofString(json))
//                .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .join();
        List<Project> projects = new ArrayList<>();
        Project project1 = new Project("Project1", new Date(), "This is project1");
        Project project2 = new Project("Project2", new Date(), "This is project2");
        projects.add(project1);
        projects.add(project2);
        return projects;
    }

    boolean requestInviteMember(String memberName) throws InterruptedException {
        return false;
    }

    List<Issue> requestListOfIssue(String projectName) throws InterruptedException {
        List<Comment> comments = new ArrayList<>();
        comments = requestListOfComment(projectName, "Issue1");
        List<Issue> issues = new ArrayList<>();
        Issue issue1 = new Issue("Issue1", "This is issue1", new ArrayList<>(), "Open", "High");
        Issue issue2 = new Issue("Issue2", "This is issue2", new ArrayList<>(), "Open", "High");
        issues.add(issue1);
        issues.add(issue2);
        return issues;
    }

    List<Comment> requestListOfComment(String projectName, String issueName) throws InterruptedException {
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment(0, 0, "This is comment1");
        Comment comment2 = new Comment(0, 0, "This is comment2");
        comments.add(comment1);
        comments.add(comment2);
        return comments;
    }
}
