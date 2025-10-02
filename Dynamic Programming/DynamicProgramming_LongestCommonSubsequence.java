public class DynamicProgramming_LongestCommonSubsequence {

    // Function to find the length of the Longest Common Subsequence (LCS)
    public static int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Create a 2D table to store lengths of LCS for substrings
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp table in bottom-up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The value in dp[m][n] is the length of LCS
        return dp[m][n];
    }

    // Function to print the LCS
    public static String printLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct the LCS from the dp table
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Since we added characters in reverse order, reverse the string
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "ABCBDAB";
        String str2 = "BDCAB";

        // Get the length of LCS
        int lengthOfLCS = longestCommonSubsequence(str1, str2);
        System.out.println("Length of Longest Common Subsequence: " + lengthOfLCS);

        // Get the actual LCS
        String lcs = printLCS(str1, str2);
        System.out.println("Longest Common Subsequence: " + lcs);
    }
}
