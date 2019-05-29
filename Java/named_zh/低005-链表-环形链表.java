package algorithm;

public class HasCycle {
    /**
     * 环形链表
     *
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 3->2->0->-4-|
     *     \-----|(-4->2)
     *
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 1<->2
     *
     * 示例 3：
     * 1
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     * @param args
     */
    public static void main(String[] args) {
        ListNode head=new ListNode(3);
        ListNode next=head.next=new ListNode(2);
//        next=next.next=new ListNode(0);
//        next=next.next=new ListNode(-4);
        next.next=head.next;
        hasCycle(head);
    }

    /**
     * 快慢指针判断是否有环
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast=head,slow=head;
        while(fast!=null &&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
