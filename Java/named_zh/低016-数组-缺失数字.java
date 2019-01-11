/**
缺失数字

给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

示例 1:

输入: [3,0,1]
输出: 2
示例 2:

输入: [9,6,4,2,3,5,7,0,1]
输出: 8

解决思路：桶排序最简单，但这题不推荐。用前N项的等差求和公式减去所有元素之和，就可以得到没出现的数2ms；第三种异或思想解决：1ms

推荐第三个
**/
class Solution {
	//方法一：等差求和相减的思想
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for(int i=0;i<=len;i++){
            if(i == len){
                sum += i;
            }else{
                sum+= i - nums[i];//每一个对应位相减得到最后的求和结果即等差求和思想
            }
        }
        return sum;
    }
	//方法二：等差求和相减的思想
	public int missingNumber2(int[] nums) {
        int t = 0;
        for (int i = 0; i < nums.length; i++) {
            t += i + 1;
            t -= nums[i];
        }
        return t;
    }
	/**
	*方法三：异或思想
	*0-n之间异或后，相同的数字变为0，剩下的就是缺的那个数
	**/
	public int missingNumber3(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= (i + 1) ^ nums[i];
        }
        return res;
    }
}