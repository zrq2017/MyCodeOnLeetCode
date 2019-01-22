/**
至少是其他数字两倍的最大数

在一个给定的数组nums中，总是存在一个最大元素 。

查找数组中的最大元素是否至少是数组中每个其他数字的两倍。

如果是，则返回最大元素的索引，否则返回-1。

示例 1:

输入: nums = [3, 6, 1, 0]
输出: 1
解释: 6是最大的整数, 对于数组中的其他整数,
6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
 

示例 2:

输入: nums = [1, 2, 3, 4]
输出: -1
解释: 4没有超过3的两倍大, 所以我们返回 -1.
 

提示:

nums 的长度范围在[1, 50].
每个 nums[i] 的整数范围在 [0, 99].

**/
class Solution {
    /**题目要求的是只要至少两倍大，而不是刚好两倍大
    *一次遍历找到最大的数max1和第二大的数max2，然后看看最大的数是不是大于等于第二大的数的两倍，
    *如果是的话那么肯定满足最大数max1大于等于数组中其他数组的两倍了
    **/
    public int dominantIndex(int[] nums) {
        // 找出最大值和次大值
        int max = -1, maxIndex = -1, secondMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }
        return max >= secondMax * 2 ? maxIndex : -1;
    }
}