import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiManager {//singleton pattern
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
    boolean requestSignin(User user) throws InterruptedException {
        setupRequest();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/User"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        return true;
    }

    // send the signup request
    // 로그인 확인 로직 안들어있음 로그인 된 경우에만 true 반환
    boolean requestSignup(User user) throws InterruptedException {
        setupRequest();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/User"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        return true;
    }

    boolean requestCreateProject(User user) throws InterruptedException {
        setupRequest();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/Project"))
                //.POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
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

    public void requestEditProject(String projectName) {
    }
}
