package algorithm;

public class MergeKLists {
    /**
     * 合并K个排序链表
     * <p>
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * 示例:
     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * @param args
     */
    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);
        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);
        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);
        mergeKLists(lists);
    }

    //两两归并得到结果（不需要额外空间，复杂度O(n^2)）
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);//虚拟头结点
        for (int i = 0, len = lists.length; i < len; i++) {
            head.next = mergeList(head.next, lists[i]);
        }
        return head.next;
    }

    private static ListNode mergeList(ListNode first, ListNode second) {
        ListNode fc = first, sc = second, head = new ListNode(0);
        ListNode cur = head;
        while (fc != null && sc != null) {
            if (fc.val <= sc.val) {
                cur.next = fc;
                fc = fc.next;
                cur = cur.next;
                cur.next = null;
            } else {
                cur.next = sc;
                sc = sc.next;
                cur = cur.next;
                cur.next = null;
            }
        }
        if (fc != null) {
            cur.next = fc;
        }
        if (sc != null) {
            cur.next = sc;
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
