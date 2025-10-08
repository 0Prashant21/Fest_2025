package Searching;

public class TernarySearch {
    public static int ternarySearch(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            if (arr[mid1] == target)
                return mid1;
            if (arr[mid2] == target)
                return mid2;

            if (target < arr[mid1])
                return ternarySearch(arr, left, mid1 - 1, target);
            else if (target > arr[mid2])
                return ternarySearch(arr, mid2 + 1, right, target);
            else
                return ternarySearch(arr, mid1 + 1, mid2 - 1, target);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 7, 9, 11, 13 };
        int result = ternarySearch(nums, 0, nums.length - 1, 11);
        System.out.println(result != -1 ? "Found at index: " + result : "Not found");
    }
}
