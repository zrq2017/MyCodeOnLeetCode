class Solution {
    public int[][] generateMatrix(int n) {
        int [][] temp=new int[n][n];
        int min=x=y=0,count=1,sum=n*n;
        while(count<=sum){
            while(y<n){//向右
                temp[x][y]=count;
                y++;count++;
            }
            x++;y--;
            while(x<n){//向下
                temp[x++][y]=count++;
            }
            x--;y--;
            while(y>=min){//向左
                temp[x][y--]=count++;
            }
            y++;x--;
            while(x>min){//向上
                temp[x--][y]=count++;
            }
            y++;x++;
            n--;min++;
        }
        return temp;
    }
}