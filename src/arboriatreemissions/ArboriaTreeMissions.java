package arboriatreemissions;

import arboriatreemissions.missions.Mission1EmergencyBroadcastLevels;
import arboriatreemissions.missions.Mission2ForestGateCheck;
import arboriatreemissions.missions.Mission3DroneDeliveryZoneSum;
import arboriatreemissions.missions.Mission4NearestSharedSupervisor;
import arboriatreemissions.missions.Mission5ArchaeologyReconstruction;

import java.util.Scanner;

/**
 * Console entry point for all Arboria missions.
 * This class handles menu/input flow and delegates algorithms to mission classes.
 */
public class ArboriaTreeMissions {

    // Shared scanner for the entire application lifetime.
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("Choose mission (1-5) or type 'exit': ");
            String choice = SCANNER.nextLine().trim();

            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Made with love by Majed Abumathkour - 120221624.\nGoodbye.");
                break;
            }

            try {
                runMission(choice);
            } catch (Exception ex) {
                // Keep the program running even if one operation fails on bad input.
                System.out.println("Invalid input: " + ex.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

    /**
     * Dispatches menu choice to the corresponding mission runner.
     */
    private static void runMission(String choice) {
        switch (choice) {
            case "1":
                runMission1();
                break;
            case "2":
                runMission2();
                break;
            case "3":
                runMission3();
                break;
            case "4":
                runMission4();
                break;
            case "5":
                runMission5();
                break;
            default:
                System.out.println("Unknown option. Please choose 1, 2, 3, 4, 5, or exit.\n");
                break;
        }
    }

    private static void runMission1() {
        System.out.println("\nMission 1 - Emergency Broadcast Levels");
        int n = readPositiveInt("Enter n (number of positions): ");
        int[] values = readIntArray(n, "Enter " + n + " integers for level-order array:");

        String output = Mission1EmergencyBroadcastLevels.solve(values);
        if (output.isEmpty()) {
            System.out.println("No active towers.");
        } else {
            System.out.println(output);
        }
        System.out.println();
    }

    private static void runMission2() {
        System.out.println("\nMission 2 - Forest Gate Check (Validate BST)");
        int n = readPositiveInt("Enter n (number of positions): ");
        int[] values = readIntArray(n, "Enter " + n + " integers for level-order array:");

        boolean valid = Mission2ForestGateCheck.solve(values);
        System.out.println(valid ? "YES" : "NO");
        System.out.println();
    }

    private static void runMission3() {
        System.out.println("\nMission 3 - Drone Delivery Zone Sum");
        int n = readPositiveInt("Enter n (number of inserted values): ");
        int[] values = readIntArray(n, "Enter " + n + " BST insertion values:");

        int[] range;
        while (true) {
            range = readTwoInts("Enter L and R (e.g., 6 15):");
            // Mission 3 uses a closed range [L, R], so L must not be greater than R.
            if (range[0] <= range[1]) {
                break;
            }
            System.out.println("Invalid range: L must be <= R. Please try again.");
        }

        long sum = Mission3DroneDeliveryZoneSum.solve(values, range[0], range[1]);
        System.out.println(sum);
        System.out.println();
    }

    private static void runMission4() {
        System.out.println("\nMission 4 - Nearest Shared Supervisor (LCA)");
        int n = readPositiveInt("Enter n (number of inserted values): ");
        int[] values = readIntArray(n, "Enter " + n + " BST insertion values:");
        int[] pq = readTwoInts("Enter p and q (e.g., 2 8):");

        int lca = Mission4NearestSharedSupervisor.solve(values, pq[0], pq[1]);
        System.out.println(lca);
        System.out.println();
    }

    private static void runMission5() {
        System.out.println("\nMission 5 - Archaeology Reconstruction");
        int n = readPositiveInt("Enter n (number of nodes): ");
        int[] preorder = readIntArray(n, "Enter " + n + " preorder values:");
        int[] inorder = readIntArray(n, "Enter " + n + " inorder values:");

        String postorder = Mission5ArchaeologyReconstruction.solve(preorder, inorder);
        System.out.println(postorder);
        System.out.println();
    }

    /**
     * Reads one integer that must be greater than zero.
     */
    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SCANNER.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value <= 0) {
                    System.out.println("Value must be > 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads exactly expectedCount integers from one line (space separated).
     */
    private static int[] readIntArray(int expectedCount, String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = SCANNER.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            String[] tokens = line.split("\\s+");
            if (tokens.length != expectedCount) {
                System.out.println("Expected exactly " + expectedCount + " integers, but got " + tokens.length + ".");
                continue;
            }

            int[] values = new int[expectedCount];
            boolean ok = true;
            for (int i = 0; i < expectedCount; i++) {
                try {
                    values[i] = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid integer: " + tokens[i]);
                    ok = false;
                    break;
                }
            }

            if (ok) {
                return values;
            }
        }
    }

    /**
     * Reads exactly two integers from one line.
     */
    private static int[] readTwoInts(String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = SCANNER.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            String[] tokens = line.split("\\s+");
            if (tokens.length != 2) {
                System.out.println("Please enter exactly two integers.");
                continue;
            }

            try {
                return new int[]{Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])};
            } catch (NumberFormatException ex) {
                System.out.println("Invalid numbers. Please try again.");
            }
        }
    }

    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println("====================================");
        System.out.println("Arboria Tree Missions - Main Menu");
        System.out.println("1) Mission 1 - Emergency Broadcast Levels");
        System.out.println("2) Mission 2 - Forest Gate Check");
        System.out.println("3) Mission 3 - Drone Delivery Zone Sum");
        System.out.println("4) Mission 4 - Nearest Shared Supervisor (LCA)");
        System.out.println("5) Mission 5 - Archaeology Reconstruction");
        System.out.println("Type 'exit' to end program");
        System.out.println("====================================");
    }
}
