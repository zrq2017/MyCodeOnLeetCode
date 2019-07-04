/**
��������

����һ���������� nums ���ҵ�һ���������͵����������飨���������ٰ���һ��Ԫ�أ������������͡�

ʾ��:

����: [-2,1,-3,4,-1,2,1,-5,4],
���: 6
����: ���������� [4,-1,2,1] �ĺ����Ϊ 6��
����:

������Ѿ�ʵ�ָ��Ӷ�Ϊ O(n) �Ľⷨ������ʹ�ø�Ϊ����ķ��η����
**/
class Solution {
	//�ڶ����ľ���汾
	public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int presum=nums[0],max=presum;
        for(int i=1;i<nums.length;i++){
            presum=presum>0?presum+nums[i]:nums[i];//֮ǰҪ����0�Ż�ʹ�������
            max=Math.max(max,presum);
        }
        return max;
    }
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // һ��һ�ε������еĺ�
        int sum = 0;
        int res = nums[0];//�൱�ڴ浽ĿǰΪֹ����ֵ
        // ��̬�滮����
        for (int num : nums) {
            if (sum > 0) {
                // ǰ���ۼӵ����� > 0 �Ǿͼ������Լ��ٿ���
                sum +=num;
            } else {
                // ǰ���ۼӵ����� < 0  ǰ��������ۼӵ��������׸��ˣ��Ǿʹ���һ�ο�ʼ������İ�
                sum = num;
            }
            // sum�͵��ڴ�ǰ��ĳһ�쵽�������������һ��Ҳ�������ж�sum��res�Ĵ�С
            res = Math.max(res, sum);
        }
        return res;
    }
}