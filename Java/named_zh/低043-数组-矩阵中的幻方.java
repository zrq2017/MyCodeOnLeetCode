/**
矩阵中的幻方

3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。

给定一个由整数组成的 N × N 矩阵，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。

 

示例 1:

输入: [[4,3,8,4],
      [9,5,1,9],
      [2,7,6,2]]
输出: 1
解释: 
下面的子矩阵是一个 3 x 3 的幻方：
438
951
276

而这一个不是：
384
519
762

总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
提示:

1 <= grid.length = grid[0].length <= 10
0 <= grid[i][j] <= 15

**/
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res=0;
        for(int i=0;i+2<n;i++) {
           for(int j=0;j+2<m;j++){
                res+=judge(i,j,grid);
            }
        }
        return res;
    }
   private int judge(int x,int y,int [][] g){
        int[] a = new int[10];
       //判断是否是1-9的数据，是否唯一
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(g[i][j]>=10 || g[i][j]<=0) return 0;
                a[g[i][j]]++;
                if( a[g[i][j]]>1) return 0;
            }
        }
       //每一行
        int sum =-1,tmp=0;
        for(int i=x;i<x+3;i++){
            tmp=0;
            for(int j=y;j<y+3;j++){
                tmp+=g[i][j];
            }
            if(sum==-1) sum=tmp;
            else if(sum!=tmp) return 0;
        }
       //每一列
        for(int j=y;j<y+3;j++){
            tmp=0;
            for(int i=x;i<x+3;i++){
                tmp+=g[i][j];
            }
            if(sum!=tmp) return 0;
        }
       //主对角线
        tmp = g[x][y]+g[x+1][y+1]+g[x+2][y+2];
        if(sum!=tmp) return 0;
       //副对角线
        tmp = g[x][y+2]+g[x+1][y+1]+g[x+2][y];
        if(sum!=tmp) return 0;
        return 1;
    }
}