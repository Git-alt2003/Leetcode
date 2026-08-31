/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
         if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        
        int first = -1;   // position of first critical point
        int prevCritical = -1; // position of previous critical point
        int minDist = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int pos = 1; // curr is at position 1 (0-indexed: head is 0)
        
        while (curr.next != null) {
            // Check if curr is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                if (first == -1) {
                    first = pos;
                } else {
                    minDist = Math.min(minDist, pos - prevCritical);
                }
                prevCritical = pos;
            }
            prev = curr;
            curr = curr.next;
            pos++;
        }
        
        if (first == -1 || prevCritical == first) {
            return new int[]{-1, -1};
        }
        
        int maxDist = prevCritical - first;
        return new int[]{minDist, maxDist};
        
    }
}