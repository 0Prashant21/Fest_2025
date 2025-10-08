import java.util.*;
import java.io.*;

class KnightDialer {
    static final int MOD = 1_000_000_007;

    public int knightDialer(int n) {
        int[][] moves = {
            {4,6},    // 0
            {6,8},    // 1
            {7,9},    // 2
            {4,8},    // 3
            {0,3,9},  // 4
            {},       // 5
            {0,1,7},  // 6
            {2,6},    // 7
            {1,3},    // 8
            {2,4}     // 9
        };

        long[] dp = new long[10];
        Arrays.fill(dp, 1);  // base case: n=1

        for (int step = 2; step <= n; step++) {
            long[] next = new long[10];
            for (int d = 0; d <= 9; d++) {
                for (int prev : moves[d]) {
                    next[d] = (next[d] + dp[prev]) % MOD;
                }
            }
            dp = next;
        }

        long ans = 0;
        for (long val : dp) ans = (ans + val) % MOD;
        return (int) ans;
    }
}
