/*****
划分字母区间

字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

示例 1:

输入: S = "ababcbacadefegdehijhklij"
输出: [9,7,8]
解释:
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
注意:

S的长度在[1, 500]之间。
S只包含小写字母'a'到'z'。
**/
class Solution {
    public List<Integer> partitionLabels(String S) {
        //先把每个字母的最大索引存起来，然后去判断每一个的交叉范围内的字母可以到达的最大索引范围
        int[] lastIndex=new int[26];
        for(int i=0;i<S.length();i++){
            lastIndex[convertCharToInt(S.charAt(i))]=i;
        }
        int first=0;
        List<Integer> list=new ArrayList<>();
        while(first<S.length()){
            int last=first;
            for(int i=first;first<S.length()&&i<=last;i++){//last控制交叉的最大范围
                int index=lastIndex[convertCharToInt(S.charAt(i))];
                if(index>last){
                    last=index;
                }
            }
            list.add(last-first+1);
            first=last+1;
        }
        return list;
    }
    
    public int convertCharToInt(char c){
        return c-'a';
    }
}