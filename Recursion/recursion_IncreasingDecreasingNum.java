/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_IncreasingDecreasingNum
{
  // recursion increasing number method...
  public static void printIncreasingNum (int n)
  {

	if (n == 1)
	  {
		System.out.print (n + " ");
		return;
	  }

	printIncreasingNum (n - 1);
	System.out.print (n + " ");
  }
  // recursion decreasing number method...
  public static void printDecreasingNum (int n)
  {

	if (n == 1)
	  {
		System.out.print (n + " ");
		return;
	  }

	System.out.print (n + " ");
	printDecreasingNum (n - 1);
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	int n = 10;
	printIncreasingNum (n);
	System.out.println(); // just to add a new line.
	printDecreasingNum (n);
  }
}
