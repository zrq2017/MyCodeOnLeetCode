package algorithm;

/**
 * Created by zrq on 2019-5-25.
 */
public class AddTwoNumbers {
    /**
     * 两数相加
     * <p>
     * 给出两个 非空 的链表用来表示两个非负的整数。
     * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1=new ListNode(2);
        ListNode lnext=l1.next=new ListNode(4);
        lnext.next=new ListNode(3);
        ListNode l2=new ListNode(5);
        ListNode rnext=l2.next=new ListNode(6);
        rnext.next=new ListNode(4);
        addTwoNumbers(l1,l2);
    }

    /**
     * 新建虚拟头结点，遍历访问两个链表保存值，使用进位标志保存当前位是否进位
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);//虚拟头结点
        ListNode cur=head;
        int tag=0;//进位标志
        while(l1!=null&&l2!=null){
            int temp=l1.val+l2.val+tag;
            cur.next=new ListNode(temp%10);
            cur=cur.next;//指针移到链表下一节点
            l1=l1.next;
            l2=l2.next;
            if(temp>=10){
                tag=1;
            }else {
                tag=0;
            }
        }
        while(l1!=null){
            int temp=l1.val+tag;
            cur.next=new ListNode(temp%10);
            cur=cur.next;
            l1=l1.next;
            if(temp>=10){
                tag=1;
            }else {
                tag=0;
            }
        }
        while(l2!=null){
            int temp=l2.val+tag;
            cur.next=new ListNode(temp%10);
            cur=cur.next;
            l2=l2.next;
            if(temp>=10){
                tag=1;
            }else {
                tag=0;
            }
        }
        if(tag==1){//处理当前还有进位的情况
            cur.next=new ListNode(1);
        }
        return head.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
