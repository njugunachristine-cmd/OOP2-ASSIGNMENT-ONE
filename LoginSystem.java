 import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String validUsername = "christine";
        String validPassword = "8011";

        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals(validUsername) && password.equals(validPassword)) {
                System.out.println("✅ Login Successful! Welcome " + username);
                success = true;
            } else {
                System.out.println("❌ Incorrect username or password. Try again.");
                attempts++;
            }
        }

        if (!success) {
            System.out.println("🚫 Too many failed attempts. Exiting program...");
        }

        scanner.close();
    }
}
