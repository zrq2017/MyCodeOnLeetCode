package algorithm;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;

/**
 * Created by zrq on 2019-5-26.
 */
public class LowestCommonAncestor {
    /**
     * 二叉树的最近公共祖先
     * <p>
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：
     * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     * 示例 1:
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * <p>
     * 示例 2:
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode rl = root.left = new TreeNode(5);
        TreeNode p = rl;
        TreeNode rr = root.right = new TreeNode(1);
        TreeNode rll = rl.left = new TreeNode(6);
        TreeNode rlr = rl.right = new TreeNode(2);
        TreeNode rrl = rr.left = new TreeNode(0);
        TreeNode rrr = rr.right = new TreeNode(8);
        rlr.left = new TreeNode(7);
        TreeNode q = rlr.right = new TreeNode(4);
        lowestCommonAncestor(root, p, q);
    }

    /**
     * 方法一（二分法思想，更快）：递归判断两节点是否在当前节点的左右子树：
     * 1）都在左边返回左子树
     * 2）都在右边返回右子树
     * 3）两边都有返回当前节点
     * 4）两边都没有返回空
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q) return root;//任一比较节点等于根节点返回根节点
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left!=null&&right!=null){
            return root;//两边的子树都不空，祖先节点为当前根节点
        }else if(left!=null&&right==null){//节点都在左子树
            return left;
        }else if(left==null&&right!=null){//节点都在右子树
            return right;
        }else {//两边都没有
            return null;
        }
    }

    /**
     * 方法二：找到两个节点的链路，根据链路来进行比较，第一个不同的节点就是祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = root;
        LinkedList<TreeNode> ltp = new LinkedList<>();
        LinkedList<TreeNode> ltq = new LinkedList<>();
        findCode(root,p,ltp);//保存除根节点外的所有链路上的节点
        findCode(root,q,ltq);
        for(int i=0,len=ltp.size()<ltq.size()?ltp.size():ltq.size();i<len;i++){//除根节点开始比较
            if(ltp.get(i)!=ltq.get(i)){
                break;
            }
            result=ltp.get(i);
        }
        return result;
    }

    //找到节点（抛开根节点）的所有路径上的节点
    public static boolean findCode(TreeNode root, TreeNode node, LinkedList<TreeNode> lt) {
        if (root == node) {//找到节点返回true
            return true;
        }
        if (root.left != null && findCode(root.left, node, lt)) {//找到节点添加节点到链路
            lt.addFirst(root.left);
            return true;
        }
        if (root.right != null && findCode(root.right, node, lt)) {
            lt.addFirst(root.right);
            return true;
        }
        return false;
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
