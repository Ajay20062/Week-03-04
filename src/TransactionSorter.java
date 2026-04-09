// Problem 1: Transaction Fee Sorting for Audit Compliance

import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // format HH:MM

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionSorter {

    // Bubble Sort (by fee) - Stable
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int swaps = 0, passes = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination (optimization)
            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result:");
        printList(list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (by fee + timestamp) - Stable
    public static void insertionSort(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare by fee, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j)); // shift right
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result:");
        printList(list);
    }

    // Comparison: fee first, then timestamp
    public static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // High-fee outliers (> 50)
    public static void findOutliers(List<Transaction> list) {
        System.out.println("\nHigh-fee Outliers (>50):");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    public static void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.print("[" + t + "] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        // Choose sorting method based on size
        if (size <= 100) {
            bubbleSort(new ArrayList<>(transactions));
        } else if (size <= 1000) {
            insertionSort(new ArrayList<>(transactions));
        }

        // Always run insertion sort for audit (fee + timestamp)
        insertionSort(new ArrayList<>(transactions));

        // Detect outliers
        findOutliers(transactions);
    }
}