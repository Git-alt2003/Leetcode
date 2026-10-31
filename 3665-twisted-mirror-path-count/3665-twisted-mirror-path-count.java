class Solution {
    static final int MOD = 1_000_000_007;
    int[][] grid;
    int m, n;


    int[][] memo; 
    boolean[][] computed;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        memo = new int[m * n][2];
        computed = new boolean[m * n][2];

        long[][] dp = new long[m][n];
        dp[0][0] = 1;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;
                if (grid[i][j] == 1) continue; 
            
                int land = resolve(i, j, 0);
                if (land >= 0) {
                    int r = land / n, c = land % n;
                    if (r == m - 1 && c == n - 1) {
               
                    }
                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }
             
                land = resolve(i, j, 1);
                if (land >= 0) {
                    int r = land / n, c = land % n;
                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }
            }
        }
        return (int) (dp[m - 1][n - 1] % MOD);
    }



    private int resolve(int i, int j, int dir) {
        int key = i * n + j;
        if (computed[key][dir]) return memo[key][dir];
        computed[key][dir] = true; 

        int r, c;
        if (dir == 0) { r = i; c = j + 1; }
        else { r = i + 1; c = j; }

        int d = dir;
        while (true) {
            if (r < 0 || r >= m || c < 0 || c >= n) {
                memo[key][dir] = -1;
                return -1;
            }
            if (grid[r][c] == 0) {
                int res = r * n + c;
                memo[key][dir] = res;
                return res;
            }
         
            if (d == 0) { 
                r = r + 1;
                d = 1;
            } else { 
                c = c + 1;
                d = 0;
            }
        }
    }
}

        
    
