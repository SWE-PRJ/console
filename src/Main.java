import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void help(){
        util.clearConsole();
        System.out.println("Available commands:");
        System.out.println("1. 'make project' - Create a new project");
        System.out.println("2. 'travel project' - Move to a project");
        System.out.println("3. 'exit' - Exit the program");
    }
    public static void main(String[] args) throws IOException, InterruptedException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            boolean signedIn = false;
            ApiManager apiManager = ApiManager.getInstance();
            util.clearConsole();
            while (!input.equals("signin") && !input.equals("signup")) {
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
                    User user = new User(username, password);
                    signedIn = apiManager.requestSignin(user);
                } else {
                    System.out.print("Enter username >> ");
                    String username = reader.readLine();
                    System.out.print("Enter password >> ");
                    String password = reader.readLine();
                    User user = new User(username, password);
                    signedIn = apiManager.requestSignup(user);
                }
            }
            //서비스 로직
            while(true) {
                util.clearConsole();
                System.out.println("Welcome to Issue Management!");
                System.out.println("Type 'help' to see the list of commands.");
                System.out.print(">> ");
                input = reader.readLine();

                if (input.equals("exit")) {
                    break;
                }
            }

    }
}