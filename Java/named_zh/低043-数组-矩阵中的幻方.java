/**
�����еĻ÷�

3 x 3 �Ļ÷���һ������д� 1 �� 9 �Ĳ�ͬ���ֵ� 3 x 3 ��������ÿ�У�ÿ���Լ������Խ����ϵĸ���֮�Ͷ���ȡ�

����һ����������ɵ� N �� N ���������ж��ٸ� 3 �� 3 �� ���÷��� �Ӿ��󣿣�ÿ���Ӿ����������ģ���

 

ʾ�� 1:

����: [[4,3,8,4],
      [9,5,1,9],
      [2,7,6,2]]
���: 1
����: 
������Ӿ�����һ�� 3 x 3 �Ļ÷���
438
951
276

����һ�����ǣ�
384
519
762

�ܵ���˵���ڱ�ʾ���������ľ�����ֻ��һ�� 3 x 3 �Ļ÷��Ӿ���
��ʾ:

1 <= grid.length = grid[0].length <= 10
0 <= grid[i][j] <= 15

**/
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res=0;
        for(int i=0;i+2<n;i++) {
           for(int j=0;j+2<m;j++){
                res+=judge(i,j,grid);
            }
        }
        return res;
    }
   private int judge(int x,int y,int [][] g){
        int[] a = new int[10];
       //�ж��Ƿ���1-9�����ݣ��Ƿ�Ψһ
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(g[i][j]>=10 || g[i][j]<=0) return 0;
                a[g[i][j]]++;
                if( a[g[i][j]]>1) return 0;
            }
        }
       //ÿһ��
        int sum =-1,tmp=0;
        for(int i=x;i<x+3;i++){
            tmp=0;
            for(int j=y;j<y+3;j++){
                tmp+=g[i][j];
            }
            if(sum==-1) sum=tmp;
            else if(sum!=tmp) return 0;
        }
       //ÿһ��
        for(int j=y;j<y+3;j++){
            tmp=0;
            for(int i=x;i<x+3;i++){
                tmp+=g[i][j];
            }
            if(sum!=tmp) return 0;
        }
       //���Խ���
        tmp = g[x][y]+g[x+1][y+1]+g[x+2][y+2];
        if(sum!=tmp) return 0;
       //���Խ���
        tmp = g[x][y+2]+g[x+1][y+1]+g[x+2][y];
        if(sum!=tmp) return 0;
        return 1;
    }
}