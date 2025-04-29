import java.rmi.*; // Import RMI classes

// Remote Interface - defines the methods that can be called remotely
public interface ServerInterface extends Remote {
    // Method to concatenate two strings
    // It throws RemoteException because it's used in RMI (network-related error handling)
    String concat(String a, String b) throws RemoteException;
}
