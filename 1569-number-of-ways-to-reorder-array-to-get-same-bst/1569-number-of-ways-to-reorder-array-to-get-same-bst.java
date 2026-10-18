class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);
        return (int) ((count(list) - 1 + MOD) % MOD);
    }

    private long count(List<Integer> nodes) {
        if (nodes.size() <= 2) return 1;
        

        int root = nodes.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i) < root) left.add(nodes.get(i));
            else right.add(nodes.get(i));
        }

        long ways = comb[left.size() + right.size()][left.size()];
        ways = ways * count(left) % MOD;
        ways = ways * count(right) % MOD;
        return ways;
    }
}
  
        
    
