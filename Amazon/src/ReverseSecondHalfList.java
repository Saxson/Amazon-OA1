/**
 * Created by whr on 12/2/15.
 */
public class ReverseSecondHalfList {
    static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    public static ListNode reverseList(ListNode p){
        if(p == null || p.next == null) return p;
        ListNode cur = p;
        ListNode prev = null;
        ListNode after = p;
        while(cur != null) {
            after = cur.next;
            cur.next = prev;
            prev = cur;
            cur = after;
        }
        return prev;
    }
    public static ListNode reverseSecondHalf(ListNode head){
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next.next;//看情况改变
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = reverseList(slow.next);
        return head;
    }
}
