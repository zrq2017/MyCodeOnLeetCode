package algorithm;

import java.util.ArrayList;
import java.util.List;

public class RotateRight {
    /**
     * 旋转链表
     * <p>
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * <p>
     * 示例 2:
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     *
     * @param args
     */
    public static void main(String[] args) {
        int k = 2;
        ListNode head = new ListNode(1);
        ListNode next = head.next = new ListNode(2);
        next=next.next=new ListNode(3);
        next=next.next=new ListNode(4);
        next.next=new ListNode(5);
        rotateRight(head, k);
    }

    /**
     * 使用一个列表保存链表的指针数组，然后按照k值取链表的节点数添加到头部
     * 改进，使用两次循环遍历，第一次获得尾节点和链表长度，第二次获得要断开的前半部分的链表尾节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int len = list.size();//k==len返回head
        k %= len;
        if(k==0) return head;//处理好所有可能的不需要移动的情况
        int start = len - k;//start=5-2=3(不需要减1)
        list.get(start-1).next = null;//获得要断开的前一半链表的尾节点
        list.get(len - 1).next = head;//将原先的链尾与链头连接组成新的链表
        head = list.get(start);//将头指针指向链头
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
