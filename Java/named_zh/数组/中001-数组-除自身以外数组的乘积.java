package algorithm;

public class ProductExceptSelf {
    /**
     * 名称：除自身以外数组的乘积
     *
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * 示例:
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * @param args
     */
    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        productExceptSelf(nums);
    }

    /**
     * 两次循环扫描，使用result数组暂存乘积的累乘值
     * 1）正向保存当前位置之前的元素乘积
     * 2）逆向保存当前元素之后的元素乘积
     * 3）解释：正向逆向两次乘积刨除当前位置的乘积后得到期望结果
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int len=nums.length;
        int[] result=new int[len];
        int mul=1;
        for(int i=0;i<len;i++){
            result[i]=mul;//保存除-当前位置元素-的前面所有元素的乘积
            mul*=nums[i];//使用当前位置元素更新乘积结果
        }
        mul=1;
        for(int i=len-1;i>=0;i--){
            //保存除-当前位置元素-的后面所有元素的乘积，特别注意这是‘*’、因为当前位置已经有临时的乘积结果了、需要将后面循环的累乘进去获得最后结果
            result[i]*=mul;
            mul*=nums[i];//使用当前位置元素更新乘积结果
        }
        return result;
    }
}
