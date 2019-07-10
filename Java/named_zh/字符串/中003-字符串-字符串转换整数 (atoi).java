package algorithm;

public class MyAtoi {
    /**
     * 字符串转换整数 (atoi)
     * <p>
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
     * 则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     * 说明：
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
     * 如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
     * 示例 1:
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     * 因此无法执行有效的转换。
     * 示例 5:
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−2^31) 。
     *
     * @param args
     */
    public static void main(String[] args) {
//        String str="4193 with words";
//        String str="words and 987";
//        String str="42";
//        String str="-42";
//        String str="-91283472332";
        String str="-2147483648";
//        String str="-2147483647";
//        String str = "2147483646";
//        String str = "2147483800";
//        String str = "1095502006p8";
//        String str = "1192820738r2";
//        String str = "-1010023630o4";
        int r = myAtoi(str);
    }

    //简单的情况判断，先判断是不是用符号开头的或数字开头的，然后转化，最麻烦的在于处理相等情况的地方，容易想不清楚，这一题没啥意义，最大的意义只是要慢慢理思路
    public static int myAtoi(String str) {
        int flag = 1;
        String s = str.trim();//去除首尾空格
        if (s.length() == 0) return 0;
        StringBuilder sb = new StringBuilder();
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {//以符号开头的
            if (s.charAt(0) == '-') {//负数的标志位
                flag = -1;
            }
            for (int i = 1, len = s.length(); i < len; i++) {//从数字头开始取数字
                if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    sb.append(s.charAt(i));
                } else {
                    break;
                }
            }
        } else if ((s.charAt(0) - '0' >= 0 && s.charAt(0) - '0' <= 9)) {//以数字开头的
            for (int i = 0, len = s.length(); i < len; i++) {//从数字头开始取数字
                if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    sb.append(s.charAt(i));
                } else {
                    break;
                }
            }
        } else {//其余不满足的情况
            return 0;
        }
        //去除开头的0
        int index = 0;
        for (int len = sb.length(); index < len; index++) {
            if (sb.charAt(index) != '0') {
                break;
            }
        }
        if (index > 0) {//如果确实有0，删除前面的0
            sb = sb.delete(0, index);
        }
        if (sb.length() == 0) {
            return 0;//没有数字
        }
        String sm = flag == 1 ? String.valueOf(Integer.MAX_VALUE) : String.valueOf(Integer.MIN_VALUE).substring(1);//保存最大最小值
        if (sb.length() > sm.length()) {//长度超过直接返回最大值
            return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else if (sb.length() < sm.length()) {//长度小于，直接转化
            return flag * Integer.valueOf(sb.toString());
        } else {//两者长度相等
            int x1 = Integer.valueOf(sb.substring(0, sm.length() - 1));
            int x2 = Integer.valueOf(sm.substring(0, sm.length() - 1));
            if (x1 < x2) {//判断除最后一位的转化是否超出范围
                return Integer.valueOf(flag == -1 ? "-" + sb.toString() : sb.toString());
            } else if (x1 == x2) {
                int lb = Integer.valueOf(sb.substring(sm.length() - 1));
                int lm = Integer.valueOf(sm.substring(sm.length() - 1));
                //判断最后一位的情况
                if (lb < lm) {//判断除最后一位的转化是否超出范围
                    return Integer.valueOf(flag == -1 ? "-" + sb.toString() : sb.toString());//在范围内才可以转化，不在就返回最大值
                }
            }
            return flag == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }
}
