/*****
两两交换链表中的节点

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.
**/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //两两交换，pre指向前一个交换节点，cur指向后一个交换节点，preno指向第一个交换节点的前一个节点
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null) return head;//head为空退出条件判断
        ListNode pre=head,cur=head.next,t=null,preno=null;
        while(cur!=null){
            t=cur.next;//交换两个节点
            cur.next=pre;
            pre.next=t;
            if(pre==head){//将第一个交换节点的前一个节点指向第二个节点
                head=cur;
            }else {
                preno.next=cur;
            }
            if(t!=null&&t.next!=null){//移动当前节点指针
                preno=pre;
                pre=t;
                cur=t.next;
            }else{
                cur=null;//退出判断
            }
        }
        return head;
    }
}