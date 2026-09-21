class Solution {
    public long[] resultArray(int[] nums, int k) {
         long[] result = new long[k];
        long[] cur = new long[k];      // counts of subarrays ending at previous index, by mod
        long[] next = new long[k];

        for (int i = 0; i < nums.length; i++) {
            java.util.Arrays.fill(next, 0);
            int r = nums[i] % k;
           
            next[r]++;
          
            for (int m = 0; m < k; m++) {
                if (cur[m] > 0) {
                    next[(m * r) % k] += cur[m];
                }
            }
            for (int m = 0; m < k; m++) {
                result[m] += next[m];
                cur[m] = next[m];
            }
        }
        return result;
        
    }
}