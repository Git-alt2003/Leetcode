class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();
        
        // dp[j] = number of ways to match current prefix of t
        // using prefix of s ending at position j
        long[] dp = new long[n + 1];
        for (int j = 0; j <= n; j++) {
            dp[j] = 1; // empty t is a subsequence of anything
        }
        
        for (int i = 1; i <= m; i++) {
            // Use long to avoid overflow during intermediate calculations
            long[] next = new long[n + 1];
            // next[0] stays 0 since we can't form non-empty t from empty s
            for (int j = 1; j <= n; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    next[j] = dp[j - 1] + next[j - 1];
                } else {
                    next[j] = next[j - 1];
                }
            }
            dp = next;
        }
        
        return (int) dp[n];
    }
        
    
}