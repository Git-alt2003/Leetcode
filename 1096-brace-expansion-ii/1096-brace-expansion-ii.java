class Solution {
    private int i = 0;
    private String expr;
    public List<String> braceExpansionII(String expression) {
         this.expr = expression;
        this.i = 0;
        TreeSet<String> result = parseExpr();
        return new ArrayList<>(result);
    }



    private TreeSet<String> parseExpr() {
        TreeSet<String> result = parseTerm();
        while (i < expr.length() && expr.charAt(i) == ',') {
            i++; 
            result.addAll(parseTerm());
        }
        return result;
    }


    private TreeSet<String> parseTerm() {
        TreeSet<String> result = new TreeSet<>();
        result.add(""); 
        while (i < expr.length() && expr.charAt(i) != ',' && expr.charAt(i) != '}') {
            TreeSet<String> factor;
            char c = expr.charAt(i);
            if (c == '{') {
                i++; 
                factor = parseExpr();
                i++; 
            } else {
                factor = new TreeSet<>();
                factor.add(String.valueOf(c));
                i++; 
            }
            
            TreeSet<String> next = new TreeSet<>();
            for (String a : result) {
                for (String b : factor) {
                    next.add(a + b);
                }
            }
            result = next;
        }
        return result;
        
    }
}