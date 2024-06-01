package com.sweprj;

import com.sweprj.API.ApiManager;

import java.io.IOException;
import java.util.Map;

public class util {
    static ApiManager apiManager = ApiManager.getInstance();

    public static void waitForEnter(){
        System.out.print("Press 'enter' to continue..");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void clearConsole() {
        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").contains("Windows")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                processBuilder = new ProcessBuilder("clear");
                Map<String, String> env = processBuilder.environment();
                env.put("TERM", "xterm");
            }
            processBuilder.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void wrondCommand() {
        System.out.println("Wrong command. Try again.");
        waitForEnter();
    }

    public static void mainHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'make project' - Create a new project(Admin only)");
        System.out.println("2. 'register user' - Register a new user(Admin only)");
        System.out.println("3. 'travel project' - Move to a project");
        System.out.println("4. 'exit' - Exit the program");
        waitForEnter();
    }

    public static void projectsHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'exit' - Exit the directory");
        System.out.println("2. 'goto [project index]' - Move to the project to see the issues");
        waitForEnter();
    }

    public static void issueHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'exit' - Exit the project");
        System.out.println("2. 'goto [issue index]' - Move to the issue");
        System.out.println("3. 'create issue' - Create a new issue");
        System.out.println("4. 'edit issue [issue index]' - Edit the issue");
        waitForEnter();
    }

    public static void detailIssueHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'exit' - Exit the issue");
        System.out.println("2. 'edit' - Edit the issue");
        System.out.println("3. 'comment' - Comment the issue");
        waitForEnter();
    }
}
