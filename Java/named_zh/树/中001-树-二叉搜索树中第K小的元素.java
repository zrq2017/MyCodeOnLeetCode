package algorithm;

//中序遍历+递归，或二分查找
public class KthSmallest {

    /**
     *  二叉搜索树中第K小的元素
     *
     *  给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     *
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     * @param args
     */
    public static void main(String[] args) {
//        示例1
        int k=1;
        TreeNode root=new TreeNode(3);
        TreeNode left1=root.left=new TreeNode(1);
        root.right=new TreeNode(4);
        left1.right=new TreeNode(2);
//        示例2
//        int k=3;
//        TreeNode root=new TreeNode(5);
//        TreeNode left1=root.left=new TreeNode(3);
//        root.right=new TreeNode(6);
//        TreeNode ll=left1.left=new TreeNode(2);
//        left1.right=new TreeNode(4);
//        ll.left=new TreeNode(1);
        kthSmallest(root,k);
    }

    static int count=0;
    static int value=0;
    //中序遍历
    public static int kthSmallest(TreeNode root, int k) {
        count=k;
        if(root.left!=null){
            visit(root.left);
        }
        count--;
        if(count==0) return root.val;
        if(root.right!=null){
            visit(root.right);
        }
        return value;
    }

    private static void visit(TreeNode root) {
        if(root.left!=null){
            visit(root.left);
        }
        count--;
        if(count==0) value=root.val;
        if(root.right!=null){
            visit(root.right);
        }
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

/**
* 二分法：
* 统计左子树的节点数，和K做比较来判断第K个值是在左子树中还是右子树中或者根节点就是第K个值。
*/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int num = count(root.left);
        if(num >= k){//大于k，说明第k个节点在左子树中
            return kthSmallest(root.left, k);
        }else if(num + 1 < k){
            return kthSmallest(root.right, k-num-1);
        }
        return root.val;
    }
    
    private int count(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }
}
