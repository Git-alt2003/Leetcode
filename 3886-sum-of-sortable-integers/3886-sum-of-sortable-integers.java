class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prev = Integer.MIN_VALUE;

            for (int i = 0; i < n; i += k) {
                int start = i;
                int drops = 0;

                for (int j = i; j < i + k; j++) {
                    int next = (j + 1 == i + k) ? i : j + 1;

                    if (nums[j] > nums[next]) {
                        drops++;
                        start = next;
                    }
                }

                if (drops > 1) {
                    valid = false;
                    break;
                }

                int first = nums[start];
                int last = nums[(start + k - 1 - i) % k + i];

                if (first < prev) {
                    valid = false;
                    break;
                }

                prev = last;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}

        

