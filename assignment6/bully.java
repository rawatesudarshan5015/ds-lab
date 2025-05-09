import java.util.Scanner;

public class bully {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Input number of processes
        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        int[] processes = new int[n];
        boolean[] alive = new boolean[n];

        // Assign process IDs from 1 to n and set all as alive
        for (int i = 0; i < n; i++) {
            processes[i] = i + 1;
            alive[i] = true;
        }

        // Step 2: Input which process has failed
        System.out.print("Enter the process ID that has failed: ");
        int failedId = scanner.nextInt();
        alive[failedId - 1] = false;

        // Step 3: Input election initiator
        System.out.print("Enter the process ID initiating the election: ");
        int initiatorId = scanner.nextInt();

        // Run Bully Algorithm
        int leader = startElection(initiatorId, processes, alive);
        System.out.println("\nFinal leader elected: Process " + leader);

        scanner.close();
    }

    public static int startElection(int initiatorId, int[] processes, boolean[] alive) {
        System.out.println("\nProcess " + initiatorId + " starts an election.");
        int maxId = -1;

        for (int i = 0; i < processes.length; i++) {
            if (processes[i] > initiatorId && alive[i]) {
                System.out.println("Process " + initiatorId + " gets response from Process " + processes[i]);
                if (processes[i] > maxId) {
                    maxId = processes[i];
                }
            }
        }

        if (maxId == -1) {
            System.out.println("Process " + initiatorId + " becomes the leader (no higher processes alive).");
            return initiatorId;
        } else {
            return startElection(maxId, processes, alive);
        }
    }
}
