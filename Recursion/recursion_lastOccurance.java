/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_lastOccurance
{
    public static int lastOccurance(int arr[],int target,int i){
        // base case
        if (i == arr.length){
            return -1;
        }
        int found = lastOccurance(arr,target,i+1);
        if (found == -1 && arr[i] == target){
            return i;
        } 
        return found;
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		int arr[] = {2,5,1,7,3,0,7,4};
		int target = 7;
		System.out.println(lastOccurance(arr,target,0));
	}
}