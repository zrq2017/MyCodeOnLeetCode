package algorithm;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    /**
     * 螺旋矩阵
     * <p>
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * 示例 1:
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * <p>
     * 示例 2:
     * 输入:
     * [
     * [1, 2, 3, 4],
     * [5, 6, 7, 8],
     * [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        spiralOrder(matrix);
    }

    /**
     * 四个方向遍历数组：左向右，上往下，右向左，下往上
     * 使用四个指针表示当前每个方向能访问的最大值，每次到达最大值换方向，判断指针增加后是否超出范围
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new ArrayList<>();//判断矩阵不为空的情况
        List<Integer> list = new ArrayList<>();
        int left = 0, right = matrix[0].length - 1, up = 0, down = matrix.length - 1;//left左向右，right右向左，up上到下，down下到上;前两者不同列指针，后两者不同行指针;矩阵不规则
        while (true) {
            //左往右
            for (int i = left; i <= right; i++) list.add(matrix[up][i]);
            if (++up > down) break;//向上走的超出当前最大访问范围,判断退出条件是下一步循环遍历的起始索引，上一步矩阵遍历不变的那个索引
            //上往下
            for (int i = up; i <= down; i++) list.add(matrix[i][right]);
            if (--right < left) break;//向左走的超出当前最大访问范围
            //右往左
            for (int i = right; i >= left; i--) list.add(matrix[down][i]);
            if (--down < up) break;//向下走的超出当前最大访问范围
            //下往上
            for (int i = down; i >= up; i--) list.add(matrix[i][left]);
            if (++left > right) break;//向右走的超出当前最大访问范围
        }
        return list;
    }
}
