/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_pairingFriendsProblem
{
    public static int pairingFriends(int n){
        // base case
        if (n == 1 || n == 0 || n == 2){
            return n;
        }
        
        // single 
        int singleWays = pairingFriends(n-1);
        
        // paired
        int pairedWays = pairingFriends(n-2);
        int totalPairedWays = pairedWays * (n-1);
        
        // total ways
        int totalWays = pairedWays + singleWays;
        
        return totalWays;
            
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		int n = 4;
		System.out.println(pairingFriends(n));
	}
}