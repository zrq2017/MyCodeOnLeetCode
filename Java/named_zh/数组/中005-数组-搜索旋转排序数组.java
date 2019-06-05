package algorithm;

public class Search {
    /**
     * 搜索旋转排序数组
     * <p>
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 示例 1:
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int r=search(nums, target);
    }

    /**
     * 二分查找
     * 1）将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
     * 2）此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private static int search(int[] nums, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < nums[high]) {//满足中间小于后面的说明后半截可能是有序链表
            if (nums[mid] < target && target <= nums[high]) {//确实是在范围内，直接在范围内搜索
                return search(nums, mid + 1, high, target);
            } else {//不在范围内的说明在前半截无序链表中
                return search(nums, low, mid - 1, target);
            }
        } else {//前半截是有序链表
            if (nums[low] <= target && target < nums[mid]) {//确实是在范围内，直接在范围内搜索
                return search(nums, low, mid - 1, target);
            } else {//不在范围内的说明在后半截无序链表中
                return search(nums, mid + 1, high, target);
            }
        }
    }
}
