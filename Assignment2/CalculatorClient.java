import CalculatorApp.*;  // Import generated classes from Calculator.idl
import CalculatorApp.CalculatorHelper;  // Helper class to narrow references
import org.omg.CORBA.*;  // Import CORBA core classes
import org.omg.CosNaming.*;  // Import naming service classes
import org.omg.CosNaming.NamingContextExt;  // Import extended naming service classes
import org.omg.CosNaming.NamingContextExtHelper;  // Helper to narrow NamingContextExt references

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Initialize the ORB (Object Request Broker)
            ORB orb = ORB.init(args, null);  // The ORB enables communication between client and server

            // Get a reference to the CORBA Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);  // Narrowing the object reference to NamingContextExt

            // Resolve the Calculator object reference from the Naming Service
            String name = "Calculator";  // Name of the Calculator object registered in the Naming Service
            Calculator calculator = CalculatorHelper.narrow(ncRef.resolve_str(name));  // Get the actual Calculator object

            // Call the remote methods and print the results
            System.out.println("Add: " + calculator.add(10.5f, 5.5f));
            System.out.println("Subtract: " + calculator.subtract(10.5f, 5.5f));
            System.out.println("Multiply: " + calculator.multiply(10.5f, 5.5f));
            System.out.println("Divide: " + calculator.divide(10.5f, 5.5f));

        } catch (Exception e) {
            e.printStackTrace();  // Print any exceptions that occur
        }
    }
}
