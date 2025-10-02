/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Backtracking_gridWays
{
    public static int gridWays(int i, int j, int m, int n){
       if (i == m-1 && j == n-1){
          return 1; 
       }  else if (i == m || j == n){
           return 0;
       }
       
       
       int w1 = gridWays(i+1 ,j ,m ,n);
       int w2 = gridWays(i ,j+1 ,m ,n);
       
       return w1 + w2;
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		int m = 4;
		int n = 4;
		System.out.print("The Total no. of ways is: " + gridWays(0,0,m,n));
	}
}