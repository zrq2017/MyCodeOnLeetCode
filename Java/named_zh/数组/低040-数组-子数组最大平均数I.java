/**
子数组最大平均数 I

给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

示例 1:

输入: [1,12,-5,-6,50,3], k = 4
输出: 12.75
解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 

注意:

1 <= k <= n <= 30,000。
所给数据范围 [-10,000，10,000]。

**/
class Solution {
	//方法一：暴力一次循环内累加，贼慢
    public double findMaxAverage1(int[] nums, int k) {
        double avg=0;
        for(int i=0;i<=nums.length-k;i++){
            int temp=0;
            for(int j=0;j<k;j++){
                temp+=nums[i+j];
            }
            if(i==0){
                avg=temp*1.0/k;
            }else{
                avg=Math.max(temp*1.0/k,avg);
            }
        }
        return avg;
    }
	//方法一：暴力一次循环内累加，贼慢
	public double findMaxAverage1(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++)
                sum += nums[j];
            max = Math.max(max, sum);
        }
        return max * 1.0 / k;
    }
}