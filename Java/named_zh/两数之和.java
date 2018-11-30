package named_zh;
/**
*问题描述：
*给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
*你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。
*注意这里下标的范围是 1 到 n，不是以 0 开头
* 样例：
*给出 numbers = [2, 7, 11, 15], target = 9, 返回 [1, 2].
*解析：
*由题目知，双重循环遍历即可得到，由于按序排好，所以只需在找到后将匹配的
下标保存到输出数组即可
**/
public class Solution {

    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int []index = new int[2];
        index[0] = index[1] = 0;
        for (int i = 0; i < numbers.length - 1; i++){
            for (int j = i + 1; j < numbers.length; j++){
                if (target == numbers[i] + numbers[j]){
                    index[0] = i + 1;
                    index[1] = j + 1;
                    return index;
                }
            }
        }
        return index;
    }
}