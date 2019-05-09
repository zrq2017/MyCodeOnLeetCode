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
	//练习的DisjKstra算法
	public int networkDelayTimeDisjKstra(int[][] times,int N,int K){
        int len=N+1;
        int[][] grid=new int [len][len];
        int[] dist=new int[len];
        for(int i=1;i<len;i++){
            dist[i]=Integer.MAX_VALUE;//初始化每个点的最大距离
        }
        for(int i=1;i<len;i++){//点从1-N
            for(int j=1;j<len;j++){
                grid[i][j]=-1;//初始化图的邻接矩阵空间
            }
        }
        for(int i=0;i<times.length;i++){
            grid[times[i][0]][times[i][1]]=times[i][2];//根据边为图赋值
        }
        dist[K]=0;//初始时的点距离为0
        ArrayDeque<Integer> deque=new ArrayDeque<>();//设置出点的队列
        deque.add(K);//起始点入队
        while(!deque.isEmpty()){//当队列不空时更新该点能到达的边的距离
            int source=deque.getFirst();
            for(int i=1;i<len;i++){
                if(grid[source][i]!=-1 && grid[source][i]+dist[source]<dist[i]){//不等于-1代表两点有边，且由源点到终点的距离更小
                    dist[i]=grid[source][i]+dist[source];
                    deque.add(i);
                }
            }
            deque.pop();
        }
        int max=0;//记录要花的时间，最大值为Integer.MAX_VALUE代表有点没访问过
        for(int i=1;i<len;i++){
            max=Math.max(max,dist[i]);
        }
        return max==Integer.MAX_VALUE?-1:max;
    }
}
//Floyd-Warshall 算法
class Solution {
    /**
     * 弗洛伊德算法Floyd-Warshall
     * 每个i-j的点经过k，然后从1-N比较是否Di,j>Di,k+Dk,j 即经过k点使得i-j距离更小那就更新最小距离
     *  for k from 1 to |V|
     *     for i from 1 to |V|
     *        for j from 1 to |V|
     *           if dist[i][j] > dist[i][k] + dist[k][j]
     *              dist[i][j] ← dist[i][k] + dist[k][j]
     *          end if
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        /*
        本质上是求单点到所有其他点的最短距离，最后求最短距离中的最大值。
        使用Floyd-Warshall 算法求解。
        */
		int len=N+1;
        int[] dist=new int[len];
        for(int i=1;i<len;i++){
            dist[i]=Integer.MAX_VALUE;//每个点到达的最短距离
        }
        dist[K]=0;//开始节点最短为0
        for(int i=1;i<len;i++){//点1-N
            for(int[] edge:times){
                int u=edge[0],v=edge[1],w=edge[2];
                if(dist[u]!=Integer.MAX_VALUE && dist[v]>dist[u]+w){//第一个不等于最大值的条件保证了由入点K开始更新最短距离，后面的思想就是相当于不断消耗edge边，就算下次碰上同样的边也不变，只有更小的边才会减小
                    //由源点u到终点v增加的距离比原来到终点v的距离更短
                    dist[v]=dist[u]+w;
                }
            }
        }
        int max=0;//判断最短距离，有没访问过的点那就是Integer.MAX_VALUE
        for(int i=1;i<len;i++){
            max=Math.max(max,dist[i]);
        }
        return max==Integer.MAX_VALUE?-1:max;
    }
}
