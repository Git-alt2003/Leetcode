class Solution {
    public int romanToInt(String s) {
        java.util.Map<Character, Integer> romanMap = new java.util.HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int total = 0;
        int prevValue = 0;
        
        
        for (int i = s.length() - 1; i >= 0; i--) {
            int currValue = romanMap.get(s.charAt(i));
            
            if (currValue < prevValue) {
                
                total -= currValue;
            } else {
                total += currValue;
            }
            
            prevValue = currValue;
        }
        
        return total;
        
    }
}