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
双链表
1. 同时遍历两个链表，如果短链表遍历结束，则以0补位直到长链表也遍历完
2. 两个链表同一位置的值相加，产生的carry为 (a+b+carry)/10, 在和链表留下的值为 (a+b+carry)%10
3. 如果长链表的最后一位相加结果有carry，则在和链表增加一个节点，为carry的值

时间复杂度:O(max(m,n)), 处理/相加每个位置的元素的时间复杂度为O(1)
空间复杂度:O(max(m,n)) 取决于长链表的大小 
 **/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //建一个新的用来寸结果的链表的头节点和尾节点
        ListNode head = null, tail = null; 
        int carry = 0; 

        //开始遍历链表，只要有一个链表还未到尾巴，就持续遍历
        while(l1!= null || l2!= null){
            //确定要相加的值，0补位短链表缺失位
            int num1 = l1 != null ? l1.val : 0; 
            int num2 = l2 != null ? l2.val : 0;
            int res = (num1+num2+carry)%10 ;
            carry = (num1+num2+carry)/10;
            
            //如果是相加的起点，把结果同时赋给head和tail，之后以tail遍历结果链表
            if(head == null){
                head = tail = new ListNode(res);
            }else{
                tail.next = new ListNode(res);
                tail = tail.next;
            }
            //一组相加结束后，各链表指针往后走
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
            
        }
        //如果carry大于0，则给结果链表增加一个节点为carry值
        if(carry > 0){
            tail.next = new ListNode(carry);
        }

        return head;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0); 
        ListNode cur = head; 
        int carry=0;

        while(l1 != null || l2 != null){
            int n1 = l1 != null ? l1.val : 0; 
            int n2 = l2 != null ? l2.val : 0; 

            int sum = n1+n2+carry; 

            int res = sum % 10; 
            carry = sum/10;

            cur.next = new ListNode(res);
            cur = cur.next;

            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(carry > 0){
            cur.next = new ListNode(carry);
        }
        return head.next;
    }
}