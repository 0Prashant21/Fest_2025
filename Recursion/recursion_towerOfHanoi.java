/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_towerOfHanoi
{
  public static void towerOfHanoi (int n, String S, String H, String D)
  {
	if (n == 1)
	  {
		System.out.println (n + " is transfered from " + S + " to " + D);
		return;
	  }

	towerOfHanoi(n - 1, S, D, H);
	System.out.println (n + " is transfered from " + S + " to " + D);
	towerOfHanoi(n - 1, H, S, D);


  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	int n = 10;
	towerOfHanoi (n, "S", "H", "D");
  }
}
