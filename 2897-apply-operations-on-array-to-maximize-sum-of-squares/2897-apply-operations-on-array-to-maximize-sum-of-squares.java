class Solution {
    public int maxSum(List<Integer> nums, int k) {
         final int MOD = 1_000_000_007;
        int[] cnt = new int[32];          
        for (int x : nums) {
            for (int b = 0; b < 32; b++) {
                if (((x >> b) & 1) == 1) {
                    cnt[b]++;
                }
            }
        }

        long[] res = new long[k];       
        for (int b = 0; b < 32; b++) {
            int take = Math.min(k, cnt[b]);   
            for (int i = 0; i < take; i++) {
                res[i] |= (1L << b);
            }
        }

        long ans = 0;
        for (long v : res) {
            ans = (ans + (v % MOD) * (v % MOD)) % MOD;
        }
        return (int) ans;
        
    }
}