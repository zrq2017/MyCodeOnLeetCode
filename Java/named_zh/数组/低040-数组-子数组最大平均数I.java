/**
���������ƽ���� I

���� n ���������ҳ�ƽ��������ҳ���Ϊ k �����������飬����������ƽ������

ʾ�� 1:

����: [1,12,-5,-6,50,3], k = 4
���: 12.75
����: ���ƽ���� (12-5-6+50)/4 = 51/4 = 12.75
 

ע��:

1 <= k <= n <= 30,000��
�������ݷ�Χ [-10,000��10,000]��

**/
class Solution {
	//����һ������һ��ѭ�����ۼӣ�����
    public double findMaxAverage1(int[] nums, int k) {
        double avg=0;
        for(int i=0;i<=nums.length-k;i++){
            int temp=0;
            for(int j=0;j<k;j++){
                temp+=nums[i+j];
            }
            if(i==0){
                avg=temp*1.0/k;
            }else{
                avg=Math.max(temp*1.0/k,avg);
            }
        }
        return avg;
    }
	//����һ������һ��ѭ�����ۼӣ�����
	public double findMaxAverage1(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++)
                sum += nums[j];
            max = Math.max(max, sum);
        }
        return max * 1.0 / k;
    }
}