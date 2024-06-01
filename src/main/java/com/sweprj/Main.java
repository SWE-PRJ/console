package com.sweprj;

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

import static com.sweprj.API.LoginAPI.requestRegister;
import static com.sweprj.API.ProjectAPI.browseEntireProjects;
import static com.sweprj.Constant.textColor.exit;
import static com.sweprj.Constant.textColor.green;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static User user = null;

    public static void showDetailIssue(Issue issue) throws IOException, InterruptedException {
        while (true) {
            util.clearConsole();
            System.out.println("Issue Name: " + issue.title);
            System.out.println("Description: " + issue.description);
            System.out.println("Status: " + issue.state);
            System.out.println("Priority: " + issue.priority);
            System.out.println("Comments: ");
            for (int i = 0; i < issue.comments.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + issue.comments.get(i).comment);
            }
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = reader.readLine();
            if (input.equals("help")) {
                util.detailIssueHelp();
            } else if (input.equals("exit")) {
                break;
            } else if (input.equals("add comment")) {

            } else if (input.equals("edit comment")) {

            } else {
                util.wrondCommand();
            }
        }
    }

    public static void travelIssue(Project project) throws IOException, InterruptedException {
        while (true) {
            List<Issue> issues = IssueAPI.requestListOfIssue(project.getId());
            util.clearConsole();
            System.out.println("You are now in the '" + project.Name + "' project.");
            System.out.println("This project has following issues.");
            System.out.println();
            for (int i = 0; i < issues.size(); i++) {
                System.out.println(green + "[" + (i + 1) + "]" + exit + "Issue Name: " + issues.get(i).title + " | Status: " + issues.get(i).state + " | Priority: " + issues.get(i).priority + " | Description: " + issues.get(i).description);
            }
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = Main.reader.readLine();
            if (input.isEmpty()) {
                continue;
            } else if (input.equals("help")) {
                util.issueHelp();
            } else if (input.equals("exit")) {
                break;
            } else if (input.startsWith("goto")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                showDetailIssue(issues.get(index));
            } else if (input.equals("create issue")) {

            } else if (input.startsWith("edit issue")) {

            } else {
                util.wrondCommand();
            }
        }
    }

    private static void travelProject() throws InterruptedException, IOException {
        List<Integer> myProjects = ProjectAPI.requestListOfProject();
        List<Project> projects = new ArrayList<>();
        List<Map<String, Object>> temp = browseEntireProjects();
        while (true) {
            util.clearConsole();
            System.out.println("There is projects as below.");
            System.out.println();
            for (int i = 0; i < temp.size(); i++) {
                if (!myProjects.contains(temp.get(i).get("id"))) continue;
                projects.add(new Project((int) temp.get(i).get("id"), (String) temp.get(i).get("name")));
                System.out.println(green + "[" + (i + 1) + "] " + exit + "Project Name: " + temp.get(i).get("name"));
            }
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = reader.readLine();
            if (input.isEmpty()) {
                continue;
            } else if (input.equals("help")) {
                util.projectsHelp();
            } else if (input.contains("goto")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                travelIssue(projects.get(index));
            } else if (input.equals("exit")) {
                break;
            } else {
                util.wrondCommand();
            }
        }
    }

    public static void makeProject() throws InterruptedException, IOException {
        System.out.print("Please enter the project name >> ");
        String projectName = reader.readLine();
        int member;
        System.out.print("Please enter the number of member you want to invite >> ");
        String memberSTR = reader.readLine();
        member = Integer.parseInt(memberSTR);
        for (int i = 0; i < member; i++) {
            System.out.print("Please enter the member name >> ");
            String memberName = reader.readLine();
            ProjectAPI.requestInviteMember(memberName);
        }
        System.out.println("Creating a new project...");
        ProjectAPI.requestCreateProject(user);
        System.out.println("Project created successfully.");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String input = null;
        boolean signedIn = false;

        //로그인 로직
        while (true) {
            util.clearConsole();
            if (signedIn) break;
            System.out.print("Enter username >> ");
            String identifier = reader.readLine();
            System.out.print("Enter password >> ");
            String password = reader.readLine();
            user = new User(identifier, password);
            signedIn = LoginAPI.requestSignin(user);
        }
        //서비스 로직
        label:
        while (true) {
            util.clearConsole();
            System.out.println("Hello, " + user.getIdentifier() + "!");
            System.out.println("Welcome to Issue Management!");
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            input = reader.readLine();
            switch (input) {
                case "help":
                    util.mainHelp();
                    break;
                case "register user":
                    registerUser();
                    break;
                case "make project"://미완
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
        String username, identifier, password, role;
        util.clearConsole();
        System.out.println("[Register a new user]");
        System.out.print("Please enter the username >> ");
        username = reader.readLine();
        System.out.print("Please enter the identifier >> ");
        identifier = reader.readLine();
        System.out.print("Please enter the password >> ");
        password = reader.readLine();
        System.out.print("Please enter the role('admin', 'pl', 'dev', 'tester') >> ");
        role = reader.readLine();
        User newUser = new User(username, password, identifier);
        requestRegister(newUser, role, user.getIdentifier());
    }
}