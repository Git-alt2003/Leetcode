class Solution {
    public int myAtoi(String s) {
            String b = s.strip();
    
    long z = 0;
    int x = 0;
    int y = 0;
    
   for(int i = 0; i < b.length(); i++){
    char ch = b.charAt(i);
    if(ch == '-' && i == 0){
        y =  -1;
    }
    else if(ch == '+' && i == 0){
        y = 1;
    }
    else if(Character.isDigit(ch)){
        if((y == 1 || y==0) &&z* 10 + Character.getNumericValue(ch) > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        else if(y == -1 && (z* 10 + Character.getNumericValue(ch) ) * y < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        else {z = z * 10 + Character.getNumericValue(ch);

        };

    }
    
    else{
        break;
    }
    
   }
   
   if(y == -1){
    z = z * y;
   }

   if(z > Integer.MAX_VALUE){
    return Integer.MAX_VALUE;
   }
   else if(z < Integer.MIN_VALUE){
    return Integer.MIN_VALUE;
   }

   else if(y == 1 || y==0 || y == -1){
    x = (int) z;
   return x;
    
   }
   return x;
   
    }
}
   

      

 
        
    
