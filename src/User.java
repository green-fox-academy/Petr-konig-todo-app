import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class User {

    Scanner scanner = new Scanner(System.in);
    private String userName;
    private String filePath;

    public User() {
        this.userName = readUser();
        this.filePath = "src/textfiles/" + this.userName + "Todos.txt";
    }

    public void handleUser() {
        if (!checkUser()) {
            System.out.print("Please provide your User name: ");
            this.userName = scanner.nextLine();
            writeUser(this.userName);
        }

        filePath = "src/textfiles/" + this.userName + "Todos.txt";
        System.out.println();
        System.out.println("--Authentication finished--");
        System.out.println();
        System.out.println("Welcome " + this.userName);
        validateTodos();
    }

    public void authUser(String authorized) {
        Path userPath = Paths.get("src/textfiles/user.txt");
        try {
            Files.write(userPath, (this.userName + "," + authorized).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuthorized() {
        Path userPath = Paths.get("src/textfiles/user.txt");
        String authorization = "";
        try {
            authorization = Files.readString(userPath);
            authorization = authorization.split(",")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (authorization.equals("authorized"));
    }

    public boolean checkUser() {
        this.userName = readUser();
        if (this.userName.isEmpty()) {
            return false;
        } else if (!isAuthorized()) {
            System.out.print("Are you: " + this.userName + "? (yes/no) ");
            String answer = scanner.nextLine();
            if (answer.equals("yes")) {
                authUser("authorized");
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    public String readUser() {
        Path userPath = Paths.get("src/textfiles/user.txt");
        String userName = null;
        try {
            userName = Files.readString(userPath);
            userName = userName.split(",")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userName;
    }

    public void writeUser(String userName) {
        Path userPath = Paths.get("src/textfiles/user.txt");
        try {
            Files.write(userPath, (userName + ",authorized").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateTodos() {
        System.out.println();
        System.out.println("Validating ToDo lists...");
        if (readTodos()) {
            System.out.println("Validation completed");
            System.out.println();
        } else {
            System.out.println("There is no Todo list");
            File todos = new File(this.filePath);
            try {
                var newFile = todos.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Empty ToDo list has been created");
            System.out.println();
        }
    }

    public boolean readTodos() {
        File todos = new File(this.filePath);
        return todos.exists();
    }

    public String getFilePath() {
        return filePath;
    }
}
