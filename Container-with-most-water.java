class Solution {
    public int maxArea(int[] height) {
        
        
        int i =0;
        int j=height.length-1;
        int min = 0;
        int max = Integer.MIN_VALUE;
    
       while(i<j){
            min = Math.min(height[i], height[j]);
            
            int len = j-i;
            max = Math.max(max,min*len);
            
            if(height[i]<height[j]){
                i++;
                
            }
            else 
                j--;
            
            
            
        }
        return max;
        
        
    }
}
