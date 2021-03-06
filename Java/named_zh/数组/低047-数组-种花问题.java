/**
种花问题
假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

示例 1:

输入: flowerbed = [1,0,0,0,1], n = 1
输出: True
示例 2:

输入: flowerbed = [1,0,0,0,1], n = 2
输出: False
注意:

数组内已种好的花不会违反种植规则。
输入的数组长度范围为 [1, 20000]。
n 是非负整数，且不会超过输入数组的大小。

**/
class Solution {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt=0;
        for(int i=0;i<flowerbed.length;i++){
            if(flowerbed[i]==1){//为1直接继续判断下一个
                continue;
            }
            int pre=i==0?0:flowerbed[i-1];//前一个
            int next=i==flowerbed.length-1?0:flowerbed[i+1];//后一个
            if(pre==0&&next==0){//前后都为0才能插入
                cnt++;
                flowerbed[i]=1;
            }
        }
        return cnt>=n;//满足条件才返回true
    }
    //判断开头结尾是不是连续为0，是计数加一，判断中间是不是连续三个0是就加1，每次加一都要将数组置位
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
		for (int i = 0; i < flowerbed.length; i++) {
			if (i == 0) {
				if (flowerbed.length == 1) {//花坛长度为1且为空时可种的计数++
					if (flowerbed[i] == 0) {
						count++;
					}
				} else {
					if (flowerbed[i + 1] == 0 && flowerbed[i] == 0) {//开头时连续两个都为0，可种1个
						flowerbed[i] = 1;
						count++;
					}
				}
			} else if (i > 0 && i < flowerbed.length - 1) {//i不为1的情况且不为最后一个的情况
				if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
					count++;//连续三个0才可以种一个
					flowerbed[i] = 1;

				}

			} else {//判断最后两个是不是连续0
				if (flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
					count++;
					flowerbed[flowerbed.length - 1] = 1;
				}
			}
		}
		if (count >= n) {//满足条件
			return true;

		} else {
			return false;
        }
    }
}