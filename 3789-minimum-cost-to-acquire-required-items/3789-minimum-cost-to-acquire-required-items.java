class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min((long) need1, (long) need2);
        long totalCost = common * Math.min((long) costBoth, (long) cost1 + (long) cost2);
        
        long rem1 = need1 - common;
        long rem2 = need2 - common;
        
        if (rem1 > 0) {
            totalCost += rem1 * Math.min((long) costBoth, (long) cost1);
        } else if (rem2 > 0) {
            totalCost += rem2 * Math.min((long) costBoth, (long) cost2);
        }
        
        return totalCost;
    }
}

     
    
