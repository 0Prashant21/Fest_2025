public class InsertionSort {
    //Sorts an array of integers using the Insertion Sort algorithm.
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i]; // The element to be inserted into the sorted sub-array
            int j = i - 1;   // Index of the last element in the sorted sub-array

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift element to the right
                j = j - 1;           // Move to the previous element in the sorted sub-array
            }
            arr[j + 1] = key; // Place the key in its correct position
        }
    }

    /**
     * A utility function to print the elements of an array.
     * Code by Madhav :)
     * @param arr The array to be printed.
     */
    public static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main method to test the Insertion Sort
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        InsertionSort sorter = new InsertionSort();
        sorter.sort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
