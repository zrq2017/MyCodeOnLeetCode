package algorithm;

import java.util.Arrays;

public class MinStack {
    /**
     * 最小栈
     *
     * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * push(x) -- 将元素 x 推入栈中。
     * pop() -- 删除栈顶的元素。
     * top() -- 获取栈顶元素。
     * getMin() -- 检索栈中的最小元素。
     * 示例:
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     */

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        System.out.println(minStack.top());      //--> 返回 2.
        System.out.println(minStack.getMin());//--> 返回 1.
        minStack.pop();
        System.out.println(minStack.getMin());//--> 返回 1.
        System.out.println(minStack.top());// --> 返回 1.
    }

    /** initialize your data structure here. */
    int[] stack;//保存栈元素
    int min;//保存最小元素
    int top;//栈顶指针
    int LEN=10;//默认增长长度

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    public MinStack() {
        stack=new int[LEN];
        min=Integer.MAX_VALUE;
        top=0;
    }

    public void push(int x) {
        if(top>=stack.length){//扩容
            int[] temp=Arrays.copyOf(stack,stack.length+LEN);//注意这个的用法，差点被这点搞死，是传入》旧数组与新数组长度《获得拷贝的新数组
            stack=temp;
        }
        stack[top++]=x;//赋值
        if(x<min){//更新最小值
            min=x;
        }
    }

    public void pop() {
        if(top<=0) return;
        top--;
        if(stack[top]==min){
            min=Integer.MAX_VALUE;
            for(int i=0;i<top;i++){//栈顶是最小元素，遍历查找新的最小元素
                if(stack[i]<min){
                    min=stack[i];
                }
            }
        }
    }

    public int top() {
        return stack[top-1];
    }

    public int getMin() {
        return min;
    }
}

//使用Java的Stack实现
class MinStack {
    Stack<Integer> stack;//保存栈元素
    public MinStack() {
        stack = new Stack<Integer>();
    }

    public void push(int x) {
        if(stack.isEmpty()){
            stack.push(x);
            stack.push(x);
        }else{
            int tmp = stack.peek();
            stack.push(x);
            if(tmp<x){
                stack.push(tmp);
            }else{
                stack.push(x);
            }
        }
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        return stack.get(stack.size()-2);
    }

    public int getMin() {
        return stack.peek();
    }
}