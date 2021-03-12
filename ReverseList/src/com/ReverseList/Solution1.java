package com.ReverseList;

/**
 递归
 头是链表的tail，每次递归的方法让之前已经反转好的指向当前节点。为了防止形成环，让当前节点指向null，断开。
 相当于每次递归方法把自己加到反转好的链表头上。
 每个点依赖自己当前的next被反转好，而且反转的结果需要从当前链表的tail输出，所以递归很合适。从底部开始
 递归的结束条件是tail,即当前节点的next是null或者这个链表本身(head)是null
 **/
class Solution1 {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode preReversed = reverseList(head.next);
        head.next.next = head; //head.next.next即reverseList(head.next) 把head加入到反转好的tail
        head.next = null;//避免形成环

        return  preReversed; //每次反转后的结果的head应该是反转好的head
    }
}