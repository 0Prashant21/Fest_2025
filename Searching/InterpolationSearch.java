package Searching;

public class InterpolationSearch {
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            if (low == high) {
                return arr[low] == target ? low : -1;
            }

            int pos = low + ((target - arr[low]) * (high - low)) /
                    (arr[high] - arr[low]);

            if (arr[pos] == target)
                return pos;
            if (arr[pos] < target)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 10, 20, 30, 40, 50, 60, 70 };
        int result = interpolationSearch(nums, 40);
        System.out.println(result != -1 ? "Found at index: " + result : "Not found");
    }
}
