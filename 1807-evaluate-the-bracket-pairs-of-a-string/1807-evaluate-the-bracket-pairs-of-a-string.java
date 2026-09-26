class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> k : knowledge) {
            map.put(k.get(0), k.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int i = 0, n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = s.indexOf(')', i);
                String key = s.substring(i + 1, j);
                sb.append(map.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
        
    }
}