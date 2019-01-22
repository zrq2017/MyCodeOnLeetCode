/**
找到所有数组中消失的数字

给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]
**/
class Solution {
	//先桶排序，在找出桶中为空的数
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {//把number都放在对应的位置
            if (nums[i] != i + 1) {
                int temp = nums[i];
                if (temp == nums[temp - 1]) {
                    continue;
                }
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
                i--;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {//遍历，找出消失数字
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}