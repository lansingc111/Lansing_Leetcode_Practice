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

/**
 双指针

 1.双指针 建一个pre，指向head， left, right = pre
 /left=head, right=head, **错，
 重要: 因为当head是要被删除的节点时，[1,2,3],n=3时，right=head,往前走3步就已经是null了,再调用right.next就报错
 2.因为要删除，所以left最好在倒数第n+1的为止(即前一位,所以要pre)->right和left的距离(长度的算法b-a+1)需要是n+1
 ->right比head先走n步，这样left和right的距离就是n+1
 当right走到tail(next==null)时，left正好在倒数第n+1个节点。
 3.通过right.next = right.next.next跳过第倒数第n个节点，即删除

 时间复杂度:O(L) L为链表长度
 空间复杂度:O(1) 只存了两个常量空间 两个指针
 0 1 2 3 4 5
 right

 **/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0,head);
        ListNode left = pre, right = pre; //right和left都不可以是head
        for(int i=0; i<n; i++){
            right = right.next;
        }

        while(right.next != null){ //如果right是head,输入[1],1时，right在此时是null，调用next会null pointer异常
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return pre.next;
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}