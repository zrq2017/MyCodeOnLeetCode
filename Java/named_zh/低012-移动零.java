/**
移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
推荐方法一 方法二思想的简化版
**/
class Solution {
	//方法一
	public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
	//方法二
    public void moveZeroes2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {//i,j指针同时增加，j!=i代表外层判断中刚过去一个0元素，j指针还在0那，刚好交换并将i,j指针同步
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = temp;
                } else {
                    j++;
                }
            }
        }
    }
}