package algorithm;

public class FindMedianSortedArrays {
    /**
     * 寻找两个有序数组的中位数
     * <p>
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * <p>
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        double r=findMedianSortedArrays(nums1, nums2);
    }

    /**
     * 使用堆排序后求中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length=nums1.length+nums2.length;
        int[] nums=new int[length];
        //源数组，源数组复制地址，目的数组，目的数组起始地址，复制的源数组长度
        System.arraycopy(nums1,0,nums,0,nums1.length);
        System.arraycopy(nums2,0,nums,nums1.length,nums2.length);
        buildHeap(nums,length-1);
        for (int i=length-1;i>=0;i--){
            int t=nums[0];
            nums[0]=nums[i];
            nums[i]=t;
            buildHeap(nums,i-1);
        }
        if(length%2==0){
            return ((double)(nums[length/2]+nums[length/2-1]))/2;//偶数要中间两位
        }else {
            return (double)(nums[length/2]);//奇数有刚好是中间
        }
    }

    private static void buildHeap(int[] nums, int i) {
        for(;i>=0;i--){
            if(nums[i]>nums[(i-1)/2]){
                int t=nums[i];
                nums[i]=nums[(i-1)/2];
                nums[(i-1)/2]=t;
            }
        }
    }
}
