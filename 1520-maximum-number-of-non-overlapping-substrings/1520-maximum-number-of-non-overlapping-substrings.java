class Solution {
    public List<String> maxNumOfSubstrings(String s) {
          int n = s.length();
        int[] first = new int[26], last = new int[26];
        Arrays.fill(first, n);
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = Math.max(last[c], i);
        }
        
   
        List<int[]> intervals = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (first[c] == n) continue;
            int start = first[c];
            int end = last[c];
            boolean ok = true;
            for (int i = start; i <= end; i++) {
                int d = s.charAt(i) - 'a';
                if (first[d] < start) { ok = false; break; }
                end = Math.max(end, last[d]);
            }
            if (ok) intervals.add(new int[]{start, end});
        }
        
       
        List<int[]> filtered = new ArrayList<>();
        for (int[] a : intervals) {
            boolean contains = false;
            for (int[] b : intervals) {
                if (a != b && b[0] >= a[0] && b[1] <= a[1]
                        && (b[0] > a[0] || b[1] < a[1])) {
                    contains = true;
                    break;
                }
            }
            if (!contains) filtered.add(a);
        }
        
       
        filtered.sort((a, b) -> a[1] - b[1]);
        
        List<String> res = new ArrayList<>();
        int lastEnd = -1;
        for (int[] iv : filtered) {
            if (iv[0] > lastEnd) {
                res.add(s.substring(iv[0], iv[1] + 1));
                lastEnd = iv[1];
            }
        }
        return res;
        
    }
}