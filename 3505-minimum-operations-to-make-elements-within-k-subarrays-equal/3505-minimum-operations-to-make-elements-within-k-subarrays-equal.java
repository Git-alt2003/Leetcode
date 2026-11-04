import java.util.*;

class Solution {

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int i = 0; i < n; i++) {
            if (m == 0 || sorted[i] != sorted[m - 1]) {
                sorted[m++] = sorted[i];
            }
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        for (int i = 0; i < x; i++) {
            int id = lowerBound(sorted, m, nums[i]);
            count.add(id + 1, 1);
            sum.add(id + 1, nums[i]);
            windowSum += nums[i];
        }

        for (int i = x; i <= n; i++) {

            int medianId = count.kth((x + 1) / 2);
            long median = sorted[medianId - 1];

            long leftSum = sum.query(medianId);
            long leftCount = count.query(medianId);

            long rightSum = windowSum - leftSum;
            long rightCount = x - leftCount;

            cost[i - x] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            if (i == n) break;

            int out = nums[i - x];
            int in = nums[i];

            int outId = lowerBound(sorted, m, out);
            int inId = lowerBound(sorted, m, in);

            count.add(outId + 1, -1);
            sum.add(outId + 1, -out);

            count.add(inId + 1, 1);
            sum.add(inId + 1, in);

            windowSum += (long) in - out;
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {

            for (int i = j * x; i <= n; i++) {

                dp[j][i] = dp[j][i - 1];

                int start = i - x;

                dp[j][i] = Math.min(
                    dp[j][i],
                    dp[j - 1][start] + cost[start]
                );
            }
        }

        return dp[k][n];
    }

    private int lowerBound(int[] arr, int n, int target) {

        int l = 0, r = n;

        while (l < r) {

            int mid = l + (r - l) / 2;

            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    class Fenwick {

        long[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void add(int i, long val) {

            while (i <= n) {
                bit[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {

            long result = 0;

            while (i > 0) {
                result += bit[i];
                i -= i & -i;
            }

            return result;
        }

        int kth(int k) {

            int idx = 0;

            int mask = Integer.highestOneBit(n);

            for (; mask > 0; mask >>= 1) {

                int next = idx + mask;

                if (next <= n && bit[next] < k) {
                    idx = next;
                    k -= (int) bit[next];
                }
            }

            return idx + 1;
        }
    }
}

        

