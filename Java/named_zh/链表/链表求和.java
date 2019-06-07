package named_zh;
/**
*问题描述：
*你有两个用链表代表的整数，其中每个节点包含一个数字。
数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。
写出一个函数将两个整数相加，用链表形式返回和。
*样例：
*给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null
*解析：
*由题目知，假定已逆转链表输出
*初始化头结点，根据传入的链表判断是否为空，两者为空则不进入循环，设置进位值
*注：头指针不计入结果链表值计算，仅为表示结果链表入口，即存值为l.next开始
*先进行链表一当前节点和的计算保存到进位值，同时指针向后
*再判断链表二同样存到进位值，同时指针向后
*根据进位除10初始化结果链表下一指针，将结果链表指针置后
*循环结束后若进位值为1则初始化下一结果链表的值
**/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;      
 *     }
 * }
 */
public class Solution {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode rst = new ListNode(0);
        ListNode dummy = rst;
        int carrier = 0;
        //while
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carrier += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carrier += l2.val;
                l2 = l2.next;
            }
            rst.next = new ListNode(carrier % 10);
            carrier = carrier / 10;
            rst = rst.next;
        }
        //check the carrier
        if (carrier == 1) {
            rst.next = new ListNode(1);
        }
        return dummy.next;
    }
}