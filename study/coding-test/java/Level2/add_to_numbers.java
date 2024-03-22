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
    int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        if (l1 == null && l2 == null) {
            if (carry == 1) {
                result.val = carry;
                return result;
            } else {
                return null;
            }
        } 
        
        result.val = add(l1, l2);

        result.next = new ListNode();

        if (l1 == null) {
            result.next = addTwoNumbers(null, l2.next);
        } else if (l2 == null) {
            result.next = addTwoNumbers(l1.next, null);
        } else {
            result.next = addTwoNumbers(l1.next, l2.next);
        }
        
        return result;
    }

    public int add(ListNode l1, ListNode l2) {
        int value;
        
        if (l1 == null) {
            value = l2.val;
        } else if (l2 == null) {
            value = l1.val;
        } else {
            value = l1.val + l2.val;
        }

        if (carry == 1) {
            value += carry;
            carry = 0;
        }

        if (value >= 10) {
            value = value - 10;
            carry = 1;
        }

        return value;
    }
}
