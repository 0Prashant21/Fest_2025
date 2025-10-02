/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Backtracking_permutation
{
    public static void totalPermutation(String str, String ans){
        // base case
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }
        
        for (int i = 0 ;i < str.length() ;i++ ){
            char curr = str.charAt(i);
            String newStr = str.substring(0,i) + str.substring(i+1);
            
            totalPermutation(newStr, ans + curr);
        } 
            
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		String str = "abcd";
		totalPermutation(str, "");
	}
}