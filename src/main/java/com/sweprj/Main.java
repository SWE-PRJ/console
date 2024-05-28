package com.sweprj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static User user = null;
    static ApiManager apiManager = ApiManager.getInstance();

    public static void showDetailIssue(Issue issue) throws IOException, InterruptedException {
        while (true) {
            util.clearConsole();
            System.out.println("Issue Name: " + issue.title);
            System.out.println("Description: " + issue.description);
            System.out.println("Status: " + issue.status);
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
            List<Issue> issues = apiManager.requestListOfIssue(project.Name);
            util.clearConsole();
            System.out.println("You are now in the '" + project.Name + "' project.");
            System.out.println("There is issues as below.");
            for (int i = 0; i < issues.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + "Issue Name: " + issues.get(i).title + " | Status: " + issues.get(i).status + " | Priority: " + issues.get(i).priority + " | Description: " + issues.get(i).description);
            }
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = Main.reader.readLine();
            if(input.isEmpty()){
                continue;
            }
            else if (input.equals("help")) {
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
        List<Project> projects = apiManager.requestListOfProject();
        while (true) {
            util.clearConsole();
            System.out.println("There is projects as below.");
            for (int i = 0; i < projects.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + "Project Name: " + projects.get(i).Name + " | Created Date: " + projects.get(i).CreatedDate + " | Description: " + projects.get(i).Description);
            }
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = reader.readLine();
            if(input.isEmpty()){
                continue;
            }
            else if (input.equals("help")) {
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
            apiManager.requestInviteMember(memberName);
        }
        System.out.println("Creating a new project...");
        apiManager.requestCreateProject(user);
        System.out.println("Project created successfully.");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String input = null;
        boolean signedIn = false;

        util.clearConsole();
        System.out.print("Enter 'signin' or 'signup' >> ");
        input = reader.readLine();
        while (!input.equals("signin") && !input.equals("signup")) {
            util.clearConsole();
            System.out.println("Wrong input. Try again.");
            System.out.print("Enter 'signin' or 'signup' >> ");
            input = reader.readLine();
        }
        //로그인/회원가입 로직
        while (true) {
            util.clearConsole();
            if (signedIn) {
                System.out.println("Signed in...");
                break;
            }
            if (input.equals("signin")) {
                System.out.print("Enter username >> ");
                String username = reader.readLine();
                System.out.print("Enter password >> ");
                String password = reader.readLine();
                user = new User(username, password);
                signedIn = apiManager.requestSignin(user);
            } else {
                System.out.print("Enter username >> ");
                String username = reader.readLine();
                System.out.print("Enter password >> ");
                String password = reader.readLine();
                user = new User(username, password);
                signedIn = apiManager.requestSignup(user);
            }
        }
        //서비스 로직
        label:
        while (true) {
            util.clearConsole();
            System.out.println("Welcome to Issue Management!");
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            input = reader.readLine();
            switch (input) {
                case "help":
                    util.mainHelp();
                    break;
                case "make project":
                    makeProject();
                    break;
                case "travel project":
                    travelProject();
                    break;
                case "exit":
                    break label;
            }
        }
    }
}