package algorithm;

import java.util.Stack;

public class IsValid {
    /**
     * 有效的括号
     * <p>
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * 示例 1:
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     * 输入: "{[]}"
     * 输出: true
     * @param args
     */
    public static void main(String[] args) {
        String s = "()[]{}";
        isValid(s);
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            char tmp = s.charAt(i);
            if (stack.isEmpty() || tmp == '(' || tmp == '[' || tmp == '{') {
                stack.push(tmp);//入栈的情况
            } else {
                char top = stack.peek();
                switch (top) {
                    case '(':
                        if (tmp == ')') {
                            stack.pop();
                            break;
                        } else {
                            return false;
                        }
                    case '[':
                        if (tmp == ']') {
                            stack.pop();
                            break;
                        } else {
                            return false;
                        }
                    case '{':
                        if (tmp == '}') {
                            stack.pop();
                            break;
                        } else {
                            return false;
                        }
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
