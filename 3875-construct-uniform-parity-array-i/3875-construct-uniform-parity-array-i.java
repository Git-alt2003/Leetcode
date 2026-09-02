class Solution {
    public boolean uniformArray(int[] nums1) {
          int oddCount = 0;
        for (int x : nums1) {
            if (x % 2 != 0) oddCount++;
        }
        return true;
    }
}