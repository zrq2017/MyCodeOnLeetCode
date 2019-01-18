/**
数组中的K-diff数对

给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.

示例 1:

输入: [3, 1, 4, 1, 5], k = 2
输出: 2
解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
尽管数组中有两个1，但我们只应返回不同的数对的数量。
示例 2:

输入:[1, 2, 3, 4, 5], k = 1
输出: 4
解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
示例 3:

输入: [1, 3, 1, 5, 4], k = 0
输出: 1
解释: 数组中只有一个 0-diff 数对，(1, 1)。
注意:

数对 (i, j) 和数对 (j, i) 被算作同一数对。
数组的长度不超过10,000。
所有输入的整数的范围在 [-1e7, 1e7]。

推荐第二种，因为判断情况少,第一种会快点
**/
class Solution {
	//方法一：先排序，然后双指针判断，res计数，能先想到的是第一种
    public int findPairs(int[] nums, int k) {
        int count = 0, n = nums.length, j = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; ++i) {
            j = Math.max(j, i + 1);
            while (j < n && nums[j] - nums[i] < k) j++;
            if (j < n && nums[j] - nums[i] == k) count;
            while (i < n - 1 && nums[i] == nums[i + 1]) i++;
        }
        return count;
    }
    /**
    利用HashMap把数和下标索引分别作为key和value存起来，然后找值是数组中元素+k的key有没有，如果有就把计数值加1，并且由于不能使用重复的元素，需要把这个key给移除掉。判断条件里面

hm.get(nums[i]+k) != i

是指的不能是自己本身，当k是0的时候,这是个测试用例里给出的特殊情况。
    **/
    public int findPairs(int[] nums, int k) {
        int count = 0;
        if(k < 0)
            return count;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            hm.put(nums[i],i);
        }
        for(int i = 0; i < nums.length; i++){
            if(hm.containsKey(nums[i] + k) && hm.get(nums[i]+k) != i){
                count++;
                hm.remove(nums[i] + k);
            }
        }
        return count;
    }
}