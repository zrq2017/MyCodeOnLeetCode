/**
单调数列

如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。

当给定的数组 A 是单调数组时返回 true，否则返回 false。

 

示例 1：

输入：[1,2,2,3]
输出：true
示例 2：

输入：[6,5,4,4]
输出：true
示例 3：

输入：[1,3,2]
输出：false
示例 4：

输入：[1,2,4,5]
输出：true
示例 5：

输入：[1,1,1]
输出：true
 

提示：

1 <= A.length <= 50000
-100000 <= A[i] <= 100000
**/
class Solution {
	//方法一：先判断首尾元素，再逐个比较相邻元素
	public boolean isMonotonic(int[] A) {
        if (A.length <= 2) return true;
        if (A[0] > A[A.length-1]) {
            for (int i = 1; i < A.length; i++) {
                if (A[i] > A[i-1]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < A.length; i++) {
                if (A[i] < A[i-1]) {
                    return false;
                }
            }
        }
        return true;
    }
	//方法二：先由首尾得结果a，再由相邻结果的差与a相乘判断
    public boolean isMonotonic(int[] A) {
        if (A.length <= 2) {//长度小于2一定单调
            return true;
        }
        int a = A[0] - A[A.length - 1];//判断最后一个数据和第一个数据的大小
        if (a == 0) {//首尾相等则所有相等才单调
            for (int i = 1; i < A.length; i++) {
                if (A[i] != A[0]) {
                    return false;
                }
            }
        } else {//首尾不等时，相邻数据差与首尾差乘积为负则不单调
            for (int i = 0; i < A.length - 1; i++) {
                if (a * (A[i] - A[i + 1]) < 0) {//单调递增a>0相乘>0，单调递减a<0相乘>0
                    return false;
                }
            }
        }
        return true;
    }
}