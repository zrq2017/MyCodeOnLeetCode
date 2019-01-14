/**
�ϴ�����λ��

��һ����Сд��ĸ���ɵ��ַ��� S �У�������һЩ��������ͬ�ַ������ɵķ��顣

���磬���ַ��� S = "abbxxxxzyy" �У��ͺ��� "a", "bb", "xxxx", "z" �� "yy" ������һЩ���顣

���ǳ����а������ڻ�������������ַ��ķ���Ϊ�ϴ���顣�ҵ�ÿһ���ϴ�������ʼ����ֹλ�á�

���ս�������ֵ�˳�������

ʾ�� 1:

����: "abbxxxxzzy"
���: [[3,6]]
����: "xxxx" ��һ����ʼ�� 3 ����ֹ�� 6 �Ľϴ���顣
ʾ�� 2:

����: "abc"
���: []
����: "a","b" �� "c" �����Ƿ���Ҫ��Ľϴ���顣
ʾ�� 3:

����: "abcdddeeeeaabbbcd"
���: [[3,5],[6,9],[12,14]]
˵��:  1 <= S.length <= 1000

**/
class Solution {
	//�����������ַ�������ѭ�����ڲ����ö���ͬ�ַ�����ѭ�����жϣ�
	//��Ҫע���һ���ǣ����ڲ�����ѭ��ʱ����ֵ�պ�Ϊ����һ������ͬ���ַ����±ꡱ
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (S.length() < 3)
            return list;
        for (int i = 0; i < S.length();) {
            int j = i;//˫ָ��������
            while (j < S.length() && S.charAt(j) == S.charAt(i))
                j++;
            if (3 <= j - i) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                temp.add(j - 1);
                list.add(temp);
            }
            i = j;
        }
        return list;
    }
}