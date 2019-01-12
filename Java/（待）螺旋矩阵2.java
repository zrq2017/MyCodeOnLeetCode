class Solution {
    public int[][] generateMatrix(int n) {
        int [][] temp=new int[n][n];
        int min=x=y=0,count=1,sum=n*n;
        while(count<=sum){
            while(y<n){//����
                temp[x][y]=count;
                y++;count++;
            }
            x++;y--;
            while(x<n){//����
                temp[x++][y]=count++;
            }
            x--;y--;
            while(y>=min){//����
                temp[x][y--]=count++;
            }
            y++;x--;
            while(x>min){//����
                temp[x--][y]=count++;
            }
            y++;x++;
            n--;min++;
        }
        return temp;
    }
}