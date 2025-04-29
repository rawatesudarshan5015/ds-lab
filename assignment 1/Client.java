import java.rmi.*; // Import RMI classes
import java.util.Scanner; // Import Scanner for taking user input

public class Client {
    public static void main(String args[]) {
        try {
            // Create a Scanner object to read input from user
            Scanner s = new Scanner(System.in);

            System.out.println("Enter the Server address: ");
            String server = s.nextLine(); // Read server address (like localhost or IP)

            // Lookup for remote object registered on the server
            ServerInterface si = (ServerInterface) Naming.lookup("rmi://" + server + "/Server");

            // Take first string input
            System.out.println("Enter first string: ");
            String first = s.nextLine();

            // Take second string input
            System.out.println("Enter second string: ");
            String second = s.nextLine();

            // Call the remote method and print the result
            System.out.println("Concatenated String: " + si.concat(first, second));

            // Close the Scanner
            s.close();
        } catch (Exception e) {
            // Print any exceptions (errors)
            System.out.println(e);
        }
    }
}
