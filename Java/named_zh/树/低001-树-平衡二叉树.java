/*****
平衡二叉树

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
**/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * https://blog.csdn.net/zane3/article/details/88979628
 */
class Solution {
    /*方法：
    * 如果一棵树的高度 Height==-1，那么此树不平衡
    * 如果一棵树的高度 Height>= 0，那么此树平衡，并且此树的高度为Height
    */
    public boolean isBalanced(TreeNode root) {
        if(root!=null){
        int lheight=depth(root.left),rheight=depth(root.right);
        if(lheight==-1||rheight==-1) return false;
            return Math.abs(lheight-rheight)<=1?true:false;
        }
        return true;
    }
    
    public int depth(TreeNode root){
        if(root==null) return 0;
        int lheight=depth(root.left),rheight=depth(root.right);
        if(lheight==-1||rheight==-1) return -1;//-1表示两棵树不平衡
        return Math.abs(lheight-rheight)<=1?Math.max(lheight,rheight)+1:-1;//加1保证树的深度由下往上增加
    }
}
class Solution {
    //这个ReturnNode是参考我描述的递归套路的第二步：思考返回值是什么
    //一棵树是BST等价于它的左、右俩子树都是BST且俩子树高度差不超过1
    //因此我认为返回值应该包含当前树是否是BST和当前树的高度这两个信息
    private class ReturnNode{
        boolean isB;
        int depth;
        public ReturnNode(int depth, boolean isB){
            this.isB = isB;
            this.depth = depth;
        }
    }
    //主函数
    public boolean isBalanced(TreeNode root) {
        return isBST(root).isB;
    }
    //参考递归套路的第三部：描述单次执行过程是什么样的
    //这里的单次执行过程具体如下：
    //是否终止?->没终止的话，判断是否满足不平衡的三个条件->返回值
    public ReturnNode isBST(TreeNode root){
        if(root == null){
            return new ReturnNode(0, true);
        }
        //不平衡的情况有3种：左树不平衡、右树不平衡、左树和右树差的绝对值大于1
        ReturnNode left = isBST(root.left);
        ReturnNode right = isBST(root.right);
        if(left.isB == false || right.isB == false){
            return new ReturnNode(0, false); 
        }
        if(Math.abs(left.depth - right.depth) > 1){
            return new ReturnNode(0, false);
        }
        //不满足上面3种情况，说明平衡了，树的深度为左右俩子树最大深度+1
        return new ReturnNode(Math.max(left.depth, right.depth) + 1, true);
    }
}