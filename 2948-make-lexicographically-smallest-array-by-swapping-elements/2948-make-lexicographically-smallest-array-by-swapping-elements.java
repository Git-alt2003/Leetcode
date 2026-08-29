class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
         int n = nums.length;
        int[] res = new int[n];
        
   
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);
        
 
        int i = 0;
        while (i < n) {
            int j = i;
           
            while (j + 1 < n && nums[idx[j + 1]] - nums[idx[j]] <= limit) {
                j++;
            }
            
     
            int[] values = new int[j - i + 1];
            int[] positions = new int[j - i + 1];
            for (int k = i; k <= j; k++) {
                positions[k - i] = idx[k];
                values[k - i] = nums[idx[k]];
            }
            
 

            Arrays.sort(positions);
            
            for (int k = 0; k < values.length; k++) {
                res[positions[k]] = values[k];
            }
            
            i = j + 1;
        }
        
        return res;
        
    }
}