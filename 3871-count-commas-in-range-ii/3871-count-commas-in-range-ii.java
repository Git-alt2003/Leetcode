class Solution {
    public long countCommas(long n) {
        long count = 0;
        for (int k = 1; k <= 5; k++) {
            long low = (long) Math.pow(10, 3L * k);
            if (n < low) break;
            long high = (long) Math.pow(10, 3L * (k + 1)) - 1;
            if (n < high) high = n;
            count += (high - low + 1) * k;
        }
        return count;
        
    }
}