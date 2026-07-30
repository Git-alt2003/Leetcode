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
    public ListNode sortList(ListNode head) {
             if (head == null || head.next == null) {
            return head;
        }
        

        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        

        ListNode dummy = new ListNode(0, head);
        
        for (int step = 1; step < length; step <<= 1) {
            ListNode prev = dummy;
            ListNode currNode = dummy.next;
            
            while (currNode != null) {
           
                ListNode left = currNode;
                ListNode right = split(left, step);
                currNode = split(right, step);
                
                // Merge the two halves
                prev = merge(left, right, prev);
            }
        }
        
        return dummy.next;
    }
    


    private ListNode split(ListNode head, int size) {
        if (head == null) return null;
        
        for (int i = 1; head.next != null && i < size; i++) {
            head = head.next;
        }
        
        ListNode second = head.next;
        head.next = null; 
        return second;
    }
    


    private ListNode merge(ListNode l1, ListNode l2, ListNode prev) {
        ListNode curr = prev;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        curr.next = (l1 != null) ? l1 : l2;
        

        while (curr.next != null) {
            curr = curr.next;
        }
        
        return curr;
        

    }
}