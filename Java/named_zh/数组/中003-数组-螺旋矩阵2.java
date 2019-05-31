package algorithm;

public class GenerateMatrix {
    /**
     * 螺旋矩阵 II
     * <p>
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * 示例:
     * 输入: 3
     * 输出:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = 3;
        generateMatrix(n);
    }

    /**
     * 四个方向遍历数组：左向右，上往下，右向左，下往上
     * 使用四个指针表示当前每个方向能访问的最大值，每次到达最大值换方向，判断指针增加后是否超出范围
     * 详细的代码注释见螺旋矩阵1
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0, right = n - 1, up = 0, down = n - 1;//四个方向的指针
        int count = 1;
        while (true) {
            for (int i = left; i <= right; i++) result[up][i] = count++;
            if (++up > down) break;
            for (int i = up; i <= down; i++) result[i][right] = count++;
            if (--right < left) break;
            for (int i = right; i >= left; i--) result[down][i] = count++;
            if (--down < up) break;
            for (int i = down; i >= up; i--) result[i][left] = count++;
            if (++left > right) break;
        }
        return result;
    }
}
