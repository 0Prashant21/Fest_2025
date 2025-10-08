/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_isSorted
{
  public static boolean isSorted (int arr[], int i)
  {
	if (i == arr.length - 1)
	  {
		return true;
	  }
	if (arr[i] > arr[i + 1])
	  {
		return false;
	  }
	return isSorted (arr, i + 1);
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	int arr[] = { 1, 4, 2, 3, 5, 6 };
	int i = 0;
	System.out.println (isSorted (arr, i));
  }
}
