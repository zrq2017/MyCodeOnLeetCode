/**
图片平滑器
包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。

示例 1:

输入:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
输出:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
解释:
对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
**/
class Solution {
    public int[][] imageSmoother(int[][] M) {
        int rows = M.length;
        int cols = M[0].length;
        int[][] res = new int[rows][cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                int sum = 0;
                int count = 0;
                // 计算3*3区域的和，并注意边界问题
                for(int x=Math.max(0,i-1); x<=Math.min(rows-1, i+1); x++)
                {
                    for(int y=Math.max(0, j-1); y<=Math.min(cols-1, j+1); y++)
                    {
                        sum += M[x][y]; // 对所有单元格求和
                        count++; // 计数增加，用于计算平均值
                    }
                }
                
                res[i][j] = sum / count; // 获得平均值
            }
        }
        return res;
    }
}