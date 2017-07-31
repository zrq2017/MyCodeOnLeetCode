/**
*问题描述：
*给定一个字符串，逐个翻转字符串中的每个单词。
* 说明：
*单词的构成：无空格字母构成一个单词
*输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
*如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个
*解析：
*先进行字符串判空操作或判断是否有空字符，结果都返回传入字符串
*有空字符的字符串分割，分割后长度为0返回传入字符串
*由可变字符串根据分割后的数组倒序添加到字符串尾
*返回结果字符串
**/
public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code
         if (s == null || s.length() == 0 || s.indexOf(" ") == -1) {
            return s;
        }

        String[] strs = s.split(" ");
        if (strs.length == 0) {
            return s;
        }
        StringBuffer sb = new StringBuffer();

        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i] + " ");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
