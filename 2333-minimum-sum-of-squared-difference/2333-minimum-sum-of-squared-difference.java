class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] cnt = new int[100001];
        long K = (long) k1 + k2;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            cnt[d]++;
            sum += d;
        }

        if (K >= sum) return 0;

        for (int d = 100000; d > 0 && K > 0; d--) {
            if (cnt[d] == 0) continue;

            long take = Math.min(K, cnt[d]);
            cnt[d] -= take;
            cnt[d - 1] += take;
            K -= take;
        }

        long ans = 0;

        for (int d = 1; d <= 100000; d++) {
            ans += (long) d * d * cnt[d];
        }

        return ans;
        
    }
}