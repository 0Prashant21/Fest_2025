public class Solution {
    public int[] dp;

    public int coinChange(int[] coins, int amount) {
        dp = new int[amount + 1];
        Arrays.fill(dp, -2);

        int ans = memo(coins, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int memo(int[] arr, int target) {
        if (target == 0) return 0;
        if (target < 0) return Integer.MAX_VALUE;

        if (dp[target] != -2) return dp[target];

        int min = Integer.MAX_VALUE;
        for (int a : arr) {
            int res = memo(arr, target - a);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }

        dp[target] = min;
        return dp[target];
    }
}
 CoinChange {
    
}
