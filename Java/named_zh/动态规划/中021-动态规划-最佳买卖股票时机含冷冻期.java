/*****
最佳买卖股票时机含冷冻期

给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
**/
class Solution {
    /**
    * 此题展现出来的维度有三个。思路是先降维，将买、卖、冷冻，降维成两个维度：持有股票和未持有股票。
    * 持有股票：今天买入和之前买入但未卖出 未持有：今天卖出和冷冻期 
    * 所以有传递式： hold[i]=max(hold[i-1],notHold[i-2]-prices[i]); 
    * 意思是当前持有的股票的最大收益是昨天持有的股票（可能今天并未有任何操作）和之前卖出的最大收益-今天买入的(i-2的意思是今天买入的话，昨天就应该是冷冻期)。
    * notHold[i]=max(notHold[i-1],hold[i-1]+prices[i]);最大收益的就很容易理解。
    **/
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(n==0)return 0;
        int[] hold=new int[n];
        hold[0]=-prices[0];
        int[] notHold=new int[n];
        for(int i=1;i<n;i++){
            if(i>=2)hold[i]=Math.max(hold[i-1],notHold[i-2]-prices[i]);
            else hold[i]=Math.max(hold[i-1],-prices[i]);
            notHold[i]=Math.max(notHold[i-1],hold[i-1]+prices[i]);
        }
        return notHold[n-1];
    }
}