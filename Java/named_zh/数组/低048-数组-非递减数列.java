/**
�ǵݼ�����

����һ������Ϊ n ���������飬����������ж������ı� 1 ��Ԫ�ص�����£��������ܷ���һ���ǵݼ����С�

��������������һ���ǵݼ����еģ� �������������е� i (1 <= i < n)������ array[i] <= array[i + 1]��

ʾ�� 1:

����: [4,2,3]
���: True
����: �����ͨ���ѵ�һ��4���1��ʹ������Ϊһ���ǵݼ����С�
ʾ�� 2:

����: [4,2,1]
���: False
����: �㲻����ֻ�ı�һ��Ԫ�ص�����½����Ϊ�ǵݼ����С�

**/
class Solution {
	/**
	* ���ȿ����� nums[i - 1] = nums[i]��
	* ��Ϊ����޸� nums[i] = nums[i - 1] �Ļ�����ô nums[i] ��������󣬾��п��ܱ� nums[i + 1] �󣬴Ӷ�Ӱ���˺���������
	* ����һ���Ƚ��ر��������� nums[i] < nums[i - 2]���޸� nums[i - 1] = nums[i] ����ʹ�����Ϊ�ǵݼ����飬ֻ���޸� nums[i] = nums[i - 1]��
	*/
	public boolean checkPossibility(int[] nums) {
        int cnt=0;
        for(int i=1;i<nums.length&&cnt<2;i++){
            if(nums[i]>=nums[i-1]){
                continue;
            }
            cnt++;
            //��ǰ�Ĳ�����Ҫô�ĵ�ǰ�Լ�Ҫô��ǰһ��
            if(i-2>=0&&nums[i-2]>nums[i]){//��ǰ�������������Ҫ�ж���ô�޸�����ʣ���ǰ���Ѿ���ǰһ��С���ǻ���ǰ����С
                nums[i]=nums[i-1];//�Ǿ�˵����ǰ���Ӧ���޸�Ϊǰһ���Ĵ�С
            }else{//����˵��ǰһ��̫����Ӧ�øĳɾ���С��
                nums[i-1]=nums[i];
            }
        }
        return cnt<=1;
    }
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {//���Ǻ���һ������ǰ��һ������������
                count++;
                if (count > 1) {
                    return false;
                }
                int privous = i > 1 ? nums[i - 2] : 0;//�ж�ǰһ�����±�Ϊ1ǰһ���ǵ�һ��Ϊ0
                int after = i < nums.length - 1 ? nums[i + 1] : 10000;//�ж�i�ǲ���Խ�磬����Ϊ���һ��
                if (nums[i - 1] > after && nums[i] < privous) {//�жϵ�ǰ��ǰһ���ǲ��Ǳȵ�ǰ��һ����ͬʱ��ǰ���С��ǰ��һ��
                    //�������±�0Ԫ�ر�2��1��0С
                    return false;
                }
            }
        }
        return true;
    }
}