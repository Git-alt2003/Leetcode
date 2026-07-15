class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows){
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i< numRows; i++){
            rows[i] = new StringBuilder();
        }
        int cr = 0;
        boolean gd = true;
        for (char ch : s.toCharArray()){
            rows[cr].append(ch);
            if (cr == numRows-1){
                gd = false;

            }
            else if (cr == 0){
                gd = true;

            }
            if (gd){
                cr++;
            }
            else{
                cr--;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows){
            ans.append(row);
        }
        return ans.toString();
        
    }
}