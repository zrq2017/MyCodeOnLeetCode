/*****
冗余连接

在本问题中, 树指的是一个连通且无环的无向图。

输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。

返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。

示例 1：

输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的无向图为:
  1
 / \
2 - 3
示例 2：

输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
输出: [1,4]
解释: 给定的无向图为:
5 - 1 - 2
    |   |
    4 - 3
注意:

输入的二维数组大小在 3 到 1000。
二维数组中的整数在1到N之间，其中N是输入数组的大小。
更新(2017-09-26):
我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
**/
class Solution {
    /**
     * 并查集：遍历判断当前两点是否已经连通，已经连通遍历到的两个点的边就是冗余的
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        UnionGrid unionGrid=new UnionGrid();
        int[] result=new int[2];
        for(int i=0;i<edges.length;i++){
            if(unionGrid.find(edges[i][0])!=unionGrid.find(edges[i][1])){//两个点还没连通在一起，代表这两个点的边不多余，合并
                unionGrid.merge(edges[i][0],edges[i][1]);
            }else{//两条边父节点相同，已经连通，这条边冗余，置为结果
                result[0]=edges[i][0];
                result[1]=edges[i][1];
            }
        }
        return result;
    }

    private class UnionGrid{
        int[] parent;

        UnionGrid(){//初始化父节点数组，点序号从1开始
            parent=new int[1001];
            for(int i=1;i<parent.length;i++){
                parent[i]=i;
            }
        }

        int find(int index){//查找父节点
            while(index!=parent[index]){
                index=parent[index];//父节点一定等于自身
            }
            return index;
        }

        void merge(int p,int q){//合并两个节点
            int proot=find(p);
            int qroot=find(q);
            parent[proot]=qroot;
        }
    }
}