/**
最大子序和

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
**/
class Solution {
	//第二个的精简版本
	public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int presum=nums[0],max=presum;
        for(int i=1;i<nums.length;i++){
            presum=presum>0?presum+nums[i]:nums[i];//之前要大于0才会使结果更大
            max=Math.max(max,presum);
        }
        return max;
    }
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 一段一段的子数列的和
        int sum = 0;
        int res = nums[0];//相当于存到目前为止最大的值
        // 动态规划的题
        for (int num : nums) {
            if (sum > 0) {
                // 前面累加的增长 > 0 那就加上我自己再看看
                sum +=num;
            } else {
                // 前面累加的增长 < 0  前面的所有累加的增长都白干了，那就从这一次开始看后面的吧
                sum = num;
            }
            // sum就等于从前面某一天到今天的增长，这一步也可以用判断sum与res的大小
            res = Math.max(res, sum);
        }
        return res;
    }
}