/**
��Ч��ɽ������

����һ���������� A�����������Ч��ɽ������ͷ��� true�����򷵻� false��

�����ǻع�һ�£���� A ����������������ô����һ��ɽ�����飺

A.length >= 3
�� 0 < i < A.length - 1 �����£����� i ʹ�ã�
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[B.length - 1]
 

ʾ�� 1��

���룺[2,1]
�����false
ʾ�� 2��

���룺[3,5,5]
�����false
ʾ�� 3��

���룺[0,3,2,1]
�����true
 

��ʾ��

0 <= A.length <= 10000
0 <= A[i] <= 10000 

**/
class Solution {
    public boolean validMountainArray(int[] A) {
        if(A.length<3||A[0]>=A[1]){//���жϳ��Ȳ�����뿪ʼ���ǵݼ��ģ�ɽ������ȵ���
            return false;
        }
        int i=0;//
        for(;i<A.length-1&&A[i]<A[i+1];i++){}//ѭ���жϵõ���������
        if(i==A.length-1) return false;
        for(;i<A.length-1&&A[i]>A[i+1];i++){}//ѭ���жϲ��ݼ���
        if(i!=A.length-1){//��iС�����鳤�ȴ���ݼ��Ľ׶β�����
            return false;
        }
        return true;
    }
}