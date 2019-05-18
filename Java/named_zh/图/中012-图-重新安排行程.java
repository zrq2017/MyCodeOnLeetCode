package algorithm;

import java.util.*;

public class FindItinerary {
    /**
     * 重新安排行程
     *
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
     * 所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
     *
     * 说明:
     *
     * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * 示例 1:
     *
     * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * 示例 2:
     *
     * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     * @param args
     */
    public static void main(String[] args) {
        List<List<String>> tickets=new ArrayList<>();
        String[][] list={{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
//        String[][] list={{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        for(String[] s:list){
            tickets.add(Arrays.asList(s));
        }
        findItinerary(tickets);
    }

    private static Map<String,PriorityQueue<String>> map=new HashMap<>();//保存节点图
    private static LinkedList<String> result=new LinkedList<>();//保存结果
    public static List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> list:tickets){//使用map建图
            if(!map.containsKey(list.get(0))) {
                map.put(list.get(0), new PriorityQueue<>());//若节点没有队列，新建队列
            }
            map.get(list.get(0)).add(list.get(1));//将边添加到队列中
        }
        dfs("JFK");
        return result;
    }

    private static void dfs(String str) {
        PriorityQueue<String> queue=map.get(str);
        while(queue!=null && !queue.isEmpty()){//一直会进入循环，所以先访问的反而暂时不会保存到结果中，后访问的先入结果
            dfs(queue.poll());
        }
        result.addFirst(str);//后面添加的反而是之前的所以一直加在队头
    }
}
