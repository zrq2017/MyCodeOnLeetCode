/*****
岛屿的最大面积

给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

示例 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

示例 2:

[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。

注意: 给定的矩阵grid 的长度和宽度都不超过 50。
**/
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int row=grid.length,col=grid[0].length,max=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){//双层遍历，使用递归操作
                int t;
                if((t=dfs(grid,i,j))>max){
                    max=t;
                }
            }
        }
        return max;
    }
    
    /**
    *递归操作判断四个方向是否符合条件，并将走过格子设置标记为2
    **/
    public int dfs(int[][] grid,int row,int col){
        if(row<0||row>=grid.length||col<0||col>=grid[0].length||grid[row][col]!=1) return 0;
        int count=1;
        grid[row][col]=2;//设置访问过的标志
        count+=dfs(grid,row,col+1);
        count+=dfs(grid,row,col-1);
        count+=dfs(grid,row+1,col);
        count+=dfs(grid,row-1,col);
        return count;
    }
}