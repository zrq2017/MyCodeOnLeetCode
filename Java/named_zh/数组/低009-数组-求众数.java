/**
求众数

给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2

推荐方法二
**/
class Solution {
	//方法一：用map键值做判断，值大于n/2数组长度的元素返回就是众数
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int temp=map.get(nums[i])!=null?map.get(nums[i]):0;
            map.put(nums[i],++temp);
            if(temp>nums.length/2){
                return nums[i];
            }
        }
        return 0;
    }
	//方法二：摩尔投票法：从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1，减到0就重新换个数开始计数，总能找到最多的那个
	 public int majorityElement(int[] nums) {
        int count = 1;
		int maj = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (maj == nums[i])
				count++;
			else {
				count--;
				if (count == 0) {
					maj = nums[i + 1];
				}
			}
		}
		return maj;
    }
}