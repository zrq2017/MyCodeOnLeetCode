/*****
分割回文串

给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
**/
class Solution {
    /**
     * 名称：分割回文串
     * 思想：1.将回文串按1-len进行加1的分割，将前i个的分割结果保存到dp(右边不断增大，s的子串fs在增大):[0],[0,1],[0,1,2]
     *      2.循环从1-(i+1)判断是否是回文串将结果保存在dp中(左边不断减小，fs的子串ss的长度在减小)[0,1,2],[1,2],[2]
     *      3.注意第二步是第一步的一个循环的步骤，上述步骤确保可以完整分割
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int len=s.length();
        List<List<String>> list=new ArrayList();
        if(len==0){
            return list;
        }
        List<String> initList=new ArrayList();
        char[] chars=s.toCharArray();
        initList.add(chars[0]+"");
        list.add(initList);
        List[] dp=new ArrayList[len];
        for(int i=1;i<len;i++){
            dp[i]=new ArrayList();
        }
        dp[0]=list;//dp数组存的是长度为i+1的分割后的回文串数组结果==dp[i]保存的是二维数组每行的元素为分割后长度为该行行号的回文串结果
        for(int i=1;i<len;i++){//i控制目前在s中分割到的字符串位置
            //首先在尾部直接添加
            doCopyAndRewiteList(dp[i],dp[i-1],chars[i]+"");
            //然后判断是否后面的能够组成新的回文
            for(int j=0;j<i;j++){//判断从0开始到目前分割到的字符串是否是回文串
                if(isPalim(chars,j,i)){
                    if(j==0){
                        List tmpList=new ArrayList();
                        tmpList.add(s.substring(0,i+1));
                        dp[i].add(tmpList);
                    }else{
                        doCopyAndRewiteList(dp[i],dp[j-1],s.substring(j,i+1));
                    }
                }
            }
        }
        return dp[len-1];
    }

    //拷贝前面的dp结果到当前
    public void doCopyAndRewiteList(List list1,List list2,String s){
        for(int i=0;i<list2.size();i++){
            List tmpList=new ArrayList();
            List tmpList2=(List)list2.get(i);
            for(int j=0;j<tmpList2.size();j++){
                tmpList.add(tmpList2.get(j));
            }
            tmpList.add(s);
            list1.add(tmpList);
        }
    }

    public boolean isPalim(char[] chars,int i,int j){
        while(i<j){//判断子序列（长度为j-i+1）是否是回文串
            if(chars[i++]!=chars[j--]){
                return false;
            }
        }
        return true;
    }
}