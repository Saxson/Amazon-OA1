/**
 * Created by whr on 12/2/15.
 */
public class LinkedListHasCycle {
    static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int x) {
            val = x;
        }
    }

    public static boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }


    //求出交点

    public static ListNode hasCycle2(ListNode head) {
        if(head == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        if(fast == null || fast.next == null) return null;
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
