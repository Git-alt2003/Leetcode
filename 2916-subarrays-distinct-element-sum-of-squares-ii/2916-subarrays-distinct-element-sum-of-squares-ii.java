class Solution {
    private static final int MOD = 1_000_000_007;

    private int[] sum;   
    private int[] sq; 
    private int[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        int size = 4 * n + 5;
        sum = new int[size];
        sq = new int[size];
        lazy = new int[size];


        int[] last = new int[100_002];
        java.util.Arrays.fill(last, -1);

        long ans = 0;
        for (int r = 0; r < n; r++) {
            int l = last[nums[r]] + 1; 
            update(1, 0, n - 1, l, r, 1);
            last[nums[r]] = r;
            ans = (ans + sq[1]) % MOD;
        }
        return (int) ans;
    }

    private void apply(int node, int lo, int hi, int x) {
        long len = hi - lo + 1;
        long s = sum[node];
        long q = sq[node];

        long newQ = (q + 2L * x % MOD * s + (long) x * x % MOD * len) % MOD;
        long newS = (s + (long) x * len) % MOD;
        sum[node] = (int) newS;
        sq[node] = (int) newQ;
        lazy[node] = (int) ((lazy[node] + x) % MOD);
    }

    private void push(int node, int lo, int hi) {
        if (lazy[node] != 0 && lo != hi) {
            int mid = (lo + hi) >>> 1;
            apply(2 * node, lo, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, hi, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int lo, int hi, int l, int r, int x) {
        if (r < lo || hi < l) return;
        if (l <= lo && hi <= r) {
            apply(node, lo, hi, x);
            return;
        }
        push(node, lo, hi);
        int mid = (lo + hi) >>> 1;
        update(2 * node, lo, mid, l, r, x);
        update(2 * node + 1, mid + 1, hi, l, r, x);
        sum[node] = (int) (((long) sum[2 * node] + sum[2 * node + 1]) % MOD);
        sq[node] = (int) (((long) sq[2 * node] + sq[2 * node + 1]) % MOD);
    }
}

        
    
