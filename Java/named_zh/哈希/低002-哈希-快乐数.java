/*****
快乐数

编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 

输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
**/
class Solution {
    //内层循环判断一次n值的sum计算结果，外层判断是否为满足条件的快乐数，用list判断元素是否出现
    public boolean isHappy(int n) {
        if(n<=0)
            return false;
        ArrayList<Integer> arrayList=new ArrayList<>();
        int sum=0;
        while(sum!=1)
        {
            sum=0;
            while(n>0)
            {
                sum+=(n%10)*(n%10);
                n=n/10;
            }
            if(arrayList.contains(sum))
                return false;
            arrayList.add(sum);
            n=sum;

        }
        return true;
    }
}