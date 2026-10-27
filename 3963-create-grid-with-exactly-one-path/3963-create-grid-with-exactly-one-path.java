class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++)
            java.util.Arrays.fill(g[i], '#');

        int i = 0, j = 0;
        g[0][0] = '.';
      
        while (i < m - 1 || j < n - 1) {
            if (i < m - 1 && (j == n - 1 || (i + j) % 2 == 0)) {
                i++;           
            } else {
                j++;          
            }
            g[i][j] = '.';
        }

        String[] res = new String[m];
        for (int r = 0; r < m; r++) res[r] = new String(g[r]);
        return res;
    }
}

        
    
