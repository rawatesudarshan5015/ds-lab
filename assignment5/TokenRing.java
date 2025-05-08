import java.util.Scanner;

public class TokenRing{


    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);


        System.out.print("Enter the Message: ");
        String message =sc.nextLine();

        System.out.print("Enter the Number of Process: ");
        int n =sc.nextInt();

        System.out.print("The Processes are: ");
        for(int i=1;i<=n;i++){
            System.out.print(i+" ");
        }
        System.out.println("1\n");


        System.out.print("Enter the Sender: ");
        int sender = sc.nextInt();

        System.out.print("Enter the Receiver: ");
        int Receiver = sc.nextInt();

        if(sender==Receiver || sender<1 || sender>n || Receiver<1 || Receiver>n){
            System.out.println("Invalid Sender/Receiver process Number!!!");
            sc.close();
            return;
        }

        System.out.print("Sender Process->"+sender);
        System.out.println("Token is received by Sender");
        System.out.println("Message to be Send: "+ message);

        int i = (sender%n)+1;

        while(i!=Receiver){
            System.out.println("Token Passed to process->"+i);
            i = (i%n)+1;
        }
        System.out.println("Receiver Process ->"+ Receiver);
        System.out.println("The Message Received: "+message);

        sc.close();

    }
}