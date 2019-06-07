package algorithm;

public class ReverseList {
    /**
     * 反转链表
     *
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * @param args
     */
    public static void main(String[] args) {
//        1->2->3->4->5->NULL
        ListNode head=new ListNode(1);
        ListNode next=head.next=new ListNode(2);
        next=next.next=new ListNode(3);
        next=next.next=new ListNode(4);
        next.next=new ListNode(5);
        reverseList(head);
    }

    /**
     * 1.保存当前指针cur，保存下一个节点的指针next
     * 2.用next判断还有没有节点做循环结束条件
     * 3.循环体中主要做：
     *      1）用temp保存next节点的next指针（下一个循环头）
     *      2）将next节点的next指针指向当前节点（翻转）
     *      3）将当前节点cur移向next节点，即next节点变为下一轮循环的当前节点
     *      4）next指针更新为temp，下一个循环头
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(head==null) return head;
        ListNode cur=head,next=head.next;
        head.next=null;
        while(next!=null){
            ListNode temp=next.next;//临时存储下一节点
            next.next=cur;//将下一节点的next指针指向当前节点
            cur=next;//将当前节点移到下一位
            next=temp;//将next节点移到下一节点
        }
        head=cur;
        return head;
    }

     static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
