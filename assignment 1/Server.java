import java.rmi.*; // Import RMI classes
import java.net.*; // Import Networking classes

public class Server {
    public static void main(String[] args) {
        try {
            // Create an instance of Servant class (implementation of remote methods)
            Servant s = new Servant();

            // Bind the remote object with the name "Server" in RMI Registry
            Naming.rebind("Server", s);
        } catch (Exception e) {
            // Print any exceptions (errors)
            System.out.println(e);
        }
    }
}
