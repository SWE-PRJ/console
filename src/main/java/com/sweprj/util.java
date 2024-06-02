package com.sweprj;

import com.sweprj.API.ApiManager;

import java.io.IOException;
import java.util.Map;

import static com.sweprj.Constant.textColor.exit;
import static com.sweprj.Constant.textColor.red;

public class util {
    static ApiManager apiManager = ApiManager.getInstance();

    public static void waitForEnter() {
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
        System.out.println();
        System.out.println(red + "Wrong command. Try again." + exit);
        waitForEnter();
    }

    public static void mainHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println();
        System.out.println("1. 'make project' - Create a new project" + red + "(Admin only)" + exit);
        System.out.println("2. 'register user' - Register a new user" + red + "(Admin only)" + exit);
        System.out.println("3. 'travel project' - Move to a project");
        System.out.println("4. 'exit' - Exit the program");
        System.out.println();
        waitForEnter();
    }

    public static void projectsHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println();
        System.out.println("1. 'goto [project id]' - Move to the project to see the issues");
        System.out.println("2. 'exit' - Exit the directory");
        System.out.println();
        waitForEnter();
    }

    public static void issueHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println();
        System.out.println("1. 'goto [issue index]' - Move to the issue");
        System.out.println("2. 'edit issue [issue index]' - Edit the issue");
        System.out.println("3. 'create issue' - Create a new issue");
        System.out.println("4. 'exit' - Exit the project");
        System.out.println();
        waitForEnter();
    }

    public static void detailIssueHelp() {
        util.clearConsole();
        System.out.println();
        System.out.println("Available commands:");
        System.out.println("1. 'add comment' - Add a comment to the issue");
        System.out.println("2. 'edit comment [comment index]' - Edit the comment" + red + "(only admin || writer)" + exit);
        System.out.println("3. 'delete comment [comment index]' - Delete the comment" + red + "(only admin || writer)" + exit);
        System.out.println("4. 'exit' - Exit the issue");
        System.out.println();
        waitForEnter();
    }
}
