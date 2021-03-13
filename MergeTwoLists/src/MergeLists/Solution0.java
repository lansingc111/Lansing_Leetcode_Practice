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
 递归
 递归到其中一个链表底之后，即l1/l2为空是，退出，返回另一个链表的当前node(即归的值)

 递归方法的核心就是跟之后关联(递，让它接着找)，且告诉调用它的前一步递归方法是我(或者用我的哪个值)(归，告诉它结果)
 所以在这里要连接的每一次方法里较小的那个值，让这个node的next与下一次递归关联,每次返回较小的那个node。因为要先递归到底再一步步返回，
 所以每一次递归的return都在最后。先递再归

 此题中归的一定是较小的那个值，递的是较大node的和较小node的之后的值。

 时间复杂度：O(m+n)? 其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），
 函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。? O(2min(m,n)+1)

 空间复杂度：O(m+n) 递归调用栈的时候需要额外的空间。栈的空间取决于递归调用的深度。结
 束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)?。 O(2min(m,n)+1)?


 **/
class Solution0 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curr = null;

        //退出条件
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        if(l1.val <= l2.val){
            curr = l1;
            curr.next = mergeTwoLists(l1.next, l2);
        } else{
            curr = l2;
            curr.next = mergeTwoLists(l1, l2.next);
        }
        return curr;
        // pre.next = curr;
        // pre = pre.next;
        // return pre;
    }
}

/**
 merge(1,1) cur = 1, 1 ->   merge(2,1) ->  merge(2,3)  -> merge(4,3)  ->merge(4,4)   ->merge(null,4)-> return 4
                 curr1     curr 1         curr2          curr3         curr 4         return 4
                 1->1      1->2           2->3           3->4          4 -> 4
                 return 1  return1        return2        return 3      return 4


 **/

// merge(1,1)
// merge(2,1)
// merge(2,3)
// merge(4,3)
// merge(4,4)-> 4
// merge(null,4) 4


