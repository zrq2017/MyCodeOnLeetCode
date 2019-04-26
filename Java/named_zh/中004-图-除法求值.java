/*****
除法求值

给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

示例 :
给定 a / b = 2.0, b / c = 3.0
问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]

输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

基于上述例子，输入如下：

equations(方程式) = [ ["a", "b"], ["b", "c"] ],
values(方程式结果) = [2.0, 3.0],
queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果
**/
class Solution {
	/**
	*并查集实现
	*/
    private Map<String,String> fa = new HashMap<>();
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String,Double> value = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String u = equations[i][0];
            String v = equations[i][1];
            if(!fa.containsKey(u) && !fa.containsKey(v) ){//两者都不在
                // u -> v
                fa.put(u,u);//构建 除数-> 被除数 的边
                fa.put(v,u);
                value.put(v,1.0);//两者都在，假设除数为1
                value.put(u,values[i]);//被除数为v[i]值
            }else if(!fa.containsKey(u)){//u不存在，v存在
                fa.put(u,v);//连接到连通集中
                value.put(u, values[i] * value.get(v));//求u的值
            }else if(!fa.containsKey(v)){//u存在，v不存在
                fa.put(v,u);
                value.put(v,value.get(u)/values[i]);//求v的值
            }else {//u，v都在 需要merge
                union(u,v,value,values[i]);
            }
        }
        List<Double> res = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            String u = queries[i][0];
            String v = queries[i][1];
            if(!value.containsKey(u) || !value.containsKey(v)
                    ||getf(u)!=getf(v)){//两个点都不在值列表的点里，或者不能通过现有的边将两点连起
                res.add(-1.0);
            }
            else res.add(value.get(u)/value.get(v));
        }
        return res.stream().mapToDouble(Double::valueOf).toArray();

    }

    private String getf(String u) {
        if(fa.get(u) == u) return u;//判断能否到自己
        fa.put(u,getf(fa.get(u)));//递归查找能到达的终点(父节点)是哪一个
        return fa.get(u);//返回父节点
    }

    private void union(String u, String v, Map<String, Double> value, double value1) {
        String fu = fa.get(u);//获得除数对应的被除数，当前点能到达的最终点，有向边
        String fv = fa.get(v);

        double ra = value.get(v)*value1/value.get(u);//基础值
        for (String s : value.keySet()) {//判断有值的点列表每个点能到达的最终的边
            if(getf(s) ==fu){//值列表中的点通过已知边添加两个点能到达的边
                value.put(s,value.get(s)*ra);//原假设基础除数为1，更新基础值
            }
        }
        fa.put(fv,fu);//已知两点在边列表中，该两点的父节点也能构成边，合并父节点
    }
}