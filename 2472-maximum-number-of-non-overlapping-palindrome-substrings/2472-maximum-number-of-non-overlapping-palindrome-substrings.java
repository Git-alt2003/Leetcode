class Solution {
    public int maxPalindromes(String s, int k) {
        
         int n = s.length();
        int[] dp = new int[n + 1]; 

        for (int i = 0; i < n; i++) {
            
            for (int len = k; len <= k + 1; len++) {
                int j = i - len + 1; 
                if (j >= 0 && isPalindrome(s, j, i)) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                }
            }
           
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        return dp[n];
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}