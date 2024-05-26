import java.io.IOException;
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

    public static void projectsHelp() {
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'exit' - Exit the project");
        System.out.println("2. 'details [project index]' - Show the details of the project");
        System.out.println("3. 'edit [project index]' - Edit the project");
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

    public static void projectEdit(int index) throws IOException {
        clearConsole();
        System.out.print("Enter new project name >> ");
        String name = Main.reader.readLine();
        System.out.print("Enter new project description >> ");
        String description = Main.reader.readLine();

    }
}
