package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Permute {
    /**
     * 全排列
     *
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     * @param args
     */
    public static void main(String[] args) {
        int[] nums={1,2,3};
        permute(nums);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        boolean[] visited=new boolean[nums.length];//标志当前位是否访问过，访问过就不添加
        backtrack(nums,result,new ArrayList<Integer>(),visited);
        return result;
    }

    /**
     * 使用递归遍历
     * 1）递归体使用一个循环控制当前访问的元素，使用访问数组控制当前元素是否访问
     * 2）每次递归由于递归体是循环从全排列数组头开始访问，所以，递归返回时要清空当前添加的和访问标志
     * 3）递归返回时，由于循环，会继续进入递归体，若是循环达到数组长度会先返回，没达到就代表一种新的排列方式，
     *    根据递归体的特性从全排列数组头开始访问元素是否遍历
     * @param nums 原始元素数组
     * @param result 保存全排列的所有结果
     * @param tmp 临时存储一个全排列的结果
     * @param visited 标志当前元素是否访问，true为访问
     */
    private static void backtrack(int[] nums, List<List<Integer>> result, ArrayList<Integer> tmp, boolean[] visited) {
        int len=nums.length;
        if(tmp.size()==len){
            result.add(new ArrayList<>(tmp));//使用新建列表添加到结果是为了不破坏中间的排序结果
            return;
        }
        for(int i=0;i<len;i++){//循环来控制当前加入的元素，由于递归的原因，所以会每次都会从头遍历到尾，所以使用访问数组控制访问
            if(!visited[i]){//如果当前元素没访问过那就添加
                visited[i]=true;//标志元素访问过
                tmp.add(nums[i]);//临时列表标志添加过
                backtrack(nums,result,tmp,visited);//全排列的遍历，会自动跳过访问的，因为每次递归都是从全排列数组的头重新开始，所以会访问所有的全排列方式
                visited[i]=false;//将当前添加的从临时列表移除并标志没访问过，等待下一次全排列的遍历
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
