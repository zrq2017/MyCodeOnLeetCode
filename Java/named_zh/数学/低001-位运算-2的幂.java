package algorithm;

public class IsPowerOfTwo {
    /**
     * 2的幂
     * <p>
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * <p>
     * 示例 1:
     * 输入: 1
     * 输出: true
     * 解释: 2^0 = 1
     * 示例 2:
     * 输入: 16
     * 输出: true
     * 解释: 2^4 = 16
     * 示例 3:
     * 输入: 218
     * 输出: false
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = 16;
        Object o = isPowerOfTwo(n);
    }

    public static boolean isPowerOfTwo(int n) {
        /**
         * (n & (n-1))去掉最低位的1
         * 2的幂只有一个最高位为1，减1剩下的除开最高位为0其余位为1
         * 例如：
         * 2^4=16=10000
         * 16 & 15=10000 & 01111=0
         */
        return n > 0 && (n & (n - 1)) == 0;
    }
}
