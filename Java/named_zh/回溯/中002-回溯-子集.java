package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    /**
     * 子集
     * <p>
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 示例:
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     *
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> list=new ArrayList<>();
        int[] nums = {1,2,3};
        subsets(nums);
    }

    /**
     * 从前往后遍历, 遇到一个数, 之前的所有集合添加上这个数组成新的子集.
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1){
            return res;
        }
        // 初始化[]
        res.add(new ArrayList<>());
        for(int i=0;i<nums.length;i++){
            for(int j=0,size=res.size();j<size;j++){
                List<Integer> list=res.get(j);
                List<Integer> temp=new ArrayList<>(list);//获取前面的集合然后添加新的元素
                temp.add(nums[i]);//添加第i个元素
                res.add(temp);
            }
        }
        return res;
    }
}
