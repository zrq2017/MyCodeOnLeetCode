/*****
删除与获得点数

给定一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

示例 1:

输入: nums = [3, 4, 2]
输出: 6
解释: 
删除 4 来获得 4 个点数，因此 3 也被删除。
之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
示例 2:

输入: nums = [2, 2, 3, 3, 3, 4]
输出: 9
解释: 
删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
注意:

nums的长度最大为20000。
每个整数nums[i]的大小都在[1, 10000]范围内。


**/
class Solution {
    /**
    dp[i]表示到i位置时不相邻数能形成的最大和，那么状态转移方程怎么写呢，我们先拿一个简单的例子来分析一下，
	比如说nums为{3, 2, 1, 5}，那么我们来看我们的dp数组应该是什么样的，
	首先dp[0]=3没啥疑问，再看dp[1]是多少呢，由于3比2大，所以我们抢第一个房子的3，当前房子的2不抢，
	所以dp[1]=3，那么再来看dp[2]，由于不能抢相邻的，
	所以我们可以用再前面的一个的dp值加上当前的房间值，和当前房间的前面一个dp值比较，取较大值当做当前dp值，
	所以我们可以得到状态转移方程dp[i] = max(num[i] + dp[i - 2], dp[i - 1]), 由此看出我们需要初始化dp[0]和dp[1]，
	其中dp[0]即为num[0]，dp[1]此时应该为max(num[0], num[1])
    **/
     // 打家劫舍的问题，两个连续的数字不能被同时使用
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num1 : nums) {
            if (max < num1) {
                max = num1;
            }
        }
        int[] values = new int[max + 1];//先取出最大的值，根据最大的值建立空间
        for (int num : nums) {
            values[num] += num;//计算数组中每个不重复元素的出现个数
        }
        return getMaxRob(values);
    }

    private int getMaxRob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //对于前 index - 1家，找到一个最大值.
        int[] maxRob = new int[nums.length];
        int[] maxValue = new int[nums.length];
        maxRob[0] = nums[0];
        maxRob[1] = nums[1];
        maxValue[1] = nums[0];
        for (int i = 2; i < nums.length; i++) {//dp保证前面的都是最大的，不抢相邻的所以需要用前两个和前一个与当前相加判断更大的
            maxRob[i] = nums[i] + maxValue[i - 1];
            maxValue[i] = Math.max(maxValue[i - 1], maxRob[i - 1]);
        }
        return Math.max(maxValue[nums.length - 1], maxRob[nums.length - 1]);
    }
}