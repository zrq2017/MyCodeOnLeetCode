package algorithm;

import java.util.Arrays;

public class FindKthLargest {
    /**
     * 数组中的第K个最大元素
     * <p>
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     * <p>
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        Object o = findKthLargest(nums, k);
    }

    /**
     * 排序后返回，使用堆排序
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        buildHeap(nums,nums.length-1);//初始构造大根堆
        int end=nums.length-k;
        for(int i=nums.length-1;i>=end;i--){//返回第K大的，所以堆排序只做到条件为K的
            int temp=nums[0];//将根节点即最大值节点放到尾节点
            nums[0]=nums[i];
            nums[i]=temp;
            buildHeap(nums,i-1);//调整新的长度的大根堆
        }
        return nums[nums.length - k];
    }

    /**
     * 构建大根堆：从末尾节点开始与根节点比较大小，较大的做根节点
     * 根节点：i,左节点：2i+1,右节点：2i+2
     * 所以右节点为i,根节点为(i-2)/2;左节点为i,根节点为(i-1)/2
     * 根据int的取整特性根节点表示为(i-1)/2
     * @param nums
     * @param n
     */
    public static void buildHeap(int[] nums,int n){
        for(int i=n;i>=0;i--){
            if(nums[i]>nums[(i-1)/2]){
                int temp=nums[i];
                nums[i]=nums[(i-1)/2];
                nums[(i-1)/2]=temp;
            }
        }
    }

    /**
     * 排序后返回,调用库函数
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestLib(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
