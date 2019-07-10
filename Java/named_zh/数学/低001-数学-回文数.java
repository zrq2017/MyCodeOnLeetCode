package algorithm;

public class IsPalindrome {
    /**
     * 回文数
     * <p>
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * 输入: 121
     * 输出: true
     * 示例 2:
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * 进阶:
     * 你能不将整数转为字符串来解决这个问题吗？
     * @param args
     */
    public static void main(String[] args) {
        int x = 121;
//        int x = 10;
        isPalindrome(x);
    }

    //数字操作
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int result = 0;//保存取反的结果
        int tmp = x;//保存求余后的剩余值
        while (tmp > 0) {//循环过程就是一个数字取反的的过程
            result *= 10;//先进位
            result += tmp % 10;//保存求余值
            tmp = tmp / 10;//求余每一位，在下个循环由result进位保存
        }
        return result == x;
    }

    //转为字符串操作
    public static boolean isPalindromeStr(int x) {
        String s = x + "";
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
