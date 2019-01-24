/*****
回文子串

给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:

输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
示例 2:

输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
注意:

输入的字符串长度不会超过1000。

**/
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
			return 0;
		}
		// 将每个s中间隔一个'#'，比如"abcd"变成"#a#b#c#d#"，这样做的目的是可以判断是否是偶回文
		// 否则只能判断奇回文
		char[] help = new char[2 * s.length() + 1];
		for (int i = 0; i < help.length; i++) {
			if (i % 2 == 0) {
				help[i] = '#';
			} else {
				help[i] = s.charAt(i / 2);
			}
		}
		// 记录有多少个回文串
		int res = 0;
		// 检查以每个字符为中心是不是回文串
		for (int i = 0; i < help.length; i++) {
			// 表示偏离i的大小
			int j = 1;
			// 记录当前回文串长度(包括#)
			int len = 1;
			// 计算以i为中心的回文串长度
			while (i + j < help.length && i - j >= 0) {
				if (help[i + j] == help[i - j]) {
					j++;
					len += 2;
				} else {
					break;
				}
			}
			// len/2表示去除#的回文串长度，再加一除二就是以i为中心的回文串个数了
			res += ((len / 2) + 1) / 2;
		}
		return res;
    }
}