/*****
正则表达式匹配

给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
'.' 匹配任意单个字符。
'*' 匹配零个或多个前面的元素。
匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
**/
class Solution {
    /**
    思路类似最长公共子序列，
    dp[i][j] = dp[i - 1][j - 1], 如果s[i] == p[j] || p[j] == '.'
                  dp[i][j - 2], 如果p[j] == '*' && s[i] != p[j - 1]
                  dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j - 2] || dp[i][j - 2], 如果p[j] == '*' && s[i] == p[j - 1]
    稍稍解释下：
    对于s和p，设各个最后一个字符为x, y，p的倒数第二字符为z，除此外前面字符设为S,P，则：
    s = Sx
    p = Pzy
    如果x == y或y == '.'，则如果S和Pz匹配，则s和p匹配，因为最后两字字母是匹配的。这就缩减了问题规模。
    而对于y ==  '*'的情况，需要考虑z：
    如果x != z，则只有在s和P匹配的情况下，s和p才匹配。
    如果x == z，设匹配符号为~吧，方便，则如果S~P，S~Pz，S~Pzy，Sx~P，Sx~Pz，都可得出s和p匹配。
	
	即，判断前面一位是否为*，根据结果决定是否判断前面两位或是前一位
    **/
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
		boolean[][] memory = new boolean[sLen+1][pLen+1];
		memory[0][0] = true;
		for(int i = 0; i <= sLen; i++) {
			for(int j = 1; j <= pLen; j++) {
				if(p.charAt(j-1) == '*') {
					memory[i][j] = memory[i][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) || 
							p.charAt(j-2) == '.') && memory[i-1][j]);
				}else {
					memory[i][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
									&& memory[i-1][j-1];
				}
			}
		}
		return memory[sLen][pLen];
    }
}
