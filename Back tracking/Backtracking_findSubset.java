


public class Backtracking_findSubset
{
    public static void findSubset(String str, String ans, int i){
        if (i == str.length()){
            System.out.println(ans);
            return;
        } 
        // recursion
        // if choice is yes 
        findSubset( str, ans+str.charAt(i), i+1);
        // backtracking
        // if choice is no
        findSubset( str, ans, i+1);
    }
  public static void printArr (int arr[])
  {
	for (int i = 0; i < arr.length; i++)
	  {
		System.out.print (arr[i] + " ");
	  }
  }
  public static void main (String[]args)
  {
	System.out.println ("Hello World");
	String str = "abc";
	findSubset(str, "", 0);

  }
}
