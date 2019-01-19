Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:

Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 

Note:

Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
 

������������һЩ���飬ÿ�����鶼������ģ������ǴӲ�ͬ�������и�ȡһ�����֣�ʹ�����������ֵĲ�ľ���ֵ�����������������ֵ����ô�����룬��Ȼ���鶼������ģ���ô��ľ���ֵ�����������ֿ϶��Ƿֱ�λ��������׺�β��ע����Ŀ��˵Ҫ�Ӳ�ͬ��������ȡ������ô��ʹĳ���������β���ܴ�Ҳ���С��������ȿ��ǵ����ö�������һ�����ѣ�һ����С�ѣ����Ѵ�ÿ�������βԪ�أ���С�Ѵ�ÿ���������Ԫ�أ������������ֺ���С�������п���������ͬһ�����飬���������ڶ��д����ֵ�ʱ��Ҫ���뵱ǰ�������ڵ��������ţ����������ʵҪ�ֱ������Ѻ���С���и�ȡ�������֣�����������ֺ���С�����ֲ���һ�����飬��ôֱ�ӷ��ض��ߵľ��Բ�ɣ�����ڵĻ�����ôҪ���صڶ������ֺ���С���־��Բ��������ֺ͵ڶ�С���־��Բ��еĽϴ�ֵ���μ��������£�

 

�ⷨһ��

���ƴ���
class Solution {
public:
    int maxDistance(vector<vector<int>>& arrays) {
        priority_queue<pair<int, int>> mx, mn;
        for (int i = 0; i < arrays.size(); ++i) {
            mn.push({-arrays[i][0], i});
            mx.push({arrays[i].back(), i});
        }
        auto a1 = mx.top(); mx.pop();
        auto b1 = mn.top(); mn.pop();
        if (a1.second != b1.second) return a1.first + b1.first;
        return max(a1.first + mn.top().first, mx.top().first + b1.first);
    }
};
���ƴ���
 

�������ַ������Ǻܲ���ģ���û���õ��ѣ���������������start��end�ֱ��ʾ��ǰ����������������С����Ԫ�أ�������βԪ�أ���ôÿ�����Ǳ�����һ���µ�����ʱ��ֻ�����������βԪ�غ�start���Բ��end����������Ԫ�صľ��Բȡ����֮��Ľϴ�ֵ�����½��res���ɣ��μ��������£�

 

�ⷨ����

���ƴ���
class Solution {
public:
    int maxDistance(vector<vector<int>>& arrays) {
        int res = 0, start = arrays[0][0], end = arrays[0].back();
        for (int i = 1; i < arrays.size(); ++i) {
            res = max(res, max(abs(arrays[i].back() - start), abs(end - arrays[i][0])));
            start = min(start, arrays[i][0]);
            end = max(end, arrays[i].back());
        }
        return res;
    }
};
���ƴ���