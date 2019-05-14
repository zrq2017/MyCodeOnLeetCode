/*****
最小高度树

对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式

该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。

你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。

示例 1:

输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

输出: [1]
示例 2:

输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

输出: [3, 4]
说明:

 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
**/
class Solution {
    /**
     * 暴力破解，求每个节点的树的高度，再求出最小的；问题会出现超时问题
     * 剪枝：先将入度为1的节点入队，按队列遍历后去掉该节点，将入度为1的节点入队，直到节点数只剩2为止；
     *      不超过3个。为什么是3个呢？举个例子，假设一个图有两个点，用一条边连起来，那么返回的结果就是这两个点。
     *      但如果图中有三个点，用两条边连起来，那么返回的结果就是中间的那一个点
     *      
     * 这题采用剪枝
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        List<Integer> leaves = new ArrayList<>();
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < n; ++i) {
            if (adj.get(i).size() == 1) leaves.add(i);
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int t = adj.get(i).iterator().next();
                adj.get(t).remove(i);
                if (adj.get(t).size() == 1) newLeaves.add(t);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
	
	//自己写的超出时间限制
	 public List<Integer> findMinHeightTreesByMe(int n, int[][] edges) {
        if(n==1) return Collections.singletonList(0);
        int[][] grid=new int[n][n];
        int[] in=new int[n];
        for(int[] edge:edges){
            grid[edge[0]][edge[1]]=1;
            grid[edge[1]][edge[0]]=1;
            in[edge[0]]+=1;//使用两个点都加的原因是，若想当成有向边使用会发现可能出现有点没有入度的情况
            in[edge[1]]+=1;
        }
        ArrayDeque<Integer> deque=new ArrayDeque<>();
        int count=n;//记录还剩下的节点数
        for(int i=0;i<n;i++){
            if (in[i]==1){
                deque.add(i);//入度为1的叶子节点入队
            }
        }
        while(count>2){
            int size=deque.size();
            count-=size;//减小数量
            for(int i=0;i<size;i++){//根据每层有的叶子节点出队
                int index=deque.pop();//弹出节点
                in[index]-=1;//清空连接边
                for(int node:grid[index]) {//获取当前叶子结点的连接点
                    if (node != 0) {//不为0代表有连接边
                        in[node] -= 1;//将连接边减1
                        if (in[node] == 1) {//判断是否成为叶子节点，是的话入队
                            deque.add(node);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> result=new ArrayList<>();
        while(!deque.isEmpty()){//最后剩下的节点都在队内，由于count=2的特性保证了两个点的边只会是1，或者剩一个点
            int index=deque.pop();
            result.add(index);
            in[index]=0;
        }
        for(int i=0;i<n;i++){
            if(in[i]>0){
                result.add(i);
            }
        }
        return result;
    }
}