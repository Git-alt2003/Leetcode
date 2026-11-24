class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        final long MAX_BOUND = 2520L * 10L;

        for (int i = 0; i < n; i++) {
            long prod = 1L;
            long g = 0L;
            long l = 1L;

            for (int j = i; j < n; j++) {
                long val = nums[j];
                prod *= val;
                if (prod > MAX_BOUND) {
                    break;
                }

                g = (g == 0L) ? val : gcd(g, val);
                l = lcm(l, val);

                if (prod == l * g) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0L) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}

        
    
