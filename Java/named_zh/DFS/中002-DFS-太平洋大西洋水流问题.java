package algorithm.summary;

import java.util.ArrayList;
import java.util.List;

public class DFSAchiveTwoArea {
    /**
     * 417. 太平洋大西洋水流问题
     *
     * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
     * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
     * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
     * 提示：
     * 输出坐标的顺序不重要
     * m 和 n 都小于150
     * 示例：
     * 给定下面的 5x5 矩阵:
     *
     *   太平洋 ~   ~   ~   ~   ~
     *        ~  1   2   2   3  (5) *
     *        ~  3   2   3  (4) (4) *
     *        ~  2   4  (5)  3   1  *
     *        ~ (6) (7)  1   4   5  *
     *        ~ (5)  1   1   2   4  *
     *           *   *   *   *   * 大西洋
     *
     * 返回:
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        DFSAchiveTwoArea d=new DFSAchiveTwoArea();
        d.pacificAtlantic(matrix);
    }

    private int m, n;
    private int[][] matrix;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ret;
        }

        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(i, 0, canReachP);//第一列是能到达太平洋的
            dfs(i, n - 1, canReachA);//最后一列是能到达大西洋的
        }
        for (int i = 0; i < n; i++) {
            dfs(0, i, canReachP);//第一行是能到达太平洋的
            dfs(m - 1, i, canReachA);//最后一行是能到达大西洋的
        }
        //由四周开始直接向内部查找是不是能到，不需要查找内部其他的
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachP[i][j] && canReachA[i][j]) {//都能到才记录
                    ret.add(new int[]{i, j});
                }
            }
        }

        return ret;
    }

    //默认当前点可以到，判断下个点能不能到，能到就递归判断
    private void dfs(int r, int c, boolean[][] canReach) {
        if (canReach[r][c]) {
            return;
        }
        canReach[r][c] = true;
        for (int[] d : direction) {
            int nextR = d[0] + r;
            int nextC = d[1] + c;
            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n
                    || matrix[r][c] > matrix[nextR][nextC]) {
                //从边框节点出发能到达的内部的节点，所以是matrix[r][c] > matrix[nextR][nextC]，外面节点(r,c)小于内部接地那(nextR,nextC)才能由内部流向外部
                continue;
            }
            //满足条件代表下一个点可以到
            dfs(nextR, nextC, canReach);
        }
    }
}
