class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int removeLeft = 0, removeRight = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                removeLeft++;
            } else if (c == ')') {
                if (removeLeft > 0) removeLeft--; 
                else removeRight++;               
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, removeLeft, removeRight, 0, new StringBuilder(), res);
        return new ArrayList<>(res);
    }

 
    private void dfs(String s, int idx, int removeLeft, int removeRight,
                     int open, StringBuilder sb, Set<String> res) {
        if (idx == s.length()) {
            if (removeLeft == 0 && removeRight == 0 && open == 0) {
                res.add(sb.toString());
            }
            return;
        }
        char c = s.charAt(idx);


        if (c == '(' && removeLeft > 0) {
            dfs(s, idx + 1, removeLeft - 1, removeRight, open, sb, res);
        }
  
        else if (c == ')' && removeRight > 0) {
            dfs(s, idx + 1, removeLeft, removeRight - 1, open, sb, res);
        }


        sb.append(c);
        if (c != '(' && c != ')') {
            dfs(s, idx + 1, removeLeft, removeRight, open, sb, res);
        } else if (c == '(') {
            dfs(s, idx + 1, removeLeft, removeRight, open + 1, sb, res);
        } else if (open > 0) { 
            dfs(s, idx + 1, removeLeft, removeRight, open - 1, sb, res);
        }
        sb.deleteCharAt(sb.length() - 1);
        
    }
}