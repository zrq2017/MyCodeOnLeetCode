/*****
买卖股票的最佳时机

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
**/
class Solution {
	public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int min=prices[0];//当前买入的最小的价格
        int max=0;//当前的最大收益
        for(int i=1;i<prices.length;i++){
            if(min>prices[i]){
                min=prices[i];//当前不是最小买入价格，替换；
            }else{
                max=Math.max(max,prices[i]-min);//上面的min保证了目前为止的最大利润，取更小min只会使得利润更大
            }
        }
        return max;
    }
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int temp=0;//保存的总的收益，一定大于0
        int max=0;//到目前为止最大的收益
        for(int i=1;i<prices.length;i++){
            temp=(prices[i]+temp-prices[i-1])>0?(prices[i]+temp-prices[i-1]):0;//保存当前的总收益：每天加之前的收益只要有收益就保存，不然就是一开始不买入的状态0
            max=max>temp?max:temp;//目前的总收益是否大于最大收益
        }
        return max;
    }
}