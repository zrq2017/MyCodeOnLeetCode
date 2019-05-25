package algorithm;

/**
 * Created by zrq on 2019-5-25.
 */
public class DeleteNode {
    /**
     * 删除链表中的节点
     * <p>
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     * 4->5->1->9
     * <p>
     * 示例 1:
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * <p>
     * 示例 2:
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     * <p>
     * 说明:
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     *
     * @param args
     */
    public static void main(String[] args) {
        head=new ListNode(4);
        head.next=new ListNode(5);
        ListNode node =head.next.next= new ListNode(1);
        node.next=new ListNode(9);
        deleteNode(node);
    }
    static ListNode head=null;
    public static void deleteNode(ListNode node) {
        //因为不是末尾节点且必是有效节点，没给头节点，那么直接将下一节点值拷贝，删除下一节点就可以
        node.val=node.next.val;
        node.next=node.next.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
