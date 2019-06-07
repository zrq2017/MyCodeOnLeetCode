package algorithm;

public class MaxPathSum {
    /**
     * 二叉树中的最大路径和
     *
     * 给定一个非空二叉树，返回其最大路径和。
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1:
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     * 输出: 6
     * 示例 2:
     * 输入: [-10,9,20,null,null,15,7]
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int r = maxPathSum(root);
    }

    /**
     * 对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
     * 1. 其左右子树中所构成的和>>路径值较大的那个<<加上该节点的值后向父节点回溯构成最大路径
     * 2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
     * @param root
     * @return
     */
    public static int maxPathSum(TreeNode root) {
        int i = getMax(root);
        return max;
    }

    private static int max = Integer.MIN_VALUE;//保存每一次获取最大值后当前根节点树的最大路径值，因为最大路径不一定会经过根节点

    private static int getMax(TreeNode root) {
        if (root == null) return 0;
        //获取左右子树最大路径
        int left = Math.max(0, getMax(root.left));// 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(root.right));
        max = Math.max(max, root.val + left + right);//判断在该节点包含左右子树的路径和是否大于当前最大路径和
        return Math.max(left, right) + root.val;//返回每次左右节点的最大值
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
