/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class recursion_firstOccurance
{
    public static int firstOccurance(int arr[],int target,int i){
        // base case
        if (i == arr.length-1){
            return 0;
        }
        if (target == arr[i]){
            return i;
        } 
        return firstOccurance(arr,target,i+1);
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		int arr[] = {2,5,1,7,3,0,4};
		int target = 3;
		System.out.println(firstOccurance(arr,target,0));
	}
}