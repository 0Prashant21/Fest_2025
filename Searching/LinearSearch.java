package Searching;

public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // found at index i
            }
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        int[] nums = { 5, 2, 9, 1, 7 };
        int result = linearSearch(nums, 7);
        System.out.println(result != -1 ? "Found at index: " + result : "Not found");
    }
}
