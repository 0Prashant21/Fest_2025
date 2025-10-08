/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_binaryStringProblem
{
  public static String ConsOne (String str, int i, String newStr)
  {
	// Base case: end of the string
	if (i == str.length () - 1)
	  {
		newStr += str.charAt (i);
		return newStr;
	  }

	if (str.charAt (i) != '1' || str.charAt (i + 1) != '1')
	  {
		newStr += str.charAt (i);
	  }
	else
	  {
		return ConsOne (str, i + 1, newStr);
	  }
	return ConsOne (str, i + 1, newStr);
  }

  public static void main (String[]args)
  {
	String str = "0101001011";
	System.out.println (ConsOne (str, 0, ""));
  }
}
