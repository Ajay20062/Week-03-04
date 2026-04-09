// Problem 2: Client Risk Score Ranking
import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore + " (Bal:" + accountBalance + ")";
    }
}

public class ClientRiskRanking {

    // 🔹 Bubble Sort (Ascending by riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // visualize swap
                    System.out.println("Swapped: " + arr[j] + " <-> " + arr[j + 1]);
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("\nBubble Sort (Ascending):");
        printArray(arr);
        System.out.println("Total Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (Descending by riskScore, then accountBalance)
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift right
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (Descending riskScore + balance):");
        printArray(arr);
    }

    // 🔹 Comparison for DESC riskScore, then DESC balance
    public static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    // 🔹 Top N highest risk clients
    public static void topRiskClients(Client[] arr, int topN) {
        System.out.println("\nTop " + topN + " Highest Risk Clients:");
        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.print("[" + c.name + ":" + c.riskScore + "] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Bubble Sort (Ascending)
        Client[] bubbleArray = clients.clone();
        bubbleSort(bubbleArray);

        // Insertion Sort (Descending)
        Client[] insertionArray = clients.clone();
        insertionSort(insertionArray);

        // Top 10 highest risk clients
        topRiskClients(insertionArray, 10);
    }
}
