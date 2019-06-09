package algorithm;

public class MaxArea {
    /**
     * 盛最多水的容器
     * <p>
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例:
     * 输入: [1,8,6,2,5,4,8,3,7]
     * 输出: 49
     * @param args
     */
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        maxArea(height);
    }

    /**
     * 使用动态规划思想
     * maxArea=Max(min(height[i],height[j])*(j-i))，其中0<=i<j<height.length //木桶短板决定水的数量
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int result = 0;//保存最大面积
        int i = 0, j = height.length - 1;//双指针判断更短边
        while (i < j) {//由长度数组两端向中间寻找最大的面积
            int h = Math.min(height[i], height[j]);
            result = Math.max(result, h * (j - i));
            if (height[i] < height[j]) {//更短边优先增大寻找更长边，保证了最大面积一直是最大面积
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
}
