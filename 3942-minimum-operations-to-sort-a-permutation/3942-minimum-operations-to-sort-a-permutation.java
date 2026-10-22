class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean right = true;
        boolean left = true;

        for (int i = 0; i < n; i++) {
            int r = (zero + i) % n;
            int l = (zero - i + n) % n;

            if (nums[r] != i) right = false;
            if (nums[l] != i) left = false;
        }

        int ans = Integer.MAX_VALUE;

        if (right) {
            ans = Math.min(ans, Math.min(zero, n - zero + 2));
        }

        if (left) {
            ans = Math.min(ans, Math.min(zero + 2, n - zero));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}


        
    
