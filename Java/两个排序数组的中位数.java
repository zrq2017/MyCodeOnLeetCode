/**
*问题描述：
*两个排序的数组A和B分别含有m和n个数，找到两个排序数组的中位数，
*要求时间复杂度应为O(log (m+n))。
* 样例
*给出数组A = [1,2,3,4,5,6] B = [2,3,4,5]，中位数3.5
*给出数组A = [1,2,3] B = [4,5]，中位数 3
*解析：
*代码采用递归思想实现，递归函数中判断传入两个数组是否为空，空则直接返回另一数组
*的中位数（因为这个空了，另一个的中位数当然是整个的中位数了。。）
*递归函数传入的k为两个数组长度之和的中间值下标
*若恰好两个数组长度的中值为1，那么说明仅有三个数比较，直接获取传入的的两个数组
*的初值并返回其中最小的即可（这步没看出来）
*
**/
class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        

        int len = A.length + B.length;

        if (len % 2 == 1) {

            return findKth(A, 0, B, 0, len / 2 + 1);

        }

        return (

            findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)

        ) / 2.0;

    }



    // find kth number of two sorted array

    public static int findKth(int[] A, int A_start,

                              int[] B, int B_start,

                              int k){		

		if (A_start >= A.length) {

			return B[B_start + k - 1];

		}

		if (B_start >= B.length) {

			return A[A_start + k - 1];

		}



		if (k == 1) {

			return Math.min(A[A_start], B[B_start]);

		}

		

		int A_key = A_start + k / 2 - 1 < A.length

		            ? A[A_start + k / 2 - 1]

		            : Integer.MAX_VALUE;

		int B_key = B_start + k / 2 - 1 < B.length

		            ? B[B_start + k / 2 - 1]

		            : Integer.MAX_VALUE; 

		

		if (A_key < B_key) {

			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);

		} else {

			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);

		}

	}
}
