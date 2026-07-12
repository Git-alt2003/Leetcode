class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int m=0;
        int l=0;
        for(int r=0;r<s.length();r++){
            char ch = s.charAt(r);
            while(set.contains(ch)){
                set.remove(s.charAt(l));
                l++;
            }
            set.add(ch);
            int len = r-l+1;
            m = Math.max(m,len);
        }
        return m;
    }
}