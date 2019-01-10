/**
存在重复元素

给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
推荐方法一，使用系统排序
**/
class Solution {
	//方法一：先使用冒泡排序，再使用异或进行判断相邻元素是否相等
	public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if((nums[i]^nums[i-1])==0){
                return true;
            }
        }
        return false;
    }
	//方法二：先使用冒泡排序，再使用异或进行判断相邻元素是否相等
    public boolean containsDuplicate(int[] nums) {
        int temp=0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]<nums[j]){
                    temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }
        }
        for(int i=1;i<nums.length;i++){
            if((nums[i]^nums[i-1])==0){
                return true;
            }
        }
        return false;
    }
}