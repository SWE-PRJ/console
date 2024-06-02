package com.sweprj;

import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.sweprj.API.CommentAPI;
import com.sweprj.API.IssueAPI;
import com.sweprj.API.LoginAPI;
import com.sweprj.API.ProjectAPI;
import com.sweprj.Class.Issue;
import com.sweprj.Class.Project;
import com.sweprj.Class.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.sweprj.API.ApiManager.handleException;
import static com.sweprj.API.IssueAPI.requestCreateIssue;
import static com.sweprj.API.LoginAPI.requestRegister;
import static com.sweprj.API.ProjectAPI.browseEntireProjects;
import static com.sweprj.API.ProjectAPI.requestCreateProject;
import static com.sweprj.Constant.textColor.*;
import static com.sweprj.util.waitForEnter;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static User user = null;

    public static void showDetailIssue(int projectId, int issueId) throws IOException, InterruptedException {
        while (true) {
            List<Issue> issues = IssueAPI.requestListOfIssue(projectId);
            AtomicReference<Issue> issueRef = new AtomicReference<>();
            Issue issue;
            util.clearConsole();
            issues.stream().filter(issueObj -> issueObj.getId() == issueId).findFirst().ifPresent(issueRef::set);
            issue = issueRef.get();
            System.out.println("Issue Name: " + issue.getTitle());
            System.out.println("Description: " + issue.getDescription());
            System.out.println("Status: " + issue.getState());
            System.out.println("Priority: " + issue.getPriority());
            System.out.println("Comments: ");
            for (int i = 0; i < issue.getComments().size(); i++) {
                System.out.println(green+"└[" + issue.getComments().get(i).getId() + "] " + exit + issue.getComments().get(i).getContent());
            }
            System.out.println("------------------------------------------");
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = reader.readLine();
            if (input.equals("help")) {
                util.detailIssueHelp();
            } else if (input.equals("exit")) {
                break;
            } else if (input.equals("add comment")) {
                System.out.print("Please enter the content of the comment >> ");
                String content = reader.readLine();
                CommentAPI.createComment(issue.getId(), content);
            } else if (input.startsWith("edit comment")) {
                if(input.split(" ").length != 3) {
                    util.handleNoNumber();
                    continue;
                }
                String commentIdSTR = input.split(" ")[2];
                int commentId = Integer.parseInt(commentIdSTR);
                System.out.print("Please enter the new content of the comment >> ");
                String content = reader.readLine();
                CommentAPI.editComment(issueId, commentId, content);
            } else if (input.startsWith("delete comment")) {//완성
                if(input.split(" ").length != 3) {
                    util.handleNoNumber();
                    continue;
                }
                String commentIdSTR = input.split(" ")[2];
                int commentId = Integer.parseInt(commentIdSTR);
                CommentAPI.deleteComment(issueId, commentId);
            } else {
                util.wrondCommand();
            }
        }
    }

    public static void travelIssue(Project project) throws IOException, InterruptedException {
        while (true) {
            List<Issue> issues = IssueAPI.requestListOfIssue(project.getId());
            util.clearConsole();
            System.out.println("You are now in the '" + cyan + project.Name + exit + "' project.");
            System.out.println();
            System.out.println("This project has following issues.");
            System.out.println("------------------------------------------");
            for (int i = 0; i < issues.size(); i++) {
                System.out.println(green + "[" + issues.get(i).getId() + "]" + exit + "Issue Name: " + issues.get(i).getTitle() + " | Status: " + issues.get(i).getState() + " | Priority: " + issues.get(i).getPriority() + " | Description: " + issues.get(i).getDescription());
            }
            System.out.println("------------------------------------------");
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = Main.reader.readLine();
            if (input.isEmpty()) {
                continue;
            } else if (input.equals("help")) {
                util.issueHelp();
            } else if (input.equals("exit")) {
                break;
            } else if (input.startsWith("goto")) {//개발중
                String[] split = input.split(" ");
                if(split.length != 2) {
                    util.handleNoNumber();
                    continue;
                }
                int index = Integer.parseInt(split[1]);
                issues.stream().filter(issue -> issue.getId() == index).findFirst().ifPresent(issue -> {
                    try {
                        showDetailIssue(project.getId(), index);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else if (input.equals("create issue")) {//완료
                System.out.print("Please enter the title of the issue >> ");
                String title = reader.readLine();
                System.out.print("Please enter the description of the issue >> ");
                String description = reader.readLine();
                System.out.print("Please enter the priority of the issue('blocker', 'critical', 'major', 'minor', 'trivial') >> ");
                String priority = reader.readLine();
                Issue issue = new Issue();
                issue.setTitle(title);
                issue.setDescription(description);
                issue.setPriority(priority);

                requestCreateIssue(project.getId(), issue);

            } else if (input.startsWith("edit issue")) {//완료
                if(input.split(" ").length != 3) {
                    util.handleNoNumber();
                    continue;
                }
                //NEW, ASSIGNED, RESOLVED, CLOSED, REOPENED
                System.out.print("Please enter the new state of the issue('NEW', 'ASSIGNED', 'RESOLVED', 'CLOSED', 'REOPENED') >> ");
                String state = reader.readLine();
                String issueIdSTR = input.split(" ")[2];
                int issueId = Integer.parseInt(issueIdSTR);

                IssueAPI.requestChangeState(issueId, state);
            } else {
                util.wrondCommand();
            }
        }
    }

    public static void makeProject() {
        try {
            util.clearConsole();
            System.out.println(blue+"[Create a new project]"+exit);
            System.out.println();
            System.out.print("Please enter the project name >> ");
            String projectName = reader.readLine();
            int projectId = requestCreateProject(projectName);
            ProjectAPI.requestInviteMember(projectId, user.getIdentifier());
            System.out.print("Please enter the number of member you want to invite >> ");
            int member = Integer.parseInt(reader.readLine());
            for (int i = 0; i < member; i++) {
                System.out.print("Please enter the member name >> ");
                String memberName = reader.readLine();
                ProjectAPI.requestInviteMember(projectId, memberName);
            }
            System.out.println();
            System.out.println("Project has been created successfully.");
            waitForEnter();
        } catch (Exception e) {
            handleException();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String input = null;
        boolean signedIn = false;

        //로그인 로직
        while (true) {
            util.clearConsole();
            if (signedIn) break;
            System.out.println("Welcome to " + purple + "Issue Management" + exit + "!");
            System.out.println();
            System.out.print("Enter username >> ");
            String identifier = reader.readLine();
            System.out.print("Enter password >> ");
            String password = reader.readLine();
            user = new User();
            user.setIdentifier(identifier);
            user.setPassword(password);
            signedIn = LoginAPI.requestSignin(user);
        }
        //서비스 로직
        label:
        while (true) {
            util.clearConsole();
            System.out.println("Hello, " + yellow + user.getIdentifier() + exit + "!");
            System.out.println("Welcome to " + purple + "Issue Management" + exit + "!");
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            input = reader.readLine();
            switch (input) {
                case "help":
                    util.mainHelp();
                    break;
                case "register user"://완료
                    registerUser();
                    break;
                case "make project"://완료
                    makeProject();
                    break;
                case "travel project"://개발중
                    travelProject();
                    break;
                case "exit":
                    break label;
                default:
                    util.wrondCommand();
            }
        }
    }

    private static void registerUser() throws IOException, InterruptedException {
        String identifier, password, role;
        util.clearConsole();
        System.out.println(blue+"[Register a new user]"+exit);
        System.out.println();
        System.out.print("Please enter the identifier >> ");
        identifier = reader.readLine();
        System.out.print("Please enter the password >> ");
        password = reader.readLine();
        System.out.print("Please enter the role('admin', 'pl', 'dev', 'tester') >> ");
        role = reader.readLine();
        User newUser = new User();
        newUser.setIdentifier(identifier);
        newUser.setPassword(password);
        requestRegister(newUser, role, user.getIdentifier());
    }

    private static void travelProject() throws InterruptedException, IOException {
        List<Integer> myProjects = ProjectAPI.requestListOfProject();
        List<Project> projects = new ArrayList<>();
        List<Map<String, Object>> temp = browseEntireProjects();
        while (true) {
            util.clearConsole();
            System.out.println("You are now in the " + cyan + "Projects" + exit + " directory.");
            System.out.println();
            System.out.println("There is projects as below.");
            System.out.println("------------------------------------------");
            for (int i = 0; i < temp.size(); i++) {
                if (!myProjects.contains(temp.get(i).get("id"))) continue;
                Project project = new Project();
                project.setId((int) temp.get(i).get("id"));
                project.setName((String) temp.get(i).get("name"));
                projects.add(project);
                System.out.println(green + "[" + temp.get(i).get("id") + "] " + exit + "Project Name: " + temp.get(i).get("name"));
            }
            System.out.println("------------------------------------------");
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = reader.readLine();
            if (input.isEmpty()) {
                continue;
            } else if (input.equals("help")) {
                util.projectsHelp();
            } else if (input.startsWith("goto")) {
                String[] split = input.split(" ");
                if(split.length != 2) {
                    util.handleNoNumber();
                    continue;
                }
                int index = Integer.parseInt(split[1]);
                projects.stream().filter(project -> project.getId() == index).findFirst().ifPresent(project -> {
                    try {
                        travelIssue(project);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else if (input.equals("exit")) {
                break;
            } else {
                util.wrondCommand();
            }
        }
    }
}