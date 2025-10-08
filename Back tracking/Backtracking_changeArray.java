/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Backtracking_changeArray
{
  public static void changeArray (int arr[], int i, int val)
  {
	// base case
	if (i == arr.length)
	  {
		printArr (arr);
		return;
	  }
	// recursion
	arr[i] = val;
	changeArray (arr, i + 1, val + 1);
	// backtracking method
	arr[i] = arr[i] - 2;



  }
  public static void printArr (int arr[])
  {
	for (int i = 0; i < arr.length; i++)
	  {
		System.out.print (arr[i] + " ");
	  }
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	int arr[] = new int[10];
	changeArray (arr, 0, 1);
	printArr (arr);
  }
}
