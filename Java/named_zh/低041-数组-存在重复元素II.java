/**
�����ظ�Ԫ�� II

����һ�����������һ������ k���ж��������Ƿ����������ͬ������ i �� j��ʹ�� nums [i] = nums [j]������ i �� j �Ĳ�ľ���ֵ���Ϊ k��

ʾ�� 1:

����: nums = [1,2,3,1], k = 3
���: true
ʾ�� 2:

����: nums = [1,0,1,1], k = 1
���: true
ʾ�� 3:

����: nums = [1,2,3,1,2,3], k = 2
���: false

�Ƽ�����������죬ʹ��JDK����
**/
class Solution {
	//����һ���ж�Ϊk�ļ����û����ȵģ�û��k�ͼ�С���ٳ����鳤��Ϊ0��k�������ȱ�С�����
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        int i=0;
        if(nums.length==1){
            return false;
        }else if(k>=nums.length){
            k=nums.length-1;
        }
        while(k>0){
            if(nums[i]==nums[i+k]){
                return true;
            }
            i++;
            if(i+k>=nums.length){
                k--;
                i=0;
            }
        }
        return false;
    }
	//��������˫ָ��ԭ���뷽��һͬ
	public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(nums.length==1 ){
            return false;
        }
        int i=0;
        int j;
        while(i<nums.length-1){
            j=i+1;
            while(j<nums.length&&j-i<=k){
            if(nums[i]==nums[j]){
                return true;
            }
                j++;
            }
             i++;
        }
        return false;
    }
	//������
	 public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if (nums.length <= 1) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);//��ָ�����kʱ�����������Ƴ�������Χ����
            if (set.contains(nums[i])) return true;//�ж�ȥ��
            set.add(nums[i]);
        }
        return false;
    }
}