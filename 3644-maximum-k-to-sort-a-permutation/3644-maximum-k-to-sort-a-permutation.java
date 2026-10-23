class Solution {
    public int sortPermutation(int[] nums) {
        int and = -1; // all bits set
        boolean misplaced = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                misplaced = true;
                and &= nums[i];
            }
        }
        return misplaced ? and : 0;
        
    }
}