/**
斐波那契数
斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
给定 N，计算 F(N)。

 

示例 1：

输入：2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
示例 2：

输入：3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
示例 3：

输入：4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 

提示：

0 ≤ N ≤ 30

推荐方法二与三：动态规划，三更简练
**/
class Solution {
	//方法一：直接上递归
    public int fib1(int N) {
        if(N==0){
            return 0;
        }else if(N==1){
            return 1;
        }
        return fib(N-1)+fib(N-2);
    }
	//方法二：动态规划
	public int fib2(int N) {
        if(N<=0) return N;
        int[] m=new int[N+1];
        m[0]=0;
        m[1]=1;
        for(int i=2;i<=N;i++){
            m[i]=m[i-1]+m[i-2];
        }
        return m[N];
    }
	//方法三：动态规划（简练）
	public int fib3(int N) {
        int a = 0, b = 1, temp;
        for (int i = 2; i <= N; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return N == 0 ? a : b;
    }
}