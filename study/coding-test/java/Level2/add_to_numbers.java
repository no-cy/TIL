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

// 리팩토링 후
class Solution {
    int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            if (carry == 0) {
                return null;
            }
            ListNode result = new ListNode(carry);
            carry = 0; // 재사용을 위해 carry를 리셋
            return result;
        }

        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
        carry = sum / 10;
        ListNode result = new ListNode(sum % 10);

        // 다음 노드로 재귀 호출
        ListNode next1 = l1 != null ? l1.next : null;
        ListNode next2 = l2 != null ? l2.next : null;
        result.next = addTwoNumbers(next1, next2);

        return result;
    }
}


// 리팩토링 전
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



