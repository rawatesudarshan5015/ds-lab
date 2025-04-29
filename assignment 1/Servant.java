import java.rmi.RemoteException; // Import RemoteException for error handling
import java.rmi.server.UnicastRemoteObject; // Import UnicastRemoteObject for exporting remote objects

// Servant class - Actual implementation of the remote methods
public class Servant extends UnicastRemoteObject implements ServerInterface {

    // Constructor: must call the superclass constructor to properly export the object
    protected Servant() throws RemoteException {
        super();
    }

    // Implementation of the concat method defined in ServerInterface
    @Override
    public String concat(String a, String b) throws RemoteException {
        return a + b; // Simply return concatenated string
    }
}
