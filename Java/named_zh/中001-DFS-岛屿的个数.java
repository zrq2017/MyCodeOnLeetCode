/*****
岛屿的个数

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:
11000
11000
00100
00011

输出: 3
**/
class Solution {
    /**
    * 递归实现，将走过的改为2
    **/
    public int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        int row=grid.length,col=grid[0].length,count=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                count+=dfs(grid,i,j);
            }
        }
        return count;
    }
    
    public int dfs(char[][] grid,int row,int col){
        if(row<0||row>=grid.length||col<0||col>=grid[0].length||grid[row][col]!='1') return 0;
        grid[row][col]='2';
        dfs(grid,row,col-1);
        dfs(grid,row,col+1);
        dfs(grid,row-1,col);
        dfs(grid,row+1,col);
        return 1;
    }
}