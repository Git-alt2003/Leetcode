class Solution {
    public int countCommas(int n) {
         if (n < 1000) return 0;
        long total = 0;
    
     

        long base = 1000; 

        int len = String.valueOf(n).length();
        for (int k = 4; k <= len; k++) {
            long lo = (long) Math.pow(10, k - 1);
            long hi = (long) Math.pow(10, k) - 1;
            long upper = Math.min(hi, n);
            if (upper >= lo) {
                long count = upper - lo + 1;
                int commasPer = (k - 1) / 3;
                total += count * commasPer;
            }
        }
        return (int) total;
        
    }
}