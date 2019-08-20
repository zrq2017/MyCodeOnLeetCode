/*****
用最少数量的箭引爆气球

在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。

一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

Example:

输入:
[[10,16], [2,8], [1,6], [7,12]]

输出:
2

解释:
对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
**/
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length==0) return 0;
        //根据末尾的顺序排序
        //Arrays.sort(points,Comparator.comparingInt(o->o[1]));
        quickSort(points,0,points.length-1);
        int end=points[0][1],out=0;
        for(int i=1;i<points.length;i++){
            if(points[i][0]<=end){
                out++;//找出重叠的区域
                continue;
            }
            end=points[i][1];
        }
        return points.length-out;//使用总的区间数减去重叠区域
    }
    
    public void quickSort(int[][] points,int left,int right){
        int i=left,j=right;
        if(left>right) return;
        int base=points[left][1],base0=points[left][0];
        while(i!=j){
            while(points[j][1]>base&&i<j){
                j--;
            }
            while(points[i][1]<=base&&i<j){
                i++;
            }
            if(i<j){
                int x=points[i][0],y=points[i][1];
                points[i][0]=points[j][0];points[i][1]=points[j][1];
                points[j][0]=x;points[j][1]=y;
            }
        }
        points[left][0]=points[i][0];points[left][1]=points[i][1];
        points[i][0]=base0;points[i][1]=base;
        quickSort(points,left,i-1);
        quickSort(points,i+1,right);
    }
}