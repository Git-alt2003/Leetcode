class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";
        int k = sum / 9;      
        int r = sum % 9;      
        int used = k + (r > 0 ? 1 : 0);
        if (used > num) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) sb.append('9');
        if (r > 0) sb.append((char)('0' + r));
        while (sb.length() < num) sb.append('0');
        return sb.toString();
        
    }
}