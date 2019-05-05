/*****
课程表

现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

示例 1:

输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。
**/
class Solution {
    /**
     * 深度优先拓扑排序（有向无环图）//这个代码可以改成循环体的形式，不用递归
     * 1.根据输入边先构建邻接矩阵形式的图的表示
     * 2.利用visited数组记录每个点的入度信息
     * 3.第一次遍历visited数组判断是否有入度为0的可以访问
     * 4.有入度为0的可以访问的进入dfs递归调用
     *      1）每次递归主要先将当前的访问点置为-1表示访问过（入度为0）
     *      2）根据建立的图信息去将当前访问点 出度所连接的点 的 入度减1
     *      3）遍历visited数组判断经过遍历有没有可以递归调用的
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] visited=new int[numCourses];
        int[][] grid=new int[numCourses][numCourses];
        int len=prerequisites.length;
        for(int i=0;i<len;i++){
            //先修课程到后一门课程的边
            grid[prerequisites[i][1]][prerequisites[i][0]]=1;
            visited[prerequisites[i][0]]+=1;//增加入度
        }
        for(int i=0;i<numCourses;i++){
            if(visited[i]==0){//刚开始入度为0才可以访问
                dfs(grid,visited,i);
            }
        }
        int count=0;
        for(int i=0;i<numCourses;i++){
            if(visited[i]<0){
                count++;
            }
        }
        return count==numCourses;
    }

    public void dfs(int[][] grid,int[] visited,int index){
        //每次访问将访问过的点出度减1，并将访问的点设置为-1访问过
        visited[index]=-1;
        for(int i=0;i<visited.length;i++){
            if(grid[index][i]>0){//访问的点出度点的入度减1
                visited[i]-=1;
            }
        }
        for(int i=0;i<visited.length;i++){
            if(visited[i]==0){//入度为0才可以访问
                dfs(grid,visited,i);
            }
        }
    }
}