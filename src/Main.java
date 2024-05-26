import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static User user = null;
    static ApiManager apiManager = ApiManager.getInstance();

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

    public static void makeProject() throws InterruptedException, IOException {
        System.out.print("Please enter the project name >> ");
        String projectName = reader.readLine();
        System.out.println("Creating a new project...");
        apiManager.requestCreateProject(user);
        System.out.println("Project created successfully.");
        System.out.print("Please enter the number of member you want to invite >> ");
        int member = Integer.parseInt(reader.readLine());
        for (int i = 0; i < member; i++) {
            System.out.print("Please enter the member name >> ");
            String memberName = reader.readLine();
            apiManager.requestInviteMember(memberName);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String input = "";
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
        while (true) {
            util.clearConsole();
            System.out.println("Welcome to Issue Management!");
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            input = reader.readLine();
            if (input.equals("help")) {
                mainHelp();
            } else if (input.equals("make project")) {
                makeProject();
            } else if (input.equals("travel project")) {
                travelProject();
            } else if (input.equals("exit")) {
                break;
            }
        }
    }

    private static void travelProject() throws InterruptedException, IOException {
        List<Project> projects = apiManager.requestListOfProject();
        while (true) {
            util.clearConsole();
            System.out.println("There is projects as below.");
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + 1 + ")" + "Project Name: " + projects.get(i).Name + " | Created Date: " + projects.get(i).CreatedDate + " | Description: " + projects.get(i).Description);
            }
            System.out.println();
            System.out.println("Please type command. Type 'help' to see the list of commands.");
            System.out.print(">> ");
            String input = reader.readLine();
            if (input.equals("help")) {
                projectsHelp();
            } else if (input.equals("exit")) {
                break;
            } else {
                int projectIndex = Integer.parseInt(input) - 1;
                System.out.println("You are in the project " + projects.get(projectIndex).Name);
                System.out.println("Please type command. Type 'help' to see the list of commands.");
                System.out.print(">> ");
                input = reader.readLine();
                if (input.equals("help")) {
                    mainHelp();
                } else if (input.equals("exit")) {
                    break;
                } else if (input.contains("details")) {
                    System.out.println("Project Name: " + projects.get(projectIndex).Name + " | Created Date: " + projects.get(projectIndex).CreatedDate + " | Description: " + projects.get(projectIndex).Description);
                } else if (input.contains("edit")) {
                    System.out.print("Please enter the new project name >> ");
                    String projectName = reader.readLine();
                    System.out.println("Editing the project...");
                    apiManager.requestEditProject(projectName);
                    System.out.println("Project edited successfully.");
                } else {
                    System.out.println("Wrong input. Try again.");
                }
            }
        }
    }

    private static void projectsHelp() {
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
}