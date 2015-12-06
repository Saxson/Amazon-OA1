/**
 * Created by whr on 12/4/15.
 */
class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
}
public class InsertInCircleLinkedList {
    public static ListNode insert(ListNode head, ListNode insertNode) {
        if(head == null) {
            head = insertNode;
            head.next = head;
            return head;
        }
        ListNode curNode = head;
        ListNode nextNode = curNode.next;
        while(nextNode != head) {
            if(insertNode.val >= curNode.val && insertNode.val < nextNode.val) break;
            curNode = nextNode;
            nextNode = nextNode.next;
        }
        curNode.next = insertNode;
        insertNode.next = nextNode;
        return head;
    }

}
