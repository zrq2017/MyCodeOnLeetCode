/**
存在重复元素 II

给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

示例 1:

输入: nums = [1,2,3,1], k = 3
输出: true
示例 2:

输入: nums = [1,0,1,1], k = 1
输出: true
示例 3:

输入: nums = [1,2,3,1,2,3], k = 2
输出: false

推荐方法三，最快，使用JDK函数
**/
class Solution {
	//方法一：判断为k的间隔有没有相等的，没有k就减小，刨除数组长度为0与k超过长度变小的情况
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        int i=0;
        if(nums.length==1){
            return false;
        }else if(k>=nums.length){
            k=nums.length-1;
        }
        while(k>0){
            if(nums[i]==nums[i+k]){
                return true;
            }
            i++;
            if(i+k>=nums.length){
                k--;
                i=0;
            }
        }
        return false;
    }
	//方法二：双指针原理与方法一同
	public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(nums.length==1 ){
            return false;
        }
        int i=0;
        int j;
        while(i<nums.length-1){
            j=i+1;
            while(j<nums.length&&j-i<=k){
            if(nums[i]==nums[j]){
                return true;
            }
                j++;
            }
             i++;
        }
        return false;
    }
	//方法三
	 public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if (nums.length <= 1) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);//当指针大于k时不满足题意移除超出范围的数
            if (set.contains(nums[i])) return true;//判断去重
            set.add(nums[i]);
        }
        return false;
    }
}