package Searching;

public class BinarySearchRecursive {
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;
            if (arr[mid] > target)
                return binarySearch(arr, left, mid - 1, target);
            return binarySearch(arr, mid + 1, right, target);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 4, 6, 8, 10 };
        int result = binarySearch(nums, 0, nums.length - 1, 8);
        System.out.println(result != -1 ? "Found at index: " + result : "Not found");
    }
}
