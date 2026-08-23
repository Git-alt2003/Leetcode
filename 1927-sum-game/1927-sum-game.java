class Solution {
    public boolean sumGame(String num) {
        int n = num.length(), half = n / 2;
        int s = 0, q = 0; 

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                q += (i < half) ? 1 : -1;
            } else {
                s += (i < half) ? (c - '0') : -(c - '0');
            }
        }

        
        if (q % 2 != 0) return true;

        
        return s + 9 * (q / 2) != 0;
        
    }
}