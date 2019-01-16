/**
有效的山脉数组

给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

A.length >= 3
在 0 < i < A.length - 1 条件下，存在 i 使得：
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[B.length - 1]
 

示例 1：

输入：[2,1]
输出：false
示例 2：

输入：[3,5,5]
输出：false
示例 3：

输入：[0,3,2,1]
输出：true
 

提示：

0 <= A.length <= 10000
0 <= A[i] <= 10000 

**/
class Solution {
    public boolean validMountainArray(int[] A) {
        if(A.length<3||A[0]>=A[1]){//先判断长度不足的与开始就是递减的，山峰必须先递增
            return false;
        }
        int i=0;//
        for(;i<A.length-1&&A[i]<A[i+1];i++){}//循环判断得到不递增的
        if(i==A.length-1) return false;
        for(;i<A.length-1&&A[i]>A[i+1];i++){}//循环判断不递减的
        if(i!=A.length-1){//当i小于数组长度代表递减的阶段不满足
            return false;
        }
        return true;
    }
}