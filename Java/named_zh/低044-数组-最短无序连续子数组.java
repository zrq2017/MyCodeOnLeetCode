/**
最短无序连续子数组

给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。

示例 1:

输入: [2, 6, 4, 8, 10, 9, 15]
输出: 5
解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
说明 :

输入的数组长度范围在 [1, 10,000]。
输入的数组可能包含重复元素 ，所以升序的意思是<=。

**/
class Solution {
    //双指针指向头尾，复制一个排序数组和它作比较，即可得到最小
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length==1) return 0;
        int[] temp=Arrays.copyOf(nums,nums.length);
        int i=0,j=nums.length-1;
        Arrays.sort(temp);
        int flag=1;
        while(i<j){
            flag=1;
            if(nums[i]==temp[i]){//相等， i ++
                i++;flag=0;
            }
            if(nums[j]==temp[j]){//相等， j--
                j--;flag=0;
            }
            if(flag==1) break;//都不相等， 就break
        }
        if(i>=j) return 0;//指针在中间就是没有返回0
        return j-i+1;//长度为末减头加1
    }
	/**这一种比较快，但是考虑的情况太多，主要就是找头递增和尾递减的拐点，只不过是一次遍历
	int findUnsortedSubarray(int* nums, int numsSize) {
    if (numsSize < 2) {
        return 0;
    }
    // 分别找到两个拐点 start是开始递增 突然开始下降的地方
    // end 倒序看 连续递减 突然上升的地方
    int start = 0;
    int end = numsSize-1;
    for (int i = 1; i < numsSize; i++) {
        if (nums[i] >= nums[i-1]) {
            start++;
        } else {
            break;
        }
    }
    if (start == numsSize - 1) {
        // 已经是连续递增了
        return 0;
    }
    
    for (int i = numsSize-2; i >= 0; i--) {
        if (nums[i] <= nums[i+1]) {
            end--;
        } else {
            break;
        }
    }
    
    if (start >= end) {
        // 程序出错
        return 0;
    }
    
    // 找出从start 到 end之间的最大值与最小值
    int max = nums[start];
    int min = nums[start];
    for (int i = start + 1; i <= end; i++) {
        if (nums[i] > max) {
            max = nums[i];
        }
        if (nums[i] < min) {
            min = nums[i];
        }
    }
    
    // 找到之后，分别从前面跟后面找
    int starti = 0;
    for (int i = 0; i <= start; ) {
        int j = i;
        while (++j <= start && nums[j] == nums[i]) {
        }
        if (nums[i] > min) {
            // 这个数字不能用 需要排序
            starti = i;
            break;
        } else {
            i = j;
        }
    }
    
    int endi = numsSize - 1;
    for (int i = numsSize - 1; i >= end; ) {
        // 过滤掉相等的数据
        int j = i;
        while (--j >= end && nums[j] == nums[i]) {
        }
        if (nums[i] < max) {
            // 这个值不能用 需要排序
            endi = i;
            break;
        } else {
            i = j;
        }
    }
    
    return endi - starti + 1;
	**/
}