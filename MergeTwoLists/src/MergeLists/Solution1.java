package MergeLists; /**
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
 迭代 iteration
 pre cur
 只要 l1 !=null && l2!= null
 if l1.val <= l2.val
 pre.next = l1;
 pre = l1;
 l1 = l1.next

 curr = curr.next
 迭代当前的curr和pre。 curr会被迭代成下一次
 **/
class Solution1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead; //可以直接让prev引用哨兵节点preHead,因为是同一个对象,prev修改对象属性时，preHead的属性next也就变了

        while (l1!=null && l2!=null){
            //prev的next指针指向当前节点，prev被迭代成当前节点，且较小的那个节点后移
            if(l1.val <= l2.val){
                prev.next = l1;
                prev = l1; //prev = prev.next
                l1 = l1.next;
            }else{
                prev.next = l2;
                prev = l2;
                l2 = l2.next;
            }
        }

        //更简洁的写法
        prev.next = l1 == null ? l2:l1;

        // if(l1 == null){
        //     prev.next = l2;
        // }else if(l2==null){
        //     prev.next = l1;
        // }
        return preHead.next;
    }
}




