
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000;

        int[][] cost = new int[n][n];

        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                int len = r - l + 1;
                cost[l][r] = INF;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int start = 0; start < d; start++) {
                        int left = start;
                        int right = (len - 1 - start / d * d);

                        // Compare positions in the same group
                        int a = start;
                        int b = start + ((len / d) - 1) * d;

                        while (a < b) {
                            if (s.charAt(l + a) != s.charAt(l + b)) {
                                changes++;
                            }
                            a += d;
                            b -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[p][j - 1] + cost[p][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}


        
    
