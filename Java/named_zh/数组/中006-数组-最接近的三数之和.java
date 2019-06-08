package algorithm;

import java.util.Arrays;

public class ThreeSumClosest {
    /**
     * 最接近的三数之和
     * <p>
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
     * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {-1, 2, 1, -4};
        int[] nums = {0, 2, 1, -3};//target=1，输出0
        int target = 1;
        threeSumClosest(nums, target);
    }

    //排序后遍历每个数组元素，在每次遍历元素使用高低指针寻找另外两个数与当前遍历元素的和、使用一个全局的变量保存最接近的和
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int near = nums[0] + nums[1] + nums[2];//初始设置最接近的是开头三个
        //写遍历判断的循环
        for (int i = 0, len = nums.length - 2; i < len; i++) {//最后的两位太短不需要判断
            int low = i + 1;
            int high = len + 1;//末位
            while (low < high) {//高低指针满足条件继续遍历
                int tmp = nums[low] + nums[high] + nums[i];
                if (Math.abs(tmp - target) < Math.abs(near - target)) {
                    near = tmp;//两者的差更小代表越接近
                }
                if (tmp > target) {
                    high--;//三数之和大于目标则高位降低
                } else if (tmp < target) {
                    low++;//三数之和小于目标则低位变大
                } else {
                    return target;//三数之和与目标等同，即最接近的返回
                }
            }
        }
        return near;
    }
}
