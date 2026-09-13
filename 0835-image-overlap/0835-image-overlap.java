class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
          int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        
        for (int[] a : ones1) {
            for (int[] b : ones2) {
               
                int key = (a[0] - b[0] + n) * 100 + (a[1] - b[1] + n);
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                max = Math.max(max, count);
            }
        }
        
        return max;
        
    }
}