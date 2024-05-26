import java.io.IOException;
import java.util.List;
import java.util.Map;

public class util {
    static ApiManager apiManager = ApiManager.getInstance();

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

    public static void mainHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'make project' - Create a new project(Admin only)");
        System.out.println("2. 'travel project' - Move to a project");
        System.out.println("3. 'exit' - Exit the program");
        while (true) {
            System.out.print("Press 'enter' to go back.. ");
            try {
                System.in.read();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void projectsHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'exit' - Exit the directory");
        System.out.println("2. 'goto [project index]' - Move to the project to see the issues");
        while (true) {
            System.out.print("Press 'enter' to go back.. ");
            try {
                System.in.read();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void issueHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'exit' - Exit the project");
        System.out.println("2. 'goto [issue index]' - Move to the issue");
        System.out.println("3. 'create issue' - Create a new issue");
        System.out.println("4. 'edit issue [issue index]' - Edit the issue");
        while (true) {
            System.out.print("Press 'enter' to go back.. ");
            try {
                System.in.read();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void gotoProject(Project project) throws IOException, InterruptedException {
        while (true) {
            List<Issue> issues = apiManager.requestListOfIssue(project.Name);
            clearConsole();
            System.out.println("You are now in the '" + project.Name + "' project.");
            System.out.println("There is issues as below.");
            for (int i = 0; i < issues.size(); i++) {
                System.out.println(i + 1 + ")" + "Issue Name: " + issues.get(i).title + " | Status: " + issues.get(i).status + " | Priority: " + issues.get(i).priority + " | Description: " + issues.get(i).description);
            }
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = Main.reader.readLine();
            if (input.equals("help")) {
                issueHelp();
            } else if (input.equals("exit")) {
                break;
            } else if (input.startsWith("goto")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                //util.gotoIssue(project.Issues.get(index));
            }
        }
    }
}
