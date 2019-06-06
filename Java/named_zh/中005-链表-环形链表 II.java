package algorithm;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle {
    /**
     * 环形链表 II
     * <p>
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * 说明：不允许修改给定的链表。
     * <p>
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 3->2->0->-4-/  (-4->2)
     *     \-----/
     * <p>
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：tail connects to node index 0
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 1->2-/  (2->1)
     *  \-/
     * <p>
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：no cycle
     * 解释：链表中没有环。
     * 1
     * <p>
     * 进阶：
     * 你是否可以不用额外空间解决此题？
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode next = head.next = new ListNode(2);
        next = next.next = new ListNode(0);
        next = next.next = new ListNode(-4);
        next.next = head.next;
        detectCycle(head);
    }

    //使用set存储出现过的节点有环就返回，没有返回null
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
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
