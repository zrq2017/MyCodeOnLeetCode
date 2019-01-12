/**
合并两个有序数组
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:

输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
**/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;//获得合并后的数组长度
        while (m >= 0 && n >= 0) {//结束条件是只要有一个遍历结束
            //从末尾指针开始保存元素，nums1与nums2谁大就保存，保存的指针就降低且尾指针也降低
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        //若是n还有元素没进来，就继续插入
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }
}