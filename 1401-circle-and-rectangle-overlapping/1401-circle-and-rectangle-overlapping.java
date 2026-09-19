class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
         int dx = Math.max(Math.max(x1 - xCenter, xCenter - x2), 0);
        int dy = Math.max(Math.max(y1 - yCenter, yCenter - y2), 0);
        return dx * dx + dy * dy <= radius * radius;
    
        
    }
}