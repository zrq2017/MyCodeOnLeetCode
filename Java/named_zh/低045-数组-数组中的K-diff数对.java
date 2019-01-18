/**
�����е�K-diff����

����һ�����������һ������ k, ����Ҫ���������ҵ���ͬ�� k-diff ���ԡ����ｫ k-diff ���Զ���Ϊһ�������� (i, j), ���� i �� j ���������е����֣�������֮��ľ���ֵ�� k.

ʾ�� 1:

����: [3, 1, 4, 1, 5], k = 2
���: 2
����: ������������ 2-diff ����, (1, 3) �� (3, 5)��
����������������1��������ֻӦ���ز�ͬ�����Ե�������
ʾ�� 2:

����:[1, 2, 3, 4, 5], k = 1
���: 4
����: ���������ĸ� 1-diff ����, (1, 2), (2, 3), (3, 4) �� (4, 5)��
ʾ�� 3:

����: [1, 3, 1, 5, 4], k = 0
���: 1
����: ������ֻ��һ�� 0-diff ���ԣ�(1, 1)��
ע��:

���� (i, j) ������ (j, i) ������ͬһ���ԡ�
����ĳ��Ȳ�����10,000��
��������������ķ�Χ�� [-1e7, 1e7]��

�Ƽ��ڶ��֣���Ϊ�ж������,��һ�ֻ���
**/
class Solution {
	//����һ��������Ȼ��˫ָ���жϣ�res�����������뵽���ǵ�һ��
    public int findPairs(int[] nums, int k) {
        int count = 0, n = nums.length, j = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; ++i) {
            j = Math.max(j, i + 1);
            while (j < n && nums[j] - nums[i] < k) j++;
            if (j < n && nums[j] - nums[i] == k) count;
            while (i < n - 1 && nums[i] == nums[i + 1]) i++;
        }
        return count;
    }
    /**
    ����HashMap�������±������ֱ���Ϊkey��value��������Ȼ����ֵ��������Ԫ��+k��key��û�У�����оͰѼ���ֵ��1���������ڲ���ʹ���ظ���Ԫ�أ���Ҫ�����key���Ƴ������ж���������

hm.get(nums[i]+k) != i

��ָ�Ĳ������Լ�������k��0��ʱ��,���Ǹ�������������������������
    **/
    public int findPairs(int[] nums, int k) {
        int count = 0;
        if(k < 0)
            return count;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            hm.put(nums[i],i);
        }
        for(int i = 0; i < nums.length; i++){
            if(hm.containsKey(nums[i] + k) && hm.get(nums[i]+k) != i){
                count++;
                hm.remove(nums[i] + k);
            }
        }
        return count;
    }
}