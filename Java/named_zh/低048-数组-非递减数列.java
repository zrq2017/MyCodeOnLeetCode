/**
非递减数列

给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。

我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。

示例 1:

输入: [4,2,3]
输出: True
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
示例 2:

输入: [4,2,1]
输出: False
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。

**/
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {//若是后面一个大于前面一个，计数增加
                count++;
                if (count > 1) {
                    return false;
                }
                int privous = i > 1 ? nums[i - 2] : 0;//判断前一个，下标为1前一个是第一个为0
                int after = i < nums.length - 1 ? nums[i + 1] : 10000;//判断i是不是越界，是设为最后一个
                if (nums[i - 1] > after && nums[i] < privous) {//判断当前的前一个是不是比当前后一个大，同时当前这个小于前面一个
                    //举例：下标0元素比2大，1比0小
                    return false;
                }
            }
        }
        return true;
    }
}