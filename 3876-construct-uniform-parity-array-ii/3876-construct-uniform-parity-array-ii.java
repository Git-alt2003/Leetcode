class Solution {
    public boolean uniformArray(int[] nums1) {
         Integer minOdd = null, minEven = null;
        for (int v : nums1) {
            if (v % 2 == 0) {
                if (minEven == null || v < minEven) minEven = v;
            } else {
                if (minOdd == null || v < minOdd) minOdd = v;
            }
        }
  
        if (minOdd == null) return true;
  
        if (minEven == null) return true; 
        return minOdd < minEven;
        
    }
}