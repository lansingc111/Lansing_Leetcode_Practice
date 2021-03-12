package com.ReverseList; /**
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
 迭代
 把当前节点的下一个节点改成前一个节点pre。需要额外的指针存储前一个节点。当前节点成为被翻转后的tail，成为pre
 由于当前节点的下一个节点被修改，所以需要提前存储当前节点的下一个节点tmpNext，一遍连续遍历原链表
 由于curr是还未操作的且为终止条件，所以最终输出的反转链表的头节点是pre而不是curr

 时间复杂度：O(n) n是链表的长度
 空间复杂度: O(1) 不需要额外的空间
 **/
class Solution0 {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while(curr != null){
            ListNode tmpNext = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmpNext;
        }
        return pre;
    }
}

/**
 * 1 2
 * NULL <- 1
 * NULL <- 1 <- 2
 * node.next.next = node
 **/

