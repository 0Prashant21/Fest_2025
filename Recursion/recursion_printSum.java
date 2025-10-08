/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_printSum
{
  // recursion print sum of n natural number...
  public static int printSum (int n)
  {
    if (n == 0){
        return 0;
    } 
    int num = printSum(n-1); // unnecessery line may or may not be Unsed.
    int sum = n + num;
    return sum;
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	int n = 10;
	System.out.println(printSum(n));
	
  }
}
