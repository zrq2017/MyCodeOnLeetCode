package algorithm;

public class LongestPalindrome {
    /**
     * 最长回文子串
     * <p>
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * 输入: "cbbd"
     * 输出: "bb"
     * @param args
     */
    public static void main(String[] args) {
        String s = "babad";
        longestPalindrome(s);
    }

    /**
     * 步骤：
     * 1）使用回文子串中的想法，给每个字符前后添加‘#’，
     * 2）然后按照每一位字符为中心判断回文子串的长度，将该长度与最大长度比较，记录最大长度的起止位置
     * 3）根据起止位置将除开‘#’的回文串添加到结果值并返回
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        if (s == null || s.length() == 0) {
            return "";
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
        int max = 0, left = 0, right = 0;
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
            if (max < len) {
                max = len;
                left = i - j + 1;
                right = i + j - 1;
            }
        }
        for (int i = left; i <= right; i++) {
            if (help[i] != '#') {
                sb.append(help[i]);
            }
        }
        return sb.toString();
    }
}
