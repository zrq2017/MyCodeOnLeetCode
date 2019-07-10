package algorithm;

public class MaxDepth {
    /**
     * 二叉树的最大深度
     *
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(9);
        TreeNode rr=root.right=new TreeNode(20);
        rr.left=new TreeNode(15);
        rr.right=new TreeNode(7);
        int i=maxDepth(root);
    }

    //二分思想，递归返回当前节点高度
    public static int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int left=maxDepth(root.left);//计算左子树的高度
        int right=maxDepth(root.right);//计算右子树的高度
        return left>right?left+1:right+1;//比较两颗棵子树的高度，返回 当前节点的高度=子树最大高度+1
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
