/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_printFactorial
{
  // recursion print factorial...
  public static int printFactorial (int n)
  {
    if (n == 0){
        return 1;
    }   
    
    int fact = printFactorial(n-1); // unnecessery line may or may not be Unsed.
    int factorial = n * fact;
    return factorial;
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	int n = 5;
	System.out.println(printFactorial(n));
	
  }
}
