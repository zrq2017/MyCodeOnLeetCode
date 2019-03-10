/*****
完全平方数

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.


解析：
**/
class Solution {
    //动态规划-状态转移方程 dp[i] = Math.min(dp[i],dp[i-j*j]+1)
    public int numSquares(int n) {
        if(n<=2) return n;
        int[] dp = new int[n + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);//初始化每一个都是最大值保证遍历时初始状态不出错
        dp[0]=0;dp[1]=1;dp[2]=2;
        for (int i = 2; i <= n; i++) {
            for(int j=1;j*j<=i;j++){
                // j + (i-j)，用循环保证从0开始到现在i这一位的结果最小
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);//比较当前位置从后往前每一轮后取更小
            }
        }
        return dp[n];
    }
}
