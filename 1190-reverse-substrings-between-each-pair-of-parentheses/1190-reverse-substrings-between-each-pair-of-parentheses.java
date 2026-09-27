class Solution {
    public String reverseParentheses(String s) {
         Deque<String> stack = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
            
                stack.push(cur.toString());
                cur.setLength(0);
            } else if (c == ')') {
           
                cur.reverse();
                cur.insert(0, stack.pop());
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
        
    }
}