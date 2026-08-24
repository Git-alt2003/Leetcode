class Solution {
    public int stoneGameVIII(int[] stones) {
         int n = stones.length;
        // prefix sums
        long[] pref = new long[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) pref[i] = pref[i-1] + stones[i];
        




        
        long[] dp = new long[n];
        long[] best = new long[n+1]; 
        best[n] = Long.MIN_VALUE / 2;
        
        for (int i = n-1; i >= 0; i--) {
            if (i == n-1) {
                dp[i] = 0;
            } else {
                dp[i] = best[i+1];
            }
            best[i] = Math.max(pref[i] - dp[i], best[i+1]);
        }
        
        return (int) dp[0];
        
    }
}