/**
杨辉三角
定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
**/
class Solution {
	//去除头尾，去除第一行的情况，直接加上一行的当前位置前一项与本项
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        for(int i=0;i<numRows;i++){
            List<Integer> row=new ArrayList<Integer>();
            if(i==0){
                row.add(1);
            }else{
                for(int j=0;j<=i;j++){
                    if(j==0||j==i){
                        row.add(1);//去除两边
                    }else{
                        row.add(result.get(i-1).get(j-1)+result.get(i-1).get(j));
                    }
                }
            }
            result.add(row);
        }
        return result;
    }
}