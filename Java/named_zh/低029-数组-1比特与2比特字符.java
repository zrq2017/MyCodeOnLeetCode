/**
1比特与2比特字符

有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。

现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。

示例 1:

输入: 
bits = [1, 0, 0]
输出: True
解释: 
唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
示例 2:

输入: 
bits = [1, 1, 1, 0]
输出: False
解释: 
唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
注意:

1 <= len(bits) <= 1000.
bits[i] 总是0 或 1.

**/
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int count = 0;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 0) {//由于最后一个必为0，所以由count计数
				/**当倒数第二个进入为0，代表最后一个必为1字节数
				count计算个数，注意出去才加1，也就是[1010],这种第二个0进入count=1，返回false
				若是进入为[0110]count为2时才进入，返回true
				就是count是刨除最后一位在2字节数是偶数时进入
				**/
                return count % 2 == 0 ? true : false;
            }
            count++;
        }
        return count % 2 == 0 ? true : false;//上述解释一致
    }
}