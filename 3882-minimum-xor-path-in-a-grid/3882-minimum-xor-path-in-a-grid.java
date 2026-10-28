class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] dp = new boolean[m * n][1024];

        int shift = grid[0][0];
        dp[0][shift] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int v = grid[i][j];
                int idx = i * n + j;
                if (i > 0) {
                    int up = (i - 1) * n + j;
                    for (int x = 0; x < 1024; x++)
                        if (dp[up][x]) dp[idx][x ^ v] = true;
                }
                if (j > 0) {
                    int left = i * n + (j - 1);
                    for (int x = 0; x < 1024; x++)
                        if (dp[left][x]) dp[idx][x ^ v] = true;
                }
            }
        }

        int last = (m - 1) * n + (n - 1);
        for (int x = 0; x < 1024; x++)
            if (dp[last][x]) return x;
        return 0;
    }
}
  
        
   
