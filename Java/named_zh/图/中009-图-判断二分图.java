/*****
判断二分图

给定一个无向图graph，当这个图为二分图时返回true。

如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。

graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。


示例 1:
输入: [[1,3], [0,2], [1,3], [0,2]]
输出: true
解释: 
无向图如下:
0----1
|    |
|    |
3----2
我们可以将节点分成两组: {0, 2} 和 {1, 3}。

示例 2:
输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
输出: false
解释: 
无向图如下:
0----1
| \  |
|  \ |
3----2
我们不能将节点分割成两个独立的子集。
注意:

graph 的长度范围为 [1, 100]。
graph[i] 中的元素的范围为 [0, graph.length - 1]。
graph[i] 不会包含 i 或者有重复的值。
图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
**/
/**
* 主体思路：以DFS判断是否有环。//DFS一直往前走，却走回了走过的结点不就是有环了嘛
* 过关关键：int[] flag用来保存DFS过程中得到的结果，以免再次计算—— flag[i]==0表示i结点状态待定，
* flag[i]==1表示无环,flag[i]==-1表示有环。//你知道i结点在环内了，还再拿i结点往下DFS不是浪费时间嘛
**/
class Solution {
    int[] colorArr;//用于记录颜色

    /**
     * dfs求解：原理是两个相邻节点其颜色必然不一样，如果一样，则不是二分图
     * 1.使用颜色数组记录每个访问过的点的颜色
     * 2.（外层循环）每次按点去做深度优先
     *      1）重新更新访问数组的标记
     *      2）若是该点已经着色那就按该颜色来，初始设置没访问的点为1
     *      3）遍历当前点的连接点，只要有一个不符合期望颜色那就返回false不安全
     * 3.（递归循环）每次深度优先访问循环，若是当前循环该点
     *      1）访问过就比较传入点与期望设置的颜色是否一致，一致就返回true，代表安全
     *      2）没访问过：
     *          a.设置访问标记，根据颜色标志数组判断是否被之前的循环访问过且与期望颜色一致，不一致就返回不安全
     *          b.设置当前点为期望颜色，遍历该点的连接点，看是否都与期望点颜色一样，只要有一个不一样就返回false不安全
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        boolean[] visited=new boolean[graph.length];//用于记录每次循环该点访问过没有
        colorArr=new int[graph.length];
        for(int i=0;i<graph.length;i++){
            Arrays.fill(visited,false);//设置都没访问过
            int color=1;
            if(colorArr[i]!=0){
                color=colorArr[i];//记录当前点color值
            }
            if(!dfs(graph,visited,i,color)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph,boolean[] visited,int index,int color){
        if(!visited[index]){//当前点还没访问
            visited[index]=true;
            if(colorArr[index]!=0 && colorArr[index]!=color){
                return false;//当前点颜色不一样
            }
            colorArr[index]=color;//记录当前颜色
            for(int i:graph[index]){
                if(!dfs(graph,visited,i,-color)){
                    return false;//只要有重复的颜色就返回false
                }
            }
            return true;
        }
        return colorArr[index]==color;//当前点访问过了，比较两者的颜色一致不
    }
}