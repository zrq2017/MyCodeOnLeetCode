/*****
按奇偶排序数组
给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。

你可以返回满足此条件的任何数组作为答案。

 

示例：

输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。

推荐解决方法二
********/
class Solution {
	/**
	方法一：不需要思考复杂，直接新建同样空间的数组，按一次扫描的原理左右填充数组元素
	**/
	public int[] sortArrayByParity1(int[] A) {
        int[] result=new int[A.length];
        int left=0;//左边开始索引
        int right=A.length-1;//右边开始索引
        for(int i=0;i<A.length;i++){
            int a=A[i];
            if(a%2==0){//偶数
                result[left]=a;
                left++;
            }else{//奇数
                result[right]=a;
                right--;
            }
        }
        return result;
    }
	/**
	方法二：相当于复杂度为O(n)，只需要额外空间1，两个索引
	左边开始扫描偶数一直加，直到不是偶数，记录位置
	右边开始扫描奇数一直减，直到不是奇数，记录位置
	将两个刚好相反的做交换，重复开始下一次扫描
	**/
    public int[] sortArrayByParity2(int[] A) {
        int p1 = 0, p2 = A.length - 1;
        while (p1 < p2) {
            while (p1 < p2 && (A[p1] & 1) == 0) {//p1是偶数
                p1 = p1 + 1;
            }
            while (p1 < p2 && (A[p2] & 1) != 0) {//p2是奇数
                p2 = p2 - 1;
            }
            int tmp = A[p1];//p1与p2交换
            A[p1] = A[p2];
            A[p2] = tmp;
        }
        int tmp = A[p1];//到中间位置时候的交换，刚好p1等于p2
        A[p1] = A[p2];
        A[p2] = tmp;
        return A;
    }
}