class Solution {
    public String multiply(String num1, String num2) {
         if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];


        for (int j = n - 1; j >= 0; j--) {
            int d2 = num2.charAt(j) - '0';
      
            for (int i = m - 1; i >= 0; i--) {
                int d1 = num1.charAt(i) - '0';
                int pos = i + j;          
                int pos1 = i + j + 1;     


                int sum = d1 * d2 + result[pos1];
                result[pos1] = sum % 10;     
                result[pos] += sum / 10;    
            }
        }

       
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (sb.length() == 0 && num == 0) {
                continue;  
            }
            sb.append(num);
        }

        return sb.toString();
        
    }
}