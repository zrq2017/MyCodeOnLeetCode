/*****
合并两个有序链表

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
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
    /**
    * 循环比较两个链表，移动链表指针，head表示头结点，tag表示当前节点，t为临时存储节点
    **/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=null,tag=null,t=null;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){//l1小于l2的情况
                if(head==null){//头结点为空的情况，将头结点置为最小值所在的链表，并将tag指向当前节点
                    head=l1;
                    tag=l1;
                }else {
                    tag.next= l1;//head不为空的时候，将当前节点的下一个节点的指针指向下一个最小的节点
                    tag=tag.next;//并将当前节点移动到下一个节点
                }
                t = l1.next;//将当前链表移动到下一个节点，并将当前节点的下一个节点置空，与要比较的两个链表断开
                l1.next=null;
                l1=t;
            }else{
                if(head==null){
                    head=l2;
                    tag=l2;
                }else {
                    tag.next = l2;
                    tag=tag.next;
                }
                t=l2.next;
                l2.next=null;
                l2=t;
            }
        }
        if(head==null) {//当两个节点其中一个为空的时候的处理情况
            head=l1==null?l2:l1;
            return head;
        }
        if(l1!=null){//处理一个节点还有剩余的情况
            tag.next=l1;
        }else if(l2!=null){
            tag.next=l2;
        }
        return head;
    }
}