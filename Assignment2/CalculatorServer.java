import CalculatorApp.*;  // Import generated classes from Calculator.idl
import CalculatorApp.CalculatorHelper;  // Helper class for Calculator interface
import org.omg.CORBA.*;  // Import CORBA core classes
import org.omg.PortableServer.*;  // Import classes for working with Portable Object Adapter (POA)
import org.omg.PortableServer.POA;  // POA reference used to register servants
import org.omg.CosNaming.*;  // Import naming service classes
import org.omg.CosNaming.NamingContextExt;  // Extended naming service classes
import org.omg.CosNaming.NamingContextExtHelper;  // Helper to narrow NamingContextExt references

// Calculator implementation class extends CalculatorPOA (generated skeleton)
class CalculatorImpl extends CalculatorPOA {
    private ORB orb;  // ORB reference needed for communication

    // Set the ORB reference
    public void setORB(ORB orb) {
        this.orb = orb;
    }

    // Implement the add method
    public float add(float a, float b) {
        return a + b;  // Return the sum of a and b
    }

    // Implement the subtract method
    public float subtract(float a, float b) {
        return a - b;  // Return the difference of a and b
    }

    // Implement the multiply method
    public float multiply(float a, float b) {
        return a * b;  // Return the product of a and b
    }

    // Implement the divide method
    public float divide(float a, float b) {
        if (b == 0) {  // Check if the denominator is zero
            throw new RuntimeException("Division by zero!");  // Throw exception if division by zero
        }
        return a / b;  // Return the quotient of a and b
    }
}

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Initialize the ORB (Object Request Broker)
            ORB orb = ORB.init(args, null);  // The ORB enables communication between client and server

            // Get reference to the root POA (Portable Object Adapter) and activate its POA manager
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();  // Activate the POA manager

            // Create an instance of CalculatorImpl (the servant) and set the ORB
            CalculatorImpl calculatorImpl = new CalculatorImpl();
            calculatorImpl.setORB(orb);

            // Convert the CalculatorImpl object to a CORBA reference
            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(calculatorImpl);
            Calculator href = CalculatorHelper.narrow(ref);  // Narrow the reference to the Calculator interface

            // Get reference to the CORBA Naming Service and narrow the reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);  // Narrow the reference to NamingContextExt

            // Register the Calculator object in the Naming Service
            String name = "Calculator";  // Name under which the object will be registered
            NameComponent[] path = ncRef.to_name(name);  // Convert the name to NameComponent array
            ncRef.rebind(path, href);  // Bind the object reference with the name in the Naming Service

            System.out.println("CalculatorServer ready and waiting ...");  // Inform that the server is ready

            // Start the ORB to listen for incoming requests
            orb.run();  // The server is now waiting for client requests

        } catch (Exception e) {
            e.printStackTrace();  // Print any exceptions that occur
        }
    }
}
