#include <iostream>
using namespace std;

void bubbleSort(int arr[], int n) {
    bool swapped;

    // Outer loop for passes
    for (int i = 0; i < n - 1; i++) {
        swapped = false;

        // Inner loop for pairwise comparisons
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j + 1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;

                swapped = true;
            }
        }

        // If no swaps occurred, array is already sorted
        if (!swapped)
            break;
    }
}

void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main() {
    int arr[] = {5, 3, 8, 4, 2};
    int n = sizeof(arr) / sizeof(arr[0]);

    cout << "Before sorting: ";
    printArray(arr, n);

    bubbleSort(arr, n);

    cout << "After sorting: ";
    printArray(arr, n);

    return 0;
}
