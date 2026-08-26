class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
         int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";
        
        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') ones++;
                if (ones == k) {
                    int len = j - i + 1;
                    String sub = s.substring(i, j + 1);
                    if (len < minLen) {
                        minLen = len;
                        result = sub;
                    } else if (len == minLen && sub.compareTo(result) < 0) {
                        result = sub;
                    }
                } else if (ones > k) {
                    break;
                }
            }
        }
        return result;
        
    }
}