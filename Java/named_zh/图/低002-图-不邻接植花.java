/*****
不邻接植花

有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。

paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。

另外，没有花园有 3 条以上的路径可以进入或者离开。

你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。

以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。

 

示例 1：

输入：N = 3, paths = [[1,2],[2,3],[3,1]]
输出：[1,2,3]
示例 2：

输入：N = 4, paths = [[1,2],[3,4]]
输出：[1,2,1,2]
示例 3：

输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
输出：[1,2,3,4]
 

提示：

1 <= N <= 10000
0 <= paths.size <= 20000
不存在花园有 4 条或者更多路径可以进入或离开。
保证存在答案。
**/
//网上的代码，通过：http://www.bubuko.com/infodetail-3057394.html
class Solution {
    /**
     * 使用贪心：
     * idea: 对每一个花园依次进行染色时，必定可以直接染色，因为相临接的最多最多就是三个，一共4中颜色，所以一定可以直接染色了
     *
     * 具体想法：
     * 每次对节点染色时，新建一个colors数组，colors[j] , 代表节点i旁边临接的节点赋值的颜色是否有，如果有，就赋值为1 然后遍历，colors,给节点i进行染色
     */
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> G = new HashMap<>();
        for (int i = 0; i < N; i ++)     G.put(i, new HashSet<>());
        for (int[] p: paths){
            G.get(p[0] - 1).add(p[1] - 1);
            G.get(p[1] - 1).add(p[0] - 1);
        }
        int[] res = new int[N];
        for (int i = 0; i < N ; i++){
            int[] colors = new int[5];
            for (int j : G.get(i))
                colors[res[j]] = 1;
            for (int c = 4; c > 0; --c)
                if (colors[c] == 0)
                    res[i] = c;
        }
        return res;
    }
   
}

//调整结构使用遍历，执行出错
class Solution {
    class Node{
        int index;//保存点序号
        int type;//保存选择
        Node next;
        Node(int index){
            this.index=index;
        }
    }
    public int[] gardenNoAdj(int N, int[][] paths) {
        int len=N+1;
        Node[] nodes=new Node[len];//保存图
        for(int i=1;i<len;i++){
            nodes[i]=new Node(i);//初始化头节点
        }
        Integer[] type={1,2,3,4};//保存选择类型
        for(int i=0,pLen=paths.length;i<pLen;i++){
            Node t1=nodes[paths[i][0]].next,t2=nodes[paths[i][1]].next;
            Node a1=new Node(paths[i][1]),a2=new Node(paths[i][0]);
            nodes[paths[i][0]].next=a1;//源点边
            nodes[paths[i][1]].next=a2;//终点边
            a1.next=t1;
            a2.next=t2;
        }
        for(int i=1;i<len;i++){
            if(nodes[i].type==0){//点没访问过
                Set<Integer> set=new HashSet<>(Arrays.asList(type));//存储选择
                Node next=nodes[i].next;
                while(next!=null){//移除连接的点访问过的设置好选择的
                    if(nodes[next.index].type!=0){
                        set.remove(nodes[next.index].type);
                    }
                    next=next.next;
                }
                int v=set.iterator().next();//初始设置选择
                nodes[i].type=v;
                set.remove(v);
                next=nodes[i].next;
                while(next!=null) {//访问所有连接的边，且点没访问过
                    if(next.type==0) {
                        int c = set.iterator().next();
                        nodes[next.index].type = c;
                        set.remove(c);
                    }
                    next=next.next;
                }
            }
        }
        int[] answer=new int[N];
        for (int i=1;i<len;i++){
            answer[i-1]=nodes[i].type;
        }
        return answer;
    }
   
}

//dfs,超时
class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int len=N+1;
        answer=new int[len];//保存每个点的选择
        int[][] grid=new int[len][len];//保存图
        Integer[] type={1,2,3,4};
        for(int i=0,pLen=paths.length;i<pLen;i++){
            grid[paths[i][0]][paths[i][1]]=1;
            grid[paths[i][1]][paths[i][0]]=1;//以边建图
        }
        for(int i=1;i<len;i++){
            if(answer[i]==0){//点没访问过
                answer[i]=1;//初始设置选择
                for(int j=1;j<len;j++) {//访问所有连接的边，且点没访问过
                    if(grid[i][j]!=0 && answer[j]==0)
                        dfs(grid, i, j);
                }
            }
        }
        return Arrays.copyOfRange(answer,1,len);//返回选择列表，节点序号从1开始的
    }
    Integer[] type={1,2,3,4};//保存选择类型
    int[] answer;//保存每个点的选择
    public void dfs(int[][] grid,int source,int dest){
        Set<Integer> set=new HashSet<>(Arrays.asList(type));//存储剩下的选择
        set.remove(answer[source]);//移除相邻的源点选择
        for(int i=1;i<grid.length;i++){//深度优先做修改
            if(grid[dest][i]!=0 && source!=dest && answer[i]!=0){//有边且不等于源点且访问过
                set.remove(answer[i]);//移除相邻的访问过的点的选择
            }
        }
        if(answer[dest]==0|| answer[source]==answer[dest]){//等于0代表没被访问过，或与当前源点选择相同，使用剩下的选择替换
            int v=set.iterator().next();
            answer[dest]=v;
            set.remove(v);
        }
        for(int i=1;i<grid.length;i++){//深度优先做修改
            if(grid[dest][i]!=0 && source!=dest && answer[i]==0){//有边且不等于源点，且没访问过
                dfs(grid,dest,i);
            }
        }
    }
}

//暴力破解：遍历每个点选择与其相邻边的点不同的选择，超时
public int[] gardenNoAdjViolence(int N, int[][] paths) {
	int len=N+1;
	int[] answer=new int[len];//保存每个点的选择
	int[][] grid=new int[len][len];//保存图
	Integer[] type={1,2,3,4};//保存选择类型
	for(int i=0,pLen=paths.length;i<pLen;i++){
		grid[paths[i][0]][paths[i][1]]=1;
		grid[paths[i][1]][paths[i][0]]=1;//以边建图
	}
	for(int i=1;i<len;i++){
		Set<Integer> set=new HashSet<>(Arrays.asList(type));//存储剩下的选择
		if(answer[i]==0) answer[i]=1;//初始时设置花的种类
		set.remove(answer[i]);
		for(int j=1;j<len;j++){
			if(grid[i][j]!=0){//有边
				if(answer[j]==0 || answer[j]==answer[i]){//等于0代表没被访问过，或与当前源点选择相同，使用剩下的选择替换
					int v=set.iterator().next();
					answer[j]=v;
					set.remove(v);
				}
			}
		}
	}
	return Arrays.copyOfRange(answer,1,len);//返回选择列表，节点序号从1开始的
}