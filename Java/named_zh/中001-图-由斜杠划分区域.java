/*****
由斜杠划分区域

在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

返回区域的数目。

 

示例 1：

输入：
[
  " /",
  "/ "
]
输出：2
解释：2x2 网格如下：

示例 2：

输入：
[
  " /",
  "  "
]
输出：1
解释：2x2 网格如下：

示例 3：

输入：
[
  "\\/",
  "/\\"
]
输出：4
解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
2x2 网格如下：

示例 4：

输入：
[
  "/\\",
  "\\/"
]
输出：5
解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
2x2 网格如下：

示例 5：

输入：
[
  "//",
  "/ "
]
输出：3
解释：2x2 网格如下：

 

提示：

1 <= grid.length == grid[0].length <= 30
grid[i][j] 是 '/'、'\'、或 ' '。

题解：
https://blog.csdn.net/qq_17550379/article/details/85262219
**/
class Solution {
    /**
     * 方法一：dfs
     * 1）将每一个字符的格子分成3*3的小格子，
     * 2)若是‘\’则将3*3的主对角线元素置1
     * 若是‘/’则将辅对角线的元素置为1
     * 3)最后使用dfs访问遍历（3N*3N）保存的小格子数组：
     * 每一次遍历一个格子判断是否为0（能访问，结果连通区域+1）、深度优先判断四个方向能否连通，能将为0的都置为1，直到不能连通，表示这个格子遍历完，连通个数加1
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        int gridLen=grid.length;
        int tmpGridLen=gridLen*3;
        int[][] tmpGrid=new int[tmpGridLen][tmpGridLen];//默认初始化为1
        for(int i=0;i<gridLen;i++){//给3*3的每个小格子有斜杆经过的格子都置1
            for(int j=0;j<gridLen;j++){
                if(grid[i].substring(j,j+1).equals("\\")){
                    tmpGrid[i*3][j*3]=1;
                    tmpGrid[i*3+1][j*3+1]=1;
                    tmpGrid[i*3+2][j*3+2]=1;
                }
                if(grid[i].substring(j,j+1).equals("/")){
                    tmpGrid[i*3][j*3+2]=1;
                    tmpGrid[i*3+1][j*3+1]=1;
                    tmpGrid[i*3+2][j*3]=1;
                }
            }
        }
        int res=0;
        for(int i=0;i<tmpGridLen;i++){//对3*3分割的小格子进行dfs
            for(int j=0;j<tmpGridLen;j++){
                if(tmpGrid[i][j]==0){//判断当前的能否访问，即抛开访问过的，剩下的能连通的区域才可以进入
                    dfs(tmpGrid,i,j);//一次dfs堵死一个连通区域的所有格子
                    res+=1;
                }
            }
        }
        return res;
    }

    public void dfs(int[][] grid,int i,int j){
        if(i>=0 && j>=0 && i<grid.length && j<grid.length && grid[i][j]==0){//不超过范围，且为0表示没访问过可以连通
            grid[i][j]=1;//置1表访问过不连通
            //继续使用深度优先搜索向四个方向将能连通的堵死
            dfs(grid,i,j+1);//向右
            dfs(grid,i,j-1);//向左
            dfs(grid,i+1,j);//向上
            dfs(grid,i-1,j);//向下
        }
    }
}

class Solution {
    /**
     * 方法二：区域连接求数量的问题用并查集解决
     * 大方块由N * N的个小方块组成
     * 将小方块按照双线划分顺时针分为0,1,2,3 共4个区域
     * 并且小方块之间是两两连接的 ，左方块的 1区域 与 右方块的 3区域连接，上方块的 2区域 与 下方块的 0 区域连接
     * 当‘/’时，小方块的 0,3 区域连接， 1,2区域连接
     * 当‘\\’时 ，小方块的 0,1区域连接，2,3区域连接
     * 当‘ ’时，小方块4个区域连接
     * 求区域个数实际是求有多少颗树
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid){
        int gridLen=grid.length;
        UnionGrid unionGrid=new UnionGrid(4*gridLen*gridLen);
        for(int i=0;i<gridLen;i++){
            for(int j=0;j<gridLen;j++){
                int start=4*(i*gridLen+j);
                switch (grid[i].charAt(j)){
                    case '/':unionGrid.merge(start,start+3);unionGrid.merge(start+1,start+2);break;
                    case '\\':unionGrid.merge(start,start+1);unionGrid.merge(start+2,start+3);break;
                    case ' ':unionGrid.merge(start,start+1);unionGrid.merge(start+1,start+2);unionGrid.merge(start+2,start+3);break;
                }
                //i,j大于0是抛开第一行第一列的边界情况
                if(i>0){//不同行相邻的上下格子0、2合并
                    unionGrid.merge(start,start-4*gridLen+2);
                }
                if(j>0){//同行相邻的格子1、3合并
                    unionGrid.merge(start+3,start-3);
                }
            }
        }
        int count=0;
        for(int i=0;i<unionGrid.parent.length;i++){
            if(i==unionGrid.parent[i]){//遍历父节点数组，每个元素是当前节点的代表一个连通区域
                count++;
            }
        }
        return count;
    }

    /**
     * 并查集内部类实现
     * 1.每个点的父节点数组
     * 2.查找当前节点的父节点方法
     * 3.合并两个节点的父节点
     */
    private class UnionGrid{
        int[] parent;
        UnionGrid(int size){//初始化父节点数组，初始时父节点都是自身
            parent=new int[size];
            for(int i=0;i<size;i++){
                parent[i]=i;
            }
        }

        public int find(int index){//查找当前节点的父节点
            while(parent[index]!=index){
                index=parent[index];
            }
            return index;
        }

        public void merge(int p,int q){//合并两个节点的父节点
            int proot=find(p);
            int qroot=find(q);
            parent[proot]=qroot;
        }

    }
}