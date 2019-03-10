/*****
最长数对链

给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。

现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。

给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。

示例 :

输入: [[1,2], [2,3], [3,4]]
输出: 2
解释: 最长的数对链是 [1,2] -> [3,4]
注意：

给出数对的个数在 [1, 1000] 范围内。


解析：
**/
class Solution {
	/**
	* 方法一：采用贪心算法，先对pairs的第二位由小到大排序，然后遍历pairs，
	* 对于遍历到的元素k，即pairs[k]，如果满足pairs[k][0]>pre（pre是int变量，存储上一次遍历的数组的第二位即pairs[k-1][1]），
	* 那么就把cnt+1，并且pre更新为pairs[k][1]
	**/
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] == o2[1] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));
        int cnt = 0;
        int pre = Integer.MIN_VALUE;
        String test = "";
        for (int[] pair : pairs) {
            if (pair[0] > pre) {//第i个的第二个比前一个的第一个大或是第i个的第一个比前一个的第2个小
                pre = pair[1];
                cnt++;
            }
        }
        return cnt;
    }
	
	/**
	* 方法二：采用动态规划，维护一个一维数组dp，dp[i]的意思是如果以第i位结尾的最大的数组对，
	* 那么我们需要两层循环，
	* 外循环遍历pairs数组对，内循环即遍历在外循环变量i之前的dp[j]的值，看是否满足条件
	**/
	public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0)
            return 0;
        int rows = pairs.length;
        int cols = pairs[0].length;
        Arrays.sort(pairs, new Comparator<int[]>(){//排序数对
            public int compare(int[] a,int[] b){
                 return a[0] - b[0];
            }
         });       
        int[] dp = new int[rows];
        dp[0] = 1;
        for(int i=1;i<rows;i++){//外层判断当前i
            for(int j=0;j<i;j++){//内层判断前i-1个与i的大小
                if(pairs[i][0] > pairs[j][1])//dp保存的是前面i个最大的，第i个与前i个比较满足插入，不满足则dp[i]=dp[i-1]
                    dp[i] = Math.max(dp[i],dp[j]+1);
                else
                    dp[i] = Math.max(dp[i],dp[j]);
            }
        }
        return dp[rows-1];
    }
}
