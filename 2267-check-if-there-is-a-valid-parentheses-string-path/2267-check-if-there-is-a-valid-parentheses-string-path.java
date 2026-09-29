class Solution {
    private char[][] grid;
    private int m, n;
    private Boolean[][][] memo;
    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        int len = m + n - 1;
   
        if (len % 2 != 0) return false;
    
        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')') return false;


        this.memo = new Boolean[m][n][len + 1];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int bal) {
  
        bal += grid[r][c] == '(' ? 1 : -1;

 
        if (bal < 0) return false;

    
        int remaining = (m - 1 - r) + (n - 1 - c);
        if (bal > remaining) return false;

    
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        if (memo[r][c][bal] != null) return memo[r][c][bal];

        boolean ok = false;
        if (r + 1 < m) ok = dfs(r + 1, c, bal);
        if (!ok && c + 1 < n) ok = dfs(r, c + 1, bal);

        memo[r][c][bal] = ok;
        return ok;
        
    }
}