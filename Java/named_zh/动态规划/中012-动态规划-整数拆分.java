/*****
整数拆分

给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。

**/
class Solution {//递归求子项最大
    public int integerBreak(int n) {
        //你可以假设 n 不小于 2 且不大于 58。
        //主要是找规律
    	// 1  2  3  4  5  6  7  8  9  10
    	// 1  1  2  4  6  9  12 16 27 36
    	int[] DP = new int[n+1];
    	DP[0] = 0;
    	DP[1] = 1;
    	for(int i=2;i<=n;i++){
    		DP[i] = 0;
    		for(int j=1;j<i;j++){
    			DP[i] = Math.max(DP[i],Math.max(j,DP[j])*Math.max(i-j, DP[i-j]));
    		}
    	}
    	return DP[n];
    }
}