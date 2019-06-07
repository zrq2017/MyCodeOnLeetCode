package algorithm;

public class SortList {

    /**
     * 排序链表
     * <p>
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     * 示例 1:
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * <p>
     * 示例 2:
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     * @param args
     */
    public static void main(String[] args) {
//        4->2->1->3排序测试
//        ListNode head = new ListNode(4);
//        ListNode next = head.next = new ListNode(2);
//        next = next.next = new ListNode(1);
//        next.next = new ListNode(3);
//        Object obj = sortList(head);
//        -1->5->3->4->0 排序测试
        ListNode head = new ListNode(-1);
        ListNode next = head.next = new ListNode(5);
        next = next.next = new ListNode(3);
        next = next.next = new ListNode(4);
        next.next = new ListNode(0);
        Object obj = sortList(head);
        System.out.println("OK");
    }

    /**
     * 归并排序法：在动手之前一直觉得空间复杂度为常量不太可能，因为原来使用归并时，都是 O(N)的，
     * 需要复制出相等的空间来进行赋值归并。对于链表，实际上是可以实现常数空间占用的（链表的归并
     * 排序不需要额外的空间）。利用归并的思想，递归地将当前链表分为两段，然后merge，分两段的方
     * 法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，知道快的走到了末尾，然后
     * 慢的所在位置就是中间位置，这样就分成了两段。merge时，把两段头部节点值比较，用一个 p 指向
     * 较小的，且记录第一个节点，然后 两段的头一步一步向后走，p也一直向后走，总是指向较小节点，
     * 直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。
     * <p>
     * 主要考察3个知识点，
     * 知识点1：归并排序的整体思想
     * 知识点2：找到一个链表的中间节点的方法
     * 知识点3：合并两个已排好序的链表为一个新的有序链表
     * <p>
     * 类似：希尔+归并
     * <p>
     * 思路：
     * 1）递归将链表分裂成两个链表，递归的底层就是一个个的链表节点（看成排序好的链表）
     * 2）根据递归的返回性质将每层返回的两个排序链表进行归并
     * 3）递归完成后获得新的排序好的链表
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;//链表为空或者链表为单个节点不具备排序的意义
        ListNode fast = head, slow = head, pre = null;//使用快慢指针，并保存慢指针的前一个节点用于将一个链表分成两个链表
        while (fast != null && fast.next != null) {//这儿需要注意快指针要判断的是快指针及其next节点是否为空，不然后抛空指针异常，慢指针不需要判断
            //快指针到链表尾的时候，慢指针刚好到链表的中间
            pre = slow;
            slow = slow.next;//慢指针走一步
            fast = fast.next.next;//快指针走两步
        }
        pre.next = null;//将链表分裂为两个
        ListNode n1 = sortList(head);//前半部分链表节点继续排序
        ListNode n2 = sortList(slow);//后半部分链表节点继续排序
        /**
         * 归并排序两个排序好的链表：
         * 1）递归到最后只有单节点，通过这个方法会将单节点变成排序链表（由上而下）
         * 2）然后递归返回与上一层的链表继续两个排序好的链表进行归并排序再递归返回过程（由下而上）
         */
        return mergeSort(n1, n2);
    }

    //辅助两个有序链表进行归并排序
    private static ListNode mergeSort(ListNode n1, ListNode n2) {
        ListNode dummyHead = new ListNode(0);//构造虚拟的头结点用于两个链表的排序
        ListNode cur = dummyHead;//记录当前排序到的节点
        while (n1 != null && n2 != null) {
            if (n1.val <= n2.val) {//谁小谁在前面
                cur.next = n1;//当前节点的下一节点为当前的较小值
                cur = cur.next;//当前节点移动到排序好的链表的末尾节点
                n1 = n1.next;//继续访问待归并的链表节点
            } else {
                cur.next = n2;
                cur = cur.next;
                n2 = n2.next;
            }
        }
        //那个链表不为空就是没归并完，将指针相连合并剩下的链表
        if (n1 != null) {
            cur.next = n1;
        }
        if (n2 != null) {
            cur.next = n2;
        }
        return dummyHead.next;//返回抛开虚拟头结点的排序好的链表
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
