/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_NthPower
{
    public static int nPower(int x ,int n){
        if (n == 1){
            return 1;
        } 
        int lessPow = (int)Math.pow(x,n-1);
        int nthPower = x * lessPow;
        return nthPower;
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		System.out.println(nPower(2,10));
		
	}
}