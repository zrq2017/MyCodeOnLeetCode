/*****
网络延迟时间

有 N 个网络节点，标记为 1 到 N。

给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。

现在，我们向当前的节点 K 发送了一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。

注意:

N 的范围在 [1, 100] 之间。
K 的范围在 [1, N] 之间。
times 的长度在 [1, 6000] 之间。
所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。
**/
/**
* 思路分析：
* 首先我们应该明白，从k传输到所有的的时间 = max （从k到点1所需要的最少时间，
* 从k到2所需要的最少时间 … 从k到n所需要的最少时间），因为传输的过程是同时的。
* 所以这道题就转换为图的最短路径求解问题。
* 请翻阅 图的最短路径：Floyd、DisjKstra、SPFA算法
* 
* 我们先使用DisjKstra算法求出各个点到k的最短距离，然后求出这些距离中最大值。
**/
//DisjKstra算法
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int len=N+1,count=0;
        ArrayDeque<Integer> myQue=new ArrayDeque<>();//该队列用于即将访问的节点
        int[] distToK=new int[len];//distToK[index]表示点K到点index的最短距离
        for(int i=1;i<len;i++){
            distToK[i]=Integer.MAX_VALUE;
        }
        int[][] graph=new int[len][len];//邻接矩阵
        for(int i=0;i<len;i++){//因为输入的图时间w存在0值所以要将图的数据初始化为-1
            for (int j=0;j<len;j++){
                graph[i][j]=-1;
            }
        }
        for(int i=0;i<times.length;i++){
            graph[times[i][0]][times[i][1]]=times[i][2];//保存边的时间值
        }
        myQue.push(K);//myQue.addLast(K);
        distToK[K] = 0;//K到自己的最短距离为0
        //开始搜索各个点到k的最短距离
        while(!myQue.isEmpty()){
            int front = myQue.getFirst();
            myQue.pop();
            //利用当前front节点，尝试稀疏点k到所有节点的最短距离
            for(int target = 1; target <len; target++){
                if(graph[front][target] != -1 && distToK[front] + graph[front][target] < distToK[target]){
                    //如果front到target有边，并且点k到front的距离distToK[front] + 点front到target距离graph[front][target]小于点k到target的距离distToK[target]
                    distToK[target] = distToK[front] + graph[front][target];//则进行稀疏
                    myQue.push(target);//放入队列
                }
            }
        }
        //寻找点k到各个点的最短距离的最大值
        int maxRes = 0;
        for(int i = 1; i <len; ++i){
            maxRes = Math.max(maxRes, distToK[i]);
        }
        return maxRes == Integer.MAX_VALUE? -1 : maxRes;
    }
}
//Floyd-Warshall 算法
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        /*
        本质上是求单点到所有其他点的最短距离，最后求最短距离中的最大值。
        使用Floyd-Warshall 算法求解。
        */
        K--;  //输入中节点编号是基1，故减1
        int[] dis = new int[N];  //存储K到每个点的最短距离
        for(int i=0; i<N; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        dis[K] = 0;
        for(int i=0; i<N; i++){
            for(int[] edge: times){
                //输入中节点编号是基1，故减1
                int u = edge[0]-1, v = edge[1]-1, w = edge[2];
                if(dis[u] != Integer.MAX_VALUE && dis[v] > dis[u]+w)
                    dis[v] = dis[u] + w;
            }
        }
        //求最短距离中的最大值
        int res = 0;
        for(int i=0; i<N; i++){
            res = Math.max(res, dis[i]);
        }
        return res == Integer.MAX_VALUE? -1: res;        
    }
}
