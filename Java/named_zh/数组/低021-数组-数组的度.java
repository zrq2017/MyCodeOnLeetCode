/**
数组的度

给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。

你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

示例 1:

输入: [1, 2, 2, 3, 1]
输出: 2
解释: 
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
示例 2:

输入: [1,2,2,3,1,4,2]
输出: 6
注意:

nums.length 在1到50,000区间范围内。
nums[i] 是一个在0到49,999范围内的整数。
**/
class Solution {
	/**
	第一步：首先还是利用HashMap类来统计各个数字出现的次数，将数值和次数分别作为key和value存入HashMap。
	第二步：找到频率最大的那几个数值（可以是一个也可以是多个数值），然后每一个分别用双指针方法找到在数组中第一次和最后一次出现的位置，
	找到他们中距离最小的那个，返回这个距离。
	**/
    public int findShortestSubArray(int[] nums) {
        int maxCount = 1;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int n:nums){//计算各个数出现次数
            if(hm.containsKey(n)){
                int temp = hm.get(n) + 1;
                hm.put(n, temp);
                if(maxCount < temp){
                    maxCount = temp;
                }
            }else{
                hm.put(n,1);
            }
        }
        Set<Integer> set = hm.keySet();
        int minDis = Integer.MAX_VALUE;//保存多个数组度最小的那个值
        for(int n:set){
            int temp = Integer.MAX_VALUE;
            if(hm.get(n) == maxCount){
                int i=0,j = nums.length-1;
                while(nums[i] != n && i<j)
                    i++;//左指针增加找到第一个相等的
                while(nums[j] != n && i<j)
                    j--;//右指针减少找到第一个相等的
                temp = j - i + 1;//某个数组的度的大小
            }
            minDis = Math.min(temp,minDis);
        }
        return minDis;
    }
}