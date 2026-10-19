class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] == target) res.add(i);
        }
        return res;
        
    }
}