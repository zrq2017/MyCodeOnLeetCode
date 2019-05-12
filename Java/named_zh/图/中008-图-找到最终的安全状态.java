/*****
找到最终的安全状态

在有向图中, 我们从某个节点和每个转向处开始, 沿着图的有向边走。 如果我们到达的节点是终点 (即它没有连出的有向边), 我们停止。

现在, 如果我们最后能走到终点，那么我们的起始节点是最终安全的。 更具体地说, 存在一个自然数 K,  无论选择从哪里开始行走, 我们走了不到 K 步后必能停止在一个终点。

哪些节点最终是安全的？ 结果返回一个有序的数组。

该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是 graph 的节点数.  图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i, j) 是图的一条有向边。

示例：
输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
输出：[2,4,5,6]
这里是上图的示意图。

Illustration of graph

提示：

graph 节点数不超过 10000.
图的边数不会超过 32000.
每个 graph[i] 被排序为不同的整数列表， 在区间 [0, graph.length - 1] 中选取。
**/
/**
* 主体思路：以DFS判断是否有环。//DFS一直往前走，却走回了走过的结点不就是有环了嘛
* 过关关键：int[] flag用来保存DFS过程中得到的结果，以免再次计算—— flag[i]==0表示i结点状态待定，
* flag[i]==1表示无环,flag[i]==-1表示有环。//你知道i结点在环内了，还再拿i结点往下DFS不是浪费时间嘛
**/
class Solution {
    int[] flag;//设置是否在环中的标志
    boolean[] visited;
    //遍历每个点使用dfs判断会不会有安全状态（即找到没有环的状态）
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //用找环来解
        int N=graph.length;
        flag=new int[N];//设置是否在环中的标志
        visited=new boolean[N];//保存访问过的数组
        ArrayList result=new ArrayList<>();//保存最终的安全点序列
        for(int i=0;i<N;i++){
            Arrays.fill(visited,false);
            if(dfs(graph,i)){//判断每个点是否是安全点加入结果列表
                result.add(i);
            }
        }
        return result;
    }

    // flag[i]==0表示i结点状态待定，flag[i]==1表示无环,flag[i]==-1表示有环。
    //一个点递归后要么最终找到的那个点是没有连接点的或是访问过的点，代表了无环和有环的状态
    private boolean dfs(int[][] graph, int i) {
        if(flag[i]==1){
            return true;//返回true代表无环
        }
        if(flag[i]==-1){
            return false;//代表有环
        }
        if(visited[i]){//递归遍历》》有环《《出口：点已经访问过的话那就是有环
            flag[i]=-1;
            return false;
        }
        visited[i]=true;//设置访问的标志：一开始假设会访问这个点，因为不知道这个点的下一步是不是安全的
        for(int j:graph[i]){//取出对应点的连接点，遍历连接点看有没有无环的出口:都是有环的出口，那么递归最后都会在循环体内返回false
            if(!dfs(graph,j)){//最后返回false代表有环
                flag[j]=-1;
                return false;
            }
        }//当前点最后有一条无环的出口没有环，不重新设置visited标志也没影响
//        visited[i]=false;//递归遍历》》无环《《出口：点没有连接点或最后能找到无环的节点
        flag[i]=1;//更新当前点拥有无环标志
        return true;
    }
}