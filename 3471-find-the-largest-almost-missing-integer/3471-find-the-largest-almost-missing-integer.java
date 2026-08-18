class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Set<Integer>> count = new HashMap<>(); 
   
        int n = nums.length;
        for (int i = 0; i <= n - k; i++) {
            Set<Integer> subarrayValues = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                subarrayValues.add(nums[j]);
            }
            for (int val : subarrayValues) {
                count.computeIfAbsent(val, x -> new HashSet<>()).add(i);
            }
        }
        
        int result = -1;
        for (Map.Entry<Integer, Set<Integer>> entry : count.entrySet()) {
            if (entry.getValue().size() == 1) {
                result = Math.max(result, entry.getKey());
            }
        }
        return result;
        
    }
}