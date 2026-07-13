class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j])
                merged[k++] = nums1[i++];
            else
                merged[k++] = nums2[j++];
        }

        while (i < m)
            merged[k++] = nums1[i++];

        while (j < n)
            merged[k++] = nums2[j++];

        int size = m + n;

        if (size % 2 == 1)
            return merged[size / 2];
        else
            return (merged[size / 2 - 1] + merged[size / 2]) / 2.0;
    }
}
        
    
