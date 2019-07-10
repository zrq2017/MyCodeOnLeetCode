package named_zh;
/**
*问题描述：
*给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
* 样例：
*给出 [1,2,2,1,3,4,3]，返回 4
*解析：
*由题知，所有数字转换成二进制，相同数二进制值相同，落单的数二进制值独一无二
*则可知所有数异或后，根据同0，异1原则，仅剩下落单的数解法可得如下
**/
public class Solution {

    /**
      *@param A : an integer array
      *return : a integer 
      */
    public int singleNumber(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
           return 0;
        }
        int rst = A[0];
        for (int i = 1; i < A.length; i++) {
            rst = rst ^ A[i];
        }
        return rst;
    }
}