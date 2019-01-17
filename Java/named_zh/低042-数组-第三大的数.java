/**
第三大的数

给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:

输入: [3, 2, 1]

输出: 1

解释: 第三大的数是 1.
示例 2:

输入: [1, 2]

输出: 2

解释: 第三大的数不存在, 所以返回最大的数 2 .
示例 3:

输入: [2, 2, 3, 1]

输出: 1

解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
存在两个值为2的数，它们都排第二。

**/
class Solution {
    public int thirdMax(int[] nums) {
        if(nums.length<3){
            Arrays.sort(nums);
            return nums[nums.length-1];
        }
        boolean f=true;//用f表示有一个数与最小整数相同
        int tag=0;//用tag表示有没有三个数大小排列的数
        int fir=Integer.MIN_VALUE,sec=Integer.MIN_VALUE,thi=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==Integer.MIN_VALUE&&f){
                tag++;
                f=false;
            }
            if(nums[i]>fir){
                tag++;
                thi=sec;
                sec=fir;
                fir=nums[i];
            }else if(nums[i]>sec&&nums[i]<fir){
                tag++;
                thi=sec;
                sec=nums[i];
            }else if(nums[i]>thi&&nums[i]<sec){
                thi=nums[i];
                tag++;
            }
        }
        return tag>=3?thi:fir;
    }
}