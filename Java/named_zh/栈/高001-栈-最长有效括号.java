 /**
     * 最长有效括号
     
	 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

	示例 1:

	输入: "(()"
	输出: 2
	解释: 最长有效括号子串为 "()"
	示例 2:

	输入: ")()())"
	输出: 4
	解释: 最长有效括号子串为 "()()"

*/
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack=new Stack<>();
        stack.push(-1);
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);//只入左括号
            }else{
                stack.pop();//碰到右括号出栈
                if(stack.isEmpty()){
                    stack.push(i);//栈空记录最左指针
                }else{
                    max=Math.max(max,i-stack.peek());//栈顶就是有效字符的开始位置，长度为当前-栈顶索引
                }
            }
        }
        return max;
    }
}