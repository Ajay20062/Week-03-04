// Problem 5: Account ID Lookup in Transaction Logs

import java.util.*;

public class AccountSearch {

    // 🔹 Linear Search: First Occurrence
    public static void linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Found at index: " + i +
                        " | Comparisons: " + comparisons);
                return;
            }
        }

        System.out.println("Not Found | Comparisons: " + comparisons);
    }

    // 🔹 Linear Search: Last Occurrence
    public static void linearLast(String[] arr, String target) {
        int comparisons = 0;
        int lastIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                lastIndex = i;
            }
        }

        System.out.println("Linear Last Index: " + lastIndex +
                " | Comparisons: " + comparisons);
    }

    // 🔹 Binary Search (any one occurrence)
    public static void binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Found at index: " + mid +
                        " | Comparisons: " + comparisons);
                return;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Not Found | Comparisons: " + comparisons);
    }

    // 🔹 Find First Occurrence (Binary)
    public static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Find Last Occurrence (Binary)
    public static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Count Occurrences
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // 🔹 Linear Search
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        // 🔹 Sort before Binary Search
        Arrays.sort(logs);
        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        // 🔹 Binary Search
        binarySearch(logs, "accB");

        // 🔹 Count duplicates
        int count = countOccurrences(logs, "accB");
        System.out.println("Total Occurrences of accB: " + count);
    }
}
