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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0);
        ListNode c = l;
        int in = 0;
        while (l1 != null || l2 != null || in != 0){
            int s = in;
            if (l1 != null){
                s= s+l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                s =s+l2.val;
                l2 = l2.next;
            }
            in = s/10;
            c.next = new ListNode(s%10);
            c = c.next;
        }
        return l.next;
        
    }
}