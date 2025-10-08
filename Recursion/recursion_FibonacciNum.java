/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class recursion_FibonacciNum
{
    public static int nthFibonacci(int n){
        if (n == 0){
            return 0;
        } 
        if(n == 1){
            return 1;
        }
        
        int fib1 = nthFibonacci(n-1);
        int fib2 = nthFibonacci(n-2);
        int fibN = fib1 + fib2;
        
        return fibN;
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter to get Fibonacci Number:");
		int n = sc.nextInt();
		System.out.println(nthFibonacci(n));
	}
}