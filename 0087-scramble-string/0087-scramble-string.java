class Solution {
   
    private Boolean[][][] memo;
    private String s1, s2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        memo = new Boolean[n + 1][n][n];
        return dp(0, 0, n);
    }

    private boolean dp(int i, int j, int len) {
 
        if (len == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }

        if (memo[len][i][j] != null) {
            return memo[len][i][j];
        }

        boolean res = false;


        for (int k = 1; k < len; k++) {
            
            if (dp(i, j, k) && dp(i + k, j + k, len - k)) {
                res = true;
                break;
            }
         
            if (dp(i, j + len - k, k) && dp(i + k, j, len - k)) {
                res = true;
                break;
            }
        }

        memo[len][i][j] = res;
        return res;
        
    }
}