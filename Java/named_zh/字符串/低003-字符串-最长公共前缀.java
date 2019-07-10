package algorithm;

public class LongestCommonPrefix {
    /**
     * 最长公共前缀
     * <p>
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * 所有输入只包含小写字母a-z。
     * @param args
     */
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        longestCommonPrefix(strs);
    }

    /**
     * 方法：水平扫描法
     * 思路:
     * 首先，我们将描述一种查找一组字符串的最长公共前缀 LCP(S_1 ... S_n)LCP(S 1 ​ …S n ​ ) 的简单方法。
     * 我们将会用到这样的结论： LCP(S_1 ... S_n) = LCP(LCP(LCP(S_1, S_2),S_3),... S_n)LCP(S 1 ​ …S n ​ )=LCP(LCP(LCP(S 1 ​ ,S 2 ​ ),S 3 ​ ),…S n ​ )
     * 算法:
     * 为了运用这种思想，算法要依次遍历字符串 [S_1 ... S_n][S 1 ​ …S n ​ ]，当遍历到第 ii 个字符串的时候，找到最长公共前缀 LCP(S_1 ... S_i)LCP(S 1 ​ …S i ​ )。
     * 当 LCP(S_1 ... S_i)LCP(S 1 ​ …S i ​ ) 是一个空串的时候，算法就结束了。 否则，在执行了 nn 次遍历之后，算法就会返回最终答案 LCP(S_1 ... S_n)LCP(S 1 ​ …S n ​ )。
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1, len = strs.length; i < len; i++) {
            while (strs[i].indexOf(prefix) != 0) {//不为第一个字符都是有希望的
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
