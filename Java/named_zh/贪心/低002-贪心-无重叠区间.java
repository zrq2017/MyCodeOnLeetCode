/*****
无重叠区间

给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

注意:

可以认为区间的终点总是大于它的起点。
区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
示例 1:

输入: [ [1,2], [2,3], [3,4], [1,3] ]

输出: 1

解释: 移除 [1,3] 后，剩下的区间没有重叠。
示例 2:

输入: [ [1,2], [1,2], [1,2] ]

输出: 2

解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
示例 3:

输入: [ [1,2], [2,3] ]

输出: 0

解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
**/
class Solution {
    //只要按照闭合区间排序，后面开始区间比它小的都是重合的
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length==0) return 0;
        Arrays.sort(intervals,Comparator.comparingInt(o->o[1]));//排序比较右闭区间大小，使用任意的排序方法都可以，交换数组元素
        int end=intervals[0][1],out=0,len=intervals.length;
        for(int i=1;i<len;i++){
            if(intervals[i][0]<end){//当前的开始区间小于上一个的闭合区间就说明重叠了
                out++;//记录当前要剔除的数
                continue;
            }
            end=intervals[i][1];//更新新的闭合区间
        }
        return out;
    }
}