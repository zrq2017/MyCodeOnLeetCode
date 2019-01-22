/**
三个数的最大乘积

给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

示例 1:

输入: [1,2,3]
输出: 6
示例 2:

输入: [1,2,3,4]
输出: 24
注意:

给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。

**/
class Solution {
    public int maximumProduct(int[] nums) {
        // 可以先排好序 然后分情况考虑
        if(nums==null || nums.length==0){
            return -1;
        }
        Arrays.sort(nums);
        int a = nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];//三个最大的正的相乘
        int b = nums[0]*nums[1]*nums[nums.length-1];//两个最大的负的相乘再乘以最大的正的
        return a>=b?a:b;//可能三个负的那就a=b
    }
}