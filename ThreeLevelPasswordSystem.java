import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

class User {
    private String username;
    private String hashedPassword;  // Store the hashed password
    private List<String> graphicalPassword;  // List of image identifiers
    private String securityQuestion;
    private String securityAnswer;

    public User(String username, String password, List<String> graphicalPassword, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.hashedPassword = hashPassword(password);  // Hash the password upon creation
        this.graphicalPassword = graphicalPassword;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getUsername() {
        return username;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public boolean verifyPassword(String password) {
        return hashedPassword.equals(hashPassword(password));
    }

    public boolean verifyGraphicalPassword(List<String> graphicalPasswordInput) {
        return graphicalPassword.equals(graphicalPasswordInput);
    }

    public boolean verifySecurityAnswer(String answer) {
        return securityAnswer.equals(answer);
    }

    private String hashPassword(String password) {
        // A simple hash function for demonstration. Use a stronger hash in production.
        return Integer.toHexString(password.hashCode());
    }
}

class AuthenticationSystem {
    private User user;

    public AuthenticationSystem(User user) {
        this.user = user;
    }

    public boolean authenticate(String password, List<String> graphicalPasswordInput, String securityAnswer) {
        return user.verifyPassword(password) &&
               user.verifyGraphicalPassword(graphicalPasswordInput) &&
               user.verifySecurityAnswer(securityAnswer);
    }

    public void displaySecurityQuestion() {
        System.out.println("Security Question: " + user.getSecurityQuestion());
    }
}

class ThreeLevelPasswordSystem {
    public static void main(String[] args) {
        // Example user creation
        User user = new User("user1", "password123", Arrays.asList("image1", "image2", "image3"), "What is your pet's name?", "Fluffy");

        // Creating authentication system
        AuthenticationSystem authSystem = new AuthenticationSystem(user);

        Scanner scanner = new Scanner(System.in);

        // Level 1: Textual Password
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Level 2: Graphical Password
        System.out.print("Enter Graphical Password (image1,image2,...): ");
        String[] graphicalPasswordInput = scanner.nextLine().split(",");
        List<String> graphicalPassword = Arrays.asList(graphicalPasswordInput);

        // Level 3: Security Question
        authSystem.displaySecurityQuestion();
        System.out.print("Answer: ");
        String securityAnswer = scanner.nextLine();

        // Authentication check
        if (authSystem.authenticate(password, graphicalPassword, securityAnswer)) {
            System.out.println("Authentication successful! Access granted.");
        } else {
            System.out.println("Authentication failed! Access denied.");
        }

        scanner.close();
    }
}
