class Solution {
    public int minimumDeletions(int[] nums) {
         int n = nums.length;
        
 
        int minIdx = 0, maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        
 
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        

        int bothFront = j + 1;
        

        int bothBack = n - i;
        
 
        int split = (i + 1) + (n - j);
        
        return Math.min(bothFront, Math.min(bothBack, split));
        
    }
}