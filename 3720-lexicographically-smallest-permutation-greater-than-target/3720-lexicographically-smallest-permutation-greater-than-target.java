class Solution {
    public String lexGreaterPermutation(String s, String target) {
         int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
       
        for (int i = n - 1; i >= 0; i--) {
            int[] cnt = count.clone();
            boolean feasible = true;
           
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';
                if (cnt[idx] == 0) { feasible = false; break; }
                cnt[idx]--;
                sb.append(target.charAt(j));
            }
            if (!feasible) continue;
    
            int ti = target.charAt(i) - 'a';
            int chosen = -1;
            for (int c = ti + 1; c < 26; c++) {
                if (cnt[c] > 0) { chosen = c; break; }
            }
            if (chosen == -1) continue;
            cnt[chosen]--;
            sb.append((char)('a' + chosen));
  
            for (int c = 0; c < 26; c++) {
                while (cnt[c] > 0) {
                    sb.append((char)('a' + c));
                    cnt[c]--;
                }
            }
            return sb.toString();
        }
        return "";
        
    }
}