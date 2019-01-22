/**
�����������������

����һ���������飬����ҪѰ��һ�������������飬���������������������������ô�������鶼���Ϊ��������

���ҵ���������Ӧ����̵ģ���������ĳ��ȡ�

ʾ�� 1:

����: [2, 6, 4, 8, 10, 9, 15]
���: 5
����: ��ֻ��Ҫ�� [6, 4, 8, 10, 9] ��������������ô���������Ϊ��������
˵�� :

��������鳤�ȷ�Χ�� [1, 10,000]��
�����������ܰ����ظ�Ԫ�� �������������˼��<=��

**/
class Solution {
    //˫ָ��ָ��ͷβ������һ����������������Ƚϣ����ɵõ���С
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length==1) return 0;
        int[] temp=Arrays.copyOf(nums,nums.length);
        int i=0,j=nums.length-1;
        Arrays.sort(temp);
        int flag=1;
        while(i<j){
            flag=1;
            if(nums[i]==temp[i]){//��ȣ� i ++
                i++;flag=0;
            }
            if(nums[j]==temp[j]){//��ȣ� j--
                j--;flag=0;
            }
            if(flag==1) break;//������ȣ� ��break
        }
        if(i>=j) return 0;//ָ�����м����û�з���0
        return j-i+1;//����Ϊĩ��ͷ��1
    }
	/**��һ�ֱȽϿ죬���ǿ��ǵ����̫�࣬��Ҫ������ͷ������β�ݼ��Ĺյ㣬ֻ������һ�α���
	int findUnsortedSubarray(int* nums, int numsSize) {
    if (numsSize < 2) {
        return 0;
    }
    // �ֱ��ҵ������յ� start�ǿ�ʼ���� ͻȻ��ʼ�½��ĵط�
    // end ���� �����ݼ� ͻȻ�����ĵط�
    int start = 0;
    int end = numsSize-1;
    for (int i = 1; i < numsSize; i++) {
        if (nums[i] >= nums[i-1]) {
            start++;
        } else {
            break;
        }
    }
    if (start == numsSize - 1) {
        // �Ѿ�������������
        return 0;
    }
    
    for (int i = numsSize-2; i >= 0; i--) {
        if (nums[i] <= nums[i+1]) {
            end--;
        } else {
            break;
        }
    }
    
    if (start >= end) {
        // �������
        return 0;
    }
    
    // �ҳ���start �� end֮������ֵ����Сֵ
    int max = nums[start];
    int min = nums[start];
    for (int i = start + 1; i <= end; i++) {
        if (nums[i] > max) {
            max = nums[i];
        }
        if (nums[i] < min) {
            min = nums[i];
        }
    }
    
    // �ҵ�֮�󣬷ֱ��ǰ���������
    int starti = 0;
    for (int i = 0; i <= start; ) {
        int j = i;
        while (++j <= start && nums[j] == nums[i]) {
        }
        if (nums[i] > min) {
            // ������ֲ����� ��Ҫ����
            starti = i;
            break;
        } else {
            i = j;
        }
    }
    
    int endi = numsSize - 1;
    for (int i = numsSize - 1; i >= end; ) {
        // ���˵���ȵ�����
        int j = i;
        while (--j >= end && nums[j] == nums[i]) {
        }
        if (nums[i] < max) {
            // ���ֵ������ ��Ҫ����
            endi = i;
            break;
        } else {
            i = j;
        }
    }
    
    return endi - starti + 1;
	**/
}