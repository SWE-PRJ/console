package com.sweprj;

import com.sweprj.API.ApiManager;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static com.sweprj.Constant.textColor.*;
import static com.sweprj.Main.reader;

public class util {
    static ApiManager apiManager = ApiManager.getInstance();

    public static void handleNoNumber() {
        System.out.println();
        System.out.println(red + "Please enter a number." + exit);
        waitForEnter();
    }

    public static void waitForEnter() {
        System.out.print("Press 'enter' to continue..");
        try {
            reader.readLine();
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
        System.out.println("1. '" + purple + "make project" + exit + "' - Create a new project" + red + "(Admin only)" + exit);
        System.out.println("2. '" + purple + "register user" + exit + "' - Register a new user" + red + "(Admin only)" + exit);
        System.out.println("3. '" + purple + "travel project" + exit + "' - Move to a project");
        System.out.println("4. '" + purple + "exit" + exit + "' - Exit the program");
        System.out.println();
        waitForEnter();
    }

    public static void projectsHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println();
        System.out.println("1. '" + purple + "goto [project id]" + exit + "' - Move to the project to see the issues");
        System.out.println("2. '" + purple + "exit" + exit + "' - Exit the directory");
        System.out.println();
        waitForEnter();
    }

    public static void issueHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println();
        System.out.println("1. '" + purple + "goto [issue index]" + exit + "' - Move to the issue");
        System.out.println("2. '" + purple + "edit issue [issue index]" + exit + "' - Edit the issue");
        System.out.println("3. '" + purple + "create issue" + exit + "' - Create a new issue");
        System.out.println("4. '" + purple + "exit" + exit + "' - Exit the project");
        System.out.println();
        waitForEnter();
    }

    public static void detailIssueHelp() {
        util.clearConsole();
        System.out.println();
        System.out.println("Available commands:");
        System.out.println("1. '" + purple + "add comment" + exit + "' - Add a comment to the issue");
        System.out.println("2. '" + purple + "edit comment [comment index]" + exit + "' - Edit the comment" + red + "(only admin || writer)" + exit);
        System.out.println("3. '" + purple + "delete comment [comment index]" + exit + "' - Delete the comment" + red + "(only admin || writer)" + exit);
        System.out.println("4. '" + purple + "exit" + exit + "' - Exit the issue");
        System.out.println();
        waitForEnter();
    }
}
