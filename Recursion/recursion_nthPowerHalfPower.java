/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_nthPowerHalfPower
{
  public static int nthPower (int x, int n)
  {
	if (n == 0)
	  {
		return 1;
	  }
	  // if n is even
	int nPower = nthPower (x, n / 2) * nthPower (x, n / 2);
	// if n is odd
	if (n % 2 != 0)
	  {
		nPower = x * nPower;
	  }
	return nPower;
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	System.out.println (nthPower (2, 10));
  }
}
