package algorithm;

import java.util.Arrays;

public class CanPartition {
    /**
     * 分割等和子集
     * <p>
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 注意:
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * <p>
     * 示例 1:
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * 示例 2:
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {1, 5, 11, 5};
        int[] nums = {99, 2, 3, 98};
        boolean t = canPartition(nums);
    }

    /**
     * 动态规划:dp数组中保存的每个index表示能否由待分割数组组成该dp索引的值即表示能装的下
     * 背包问题：看数组中是否存在加起来为sum/2的数.
     * 背包容量（V） = sum/2
     * 每一个物品只能装一次,如果出现背包中重量等于sum/2则为true else false
     * dp[i]表示能否填满容量为i的背包
     * 状态转移方程为 dp[i] = dp[i-1] || nums[i]+dp[i-nums[j]]
     * 举例:v = sum/2 = 11   nums = [1,5,11,5]  1是true 0 是false
     *         0  1  2  3  4  5  6  7  8  9  10  11
     * nums[0] 0  1  0  0  0  0  0  0  0  0   0   0
     * nums[1] 0  1  0  0  0  1  1  0  0  0   0   0
     * nums[2] 0  1  0  0  0  1  1  0  0  0   0   1
     * nums[3] 0  1  0  0  0  1  1  0  0  0   0   1
     * 注：表示的nums数组每个元素dp数组能否装的下，思想就是取前面的保存过的
     * 所以返回true，因为背包中nums[i]的状态都是由上一行决定的因此可以将二维转化为1维数组从尾部开始
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        if (nums.length < 1) return false;
        int sum = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {//假设可以拆分为两个和相等的子集, 数组的和是子集和的两倍, 一定是偶数
            return false;
        }
        int len = sum / 2;
        int[] dp = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            dp[i] = nums[0] == i ? 1 : 0;//初始化数组
        }
        for (int i = 1, n = nums.length; i < n; i++) {
            for (int j = len; j >= nums[i]; j--) {//每个都先从大容量开始判断是不是刚好能保存或是由其他的组成
                dp[j] = dp[j] == 1 || dp[j - nums[i]] == 1 ? 1 : 0;
            }
        }
        return dp[len]==1;
    }

    /**
     * 回溯思想（超时）: 先排序数组来减少回溯的节点, 然后通过递归遍历剩下的和要比所有的位大于等于（不能小于说明是对半分的）, 遍历所有的可能
     *
     * @param nums
     * @return
     */
    public static boolean canPartitionBack(int[] nums) {
        int sum = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {//假设可以拆分为两个和相等的子集, 数组的和是子集和的两倍, 一定是偶数
            return false;
        }
        Arrays.sort(nums);
        return isPar(nums, sum / 2, 0);
    }

    private static boolean isPar(int[] nums, int remain, int index) {
        if (remain == 0) {
            return true;
        }
        if (index < nums.length && remain < nums[index]) {//退出条件是没有剩余的和，及剩下的和小于当前位（表示前一半分的多，没法均分，不满足条件）
            return false;
        }
        for (int i = index, len = nums.length - 1; i < len; i++) {//该循环相当于全排列，列出所有的可能情况
            if (isPar(nums, remain - nums[i], i + 1)) {//判断下一层是不是符合条件的子集
                return true;
            }
        }
        return false;
    }
}
