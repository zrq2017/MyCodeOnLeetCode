package algorithm.summary;

import algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {
	/**
     * 二叉树的中序遍历
     *
     * 给定一个二叉树，返回它的中序 遍历。
     *
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.right=new TreeNode(2);
        root.right.left=new TreeNode(3);
        inorderTraversal(root);
//        heapSort(sort);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if(root==null) return new ArrayList<Integer>();
        List<Integer> result=new ArrayList<>();
        Stack<TreeNode> s=new Stack<>();
        TreeNode cur=root;
        while(cur!=null||!s.isEmpty()){
            if(cur!=null){//只需要一个指针在当前树移动遍历就可以
                s.push(cur);
                cur=cur.left;//当前不为空访问左孩子
            }else{
                cur=s.pop();//当前为空，出栈加入结果，访问右孩子
                result.add(cur.val);
                cur=cur.right;
            }
        }
        // dfs(root,result);
        return result;
    }
	
	private static void dfs(TreeNode root,List<Integer> r){
        if(root==null) return;
        if(root.left!=null){
            dfs(root.left,r);
        }
        r.add(root.val);
        if(root.right!=null){
            dfs(root.right,r);
        }
    }
}