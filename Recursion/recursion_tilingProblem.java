/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_tilingProblem
{
    public static int tilingWays(int n){
        // base case
        if(n == 0 || n == 1){
            return 1;
        }
         // vertical ways
         int verticalWays = tilingWays(n-1);
         
         // horizontal ways
         int horizontalWays = tilingWays(n-2);
         
         // total ways
         int totalWays = horizontalWays + verticalWays;
         
         return totalWays;
            
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		int n = 5;
		System.out.println(tilingWays(n));
	}
}