package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /**
     * 三数之和
     * <p>
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums);
    }

    /**
     * 先排序，再求两个数的和等于第三个数的相反数：a+b=-c
     * 1）堆排序
     * 2）第一层循环遍历当前的数组，第二层循环使用双指针去找有可能满足等式：a+b=-c的a,b
     * 注：题目的不包含重复的三元组 并不代表 三元组元素不能重复
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) return list;
        buildHeap(nums, nums.length - 1);
        for (int i = nums.length - 1; i >= 0; i--) {//堆排序，先将数组变成有序的
            int t = nums[0];
            nums[0] = nums[i];
            nums[i] = t;
            buildHeap(nums, i - 1);
        }
        for (int i = 0, len = nums.length - 2; i < len; ) {
            int target = 0 - nums[i];
            int low = i + 1;
            int high = len + 1;
            while (low < high) {
                int sum = nums[low] + nums[high];
                if (sum > target) {
                    while (low < --high && nums[high] == nums[high + 1]) ;//大于目标，高位降低到去除高位相等的情况，即下一个小于当前位的数
                } else if (sum < target) {
                    while (low++ < high && nums[low] == nums[low - 1]) ;//去除后一位与前一位相等的情况
                } else {//添加相等的情况到结果
                    list.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (++low < high && nums[low] == nums[low - 1]) ;//去除相等的下一位
                }
            }
            while (++i < len && nums[i] == nums[i - 1]) ;//去重：判断数组下一位是否与当前位一样，一样就跳过
        }
        return list;
    }

    private static void buildHeap(int[] arr, int len) {
        for (int i = len; i >= 0; i--) {
            if (arr[i] > arr[(i - 1) / 2]) {
                int t = arr[i];
                arr[i] = arr[(i - 1) / 2];
                arr[(i - 1) / 2] = t;
            }
        }
    }
}
