/**
较大分组的位置

在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。

例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。

我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。

最终结果按照字典顺序输出。

示例 1:

输入: "abbxxxxzzy"
输出: [[3,6]]
解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。
示例 2:

输入: "abc"
输出: []
解释: "a","b" 和 "c" 均不是符合要求的较大分组。
示例 3:

输入: "abcdddeeeeaabbbcd"
输出: [[3,5],[6,9],[12,14]]
说明:  1 <= S.length <= 1000

**/
class Solution {
	//用外层对整个字符串进行循环，内层设置对相同字符进行循环的判断，
	//需要注意的一点是，当内层跳出循环时，其值刚好为“第一个不相同的字符的下标”
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (S.length() < 3)
            return list;
        for (int i = 0; i < S.length();) {
            int j = i;//双指针解决问题
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