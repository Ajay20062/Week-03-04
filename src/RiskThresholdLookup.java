// Problem 6: Risk Threshold Binary Lookup

public class RiskThresholdLookup {

    // 🔹 Linear Search (unsorted)
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i +
                        " | Comparisons: " + comparisons);
                return;
            }
        }

        System.out.println("Linear: Not Found | Comparisons: " + comparisons);
    }

    // 🔹 Binary Search (exact match)
    public static void binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Binary: Found at index " + mid +
                        " | Comparisons: " + comparisons);
                return;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary: Not Found | Comparisons: " + comparisons);
    }

    // 🔹 Lower Bound (first >= target) → insertion point
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // insertion index
    }

    // 🔹 Floor (largest ≤ target)
    public static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Ceiling (smallest ≥ target)
    public static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int[] sorted = {10, 25, 50, 100};

        int target = 30;

        // 🔹 Linear Search (unsorted)
        linearSearch(unsorted, target);

        // 🔹 Binary Search (sorted)
        binarySearch(sorted, target);

        // 🔹 Insertion Point
        int index = lowerBound(sorted, target);
        System.out.println("Insertion Point for " + target + ": index " + index);

        // 🔹 Floor & Ceiling
        Integer f = floor(sorted, target);
        Integer c = ceiling(sorted, target);

        System.out.println("Floor(" + target + "): " + f);
        System.out.println("Ceiling(" + target + "): " + c);
    }
}