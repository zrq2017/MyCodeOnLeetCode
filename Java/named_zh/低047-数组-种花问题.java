/**
�ֻ�����
��������һ���ܳ��Ļ�̳��һ���ֵؿ���ֲ�˻�����һ����ȴû�С����ǣ����ܲ�����ֲ�����ڵĵؿ��ϣ����ǻ�����ˮԴ�����߶�����ȥ��

����һ����̳����ʾΪһ���������0��1������0��ʾû��ֲ����1��ʾ��ֲ�˻�������һ���� n ���ܷ��ڲ�������ֲ�������������� n �仨�����򷵻�True�������򷵻�False��

ʾ�� 1:

����: flowerbed = [1,0,0,0,1], n = 1
���: True
ʾ�� 2:

����: flowerbed = [1,0,0,0,1], n = 2
���: False
ע��:

���������ֺõĻ�����Υ����ֲ����
��������鳤�ȷ�ΧΪ [1, 20000]��
n �ǷǸ��������Ҳ��ᳬ����������Ĵ�С��

**/
class Solution {
    //�жϿ�ͷ��β�ǲ�������Ϊ0���Ǽ�����һ���ж��м��ǲ�����������0�Ǿͼ�1��ÿ�μ�һ��Ҫ��������λ
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
		for (int i = 0; i < flowerbed.length; i++) {
			if (i == 0) {
				if (flowerbed.length == 1) {//��̳����Ϊ1��Ϊ��ʱ���ֵļ���++
					if (flowerbed[i] == 0) {
						count++;
					}
				} else {
					if (flowerbed[i + 1] == 0 && flowerbed[i] == 0) {//��ͷʱ����������Ϊ0������1��
						flowerbed[i] = 1;
						count++;
					}
				}
			} else if (i > 0 && i < flowerbed.length - 1) {//i��Ϊ1������Ҳ�Ϊ���һ�������
				if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
					count++;//��������0�ſ�����һ��
					flowerbed[i] = 1;

				}

			} else {//�ж���������ǲ�������0
				if (flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
					count++;
					flowerbed[flowerbed.length - 1] = 1;
				}
			}
		}
		if (count >= n) {//��������
			return true;

		} else {
			return false;
        }
    }
}