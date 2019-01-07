/**
杨辉三角
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 3
输出: [1,3,3,1]

推荐方法一
方法二：二项式定理，容易溢出，回答不正确
**/
class Solution {
	//方法一：要哪一行直接输出那一行
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> list=new ArrayList<Integer>();

        for(int i=0;i<=rowIndex;i++){//第一层先做初始值的赋予
            list.add(1);//索引为0
            for(int j=i-1;j>=1;j--){//j-1与>=1是做头尾元素为1的去除
                list.set(j, list.get(j)+list.get(j-1) );
            }
        }
        return list;
    }
	//方法二：二项式定理，容易溢出，回答不正确
	public List<Integer> getRow2(int rowIndex) {
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        for(int i=1;i<=rowIndex;i++){
            list.add(list.get(i-1)*(rowIndex-i+1)/i);
        }
        return list;
    }
}