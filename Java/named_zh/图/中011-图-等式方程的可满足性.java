package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EquationsPossible {
    /**
     * 等式方程的可满足性
     *
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
     * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
     *
     * 示例 1：
     * 输入：["a==b","b!=a"]
     * 输出：false
     * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
     *
     * 示例 2：
     * 输出：["b==a","a==b"]
     * 输入：true
     * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
     *
     * 示例 3：
     * 输入：["a==b","b==c","a==c"]
     * 输出：true
     *
     * 示例 4：
     * 输入：["a==b","b!=c","c==a"]
     * 输出：false
     *
     * 示例 5：
     * 输入：["c==c","b==d","x!=z"]
     * 输出：true
     *
     * 提示：
     * 1 <= equations.length <= 500
     * equations[i].length == 4
     * equations[i][0] 和 equations[i][3] 是小写字母
     * equations[i][1] 要么是 '='，要么是 '!'
     * equations[i][2] 是 '='
     * @param args
     */
    public static void main(String[] args) {
        String[] equations={"a==b","b!=a"};
//        String[] equations={"b==a","a==b"};
//        String[] equations={"a==b","b==c","a==c"};
//        String[] equations={"c==c","b==d","x!=z"};
//        String[] equations={"a==b","b!=c","c==a"};
        equationsPossible(equations);
    }

    /**
     * 并查集
     * 1）使用map保存节点的父节点
     * 2）遍历将相等的式子的两个节点合并并记录不相等的式子的索引
     * 3）遍历判断不相等索引的父节点是否一致，只要有一个为一致则代表结果为false
     * @param equations
     * @return
     */
    public static boolean equationsPossible(String[] equations) {
        Union union=new Union(equations);
        ArrayList<Integer> unEqualIndex=new ArrayList();
        //==与!=两种情况就是父节点一致，与父节点不一致
        for(int i=0,len=equations.length;i<len;i++){
            if(equations[i].charAt(1)=='='){//相等就合并父节点
                union.merge(equations[i].charAt(0),equations[i].charAt(3));
            }else {//不等就记录数组索引
                unEqualIndex.add(i);
            }
        }
        boolean result=true;
        for(int index:unEqualIndex){
            if(union.isParentEqual(equations[index].charAt(0),equations[index].charAt(3))){//索引记录的等式是不等的，所以如果返回真，反而说明等式不成立
                result=false;
                break;
            }
        }
        return result;
    }

    static class Union{
        Map<Character,Character> parent;//保存节点的父节点
        Union(String[] equations){
            parent=new HashMap<>();
            for(String str:equations){//初始化节点列表，刚开始父节点都是自身
                parent.put(str.charAt(0),str.charAt(0));
                parent.put(str.charAt(3),str.charAt(3));
            }
        }

        public char findParent(char c){
            char parentChar=parent.get(c);
            while(parentChar!=parent.get(parentChar)){
                parentChar=parent.get(parentChar);//循环获取当前字符的父节点
            }
            return parentChar;
        }

        public void merge(char a,char b){//合并两个节点的父节点
            char pa=findParent(a);
            char pb=findParent(b);
            parent.put(pa,pb);
        }

        public boolean isParentEqual(char a,char b){//判断两个节点的父节点是否一致
            char pa=findParent(a);
            char pb=findParent(b);
            return pa==pb;
        }
    }
}
