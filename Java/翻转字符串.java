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
        // write your code
         if (s == null || s.length() == 0 || s.indexOf(" ") == -1) {
            return s;
        }

        String[] strs = s.split(" ");
        if (strs.length == 0) {
            return s;
        }
        StringBuffer sb = new StringBuffer();

        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i] + " ");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}