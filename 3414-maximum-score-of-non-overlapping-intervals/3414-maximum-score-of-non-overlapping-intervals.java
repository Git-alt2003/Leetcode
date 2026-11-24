import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, idx;
        Interval(int l, int r, int weight, int idx) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.idx = idx;
        }
    }

    private static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> cur = intervals.get(i);
            arr[i] = new Interval(cur.get(0), cur.get(1), cur.get(2), i);
        }

        // Sort by right endpoint ascending
        Arrays.sort(arr, Comparator.comparingInt(a -> a.r));


        State[][] dp = new State[n][5];

        for (int i = 0; i < n; i++) {
            int prev = findLastNonOverlapping(arr, i);

            for (int k = 1; k <= 4; k++) {
              
                State best = (i > 0) ? dp[i - 1][k] : new State(0L, new ArrayList<>());

                // Option 2: pick interval i
                long takeWeight = arr[i].weight;
                List<Integer> takeIndices = new ArrayList<>();
                if (prev != -1 && k > 1) {
                    takeWeight += dp[prev][k - 1].weight;
                    takeIndices.addAll(dp[prev][k - 1].indices);
                }
                takeIndices.add(arr[i].idx);
                Collections.sort(takeIndices);

                State candidate = new State(takeWeight, takeIndices);
                best = pickBetter(best, candidate);
                dp[i][k] = best;
            }
        }

        State ans = dp[n - 1][4];
        int[] res = new int[ans.indices.size()];
        for (int i = 0; i < ans.indices.size(); i++) {
            res[i] = ans.indices.get(i);
        }
        return res;
    }


    private int findLastNonOverlapping(Interval[] arr, int i) {
        int targetStart = arr[i].l;
        int lo = 0, hi = i - 1, ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid].r < targetStart) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private State pickBetter(State a, State b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }
        return compareLex(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compareLex(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }
        return Integer.compare(a.size(), b.size());
    }
}

        
    
