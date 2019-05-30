package algorithm;

public class SingleNumber {
    /**
     * 只出现一次的数字
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     * @param args
     */
    public static void main(String[] args) {
        int[] nums={2,2,1};
        singleNumber(nums);
    }

    //位异或：同0异1，最后只剩不一样的
    public static int singleNumber(int[] nums) {
        int result=0;
        for(int i=0,len=nums.length;i<len;i++){
            result^=nums[i];
        }
        return result;
    }
}
