/**
���������

����һ���ǿ����飬���ش������е������������������ڣ��򷵻���������������Ҫ���㷨ʱ�临�Ӷȱ�����O(n)��

ʾ�� 1:

����: [3, 2, 1]

���: 1

����: ����������� 1.
ʾ�� 2:

����: [1, 2]

���: 2

����: ���������������, ���Է��������� 2 .
ʾ�� 3:

����: [2, 2, 3, 1]

���: 1

����: ע�⣬Ҫ�󷵻ص������������ָ��������Ψһ���ֵ�����
��������ֵΪ2���������Ƕ��ŵڶ���

**/
class Solution {
    public int thirdMax(int[] nums) {
        if(nums.length<3){
            Arrays.sort(nums);
            return nums[nums.length-1];
        }
        boolean f=true;//��f��ʾ��һ��������С������ͬ
        int tag=0;//��tag��ʾ��û����������С���е���
        int fir=Integer.MIN_VALUE,sec=Integer.MIN_VALUE,thi=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==Integer.MIN_VALUE&&f){
                tag++;
                f=false;
            }
            if(nums[i]>fir){
                tag++;
                thi=sec;
                sec=fir;
                fir=nums[i];
            }else if(nums[i]>sec&&nums[i]<fir){
                tag++;
                thi=sec;
                sec=nums[i];
            }else if(nums[i]>thi&&nums[i]<sec){
                thi=nums[i];
                tag++;
            }
        }
        return tag>=3?thi:fir;
    }
}