import java.util.Arrays;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0)
            return 0;
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return j + 1;
    }

    public static void main(String[] args) {
        int[] array = { 1, 1, 2, 2, 3, 4, 4, 5 };
        int newLength = removeDuplicates(array);
        System.out.println("Array after removing duplicates: " + Arrays.toString(Arrays.copyOf(array, newLength)));
    }
}
