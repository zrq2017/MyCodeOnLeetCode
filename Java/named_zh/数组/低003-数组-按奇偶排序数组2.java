/**
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。

 

示例：

输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 

提示：

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000

推荐方法二
**/

class Solution {
	/**
	方法一：生成同样大小数组，用两个指针表示奇偶，当前循环元素为奇数，奇指针增加2否则为偶指针
	**/
    public int[] sortArrayByParityII1(int[] A) {
        int[] a=new int[A.length];
        int x=0,y=1;
        for(int i=0;i<A.length;i++){
            if(A[i]%2==0){
                a[x]=A[i];
                x+=2;
            }else{
                a[y]=A[i];
                y+=2;
            }
        }
        return a;
    }
	
	/**
	方法二：一次遍历奇偶排序完毕
	**/
	public int[] sortArrayByParityII2(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length - 1; i = i + 2) {
            if ((A[i] & 1) != 0) {//判断为奇数则进入
                while ((A[j] & 1) != 0) {//判断奇数指针元素是否为奇数，是的话奇数指针增加
                    j = j + 2;//由于i，j指针判断均为奇数进入循环，表明有错位，不会发生j指针越界情况，因为j越界表示元素肯定按奇偶排列完毕
                }
                //得到奇数指针中的元素为偶数，交换位置
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }
}