package com.rocketball.alg.tree;

import com.rocketball.alg.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        testLevelOrder();
    }

    public static void testIsSameTree() {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        a1.left = a2;
        b1.right = b2;
        isSameTree(a1, b1);

    }

    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q == null)
            return false;
        if (p == null && q != null)
            return false;
        if (p != null && q != null) {
            if (p.val == q.val) {
                boolean left = isSameTree(p.left, q.left);
                boolean right = isSameTree(p.right, q.right);
                return left && right;
            } else
                return false;
        }
        return true;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSameTree2(root.left, root.right);
    }

    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p != null && q == null)
            return false;
        if (p == null && q != null)
            return false;
        if (p != null && q != null) {
            if (p.val == q.val) {
                boolean left = isSameTree2(p.left, q.right);
                boolean right = isSameTree2(p.right, q.left);
                return left && right;
            } else
                return false;
        }
        return true;
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public static void testMaxDepth() {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        a1.right = a2;
        a1.left = a3;
        a3.right = a4;
        a4.left = a5;
        System.out.println(maxDepth(a1));
    }

    /**
     * ① NLR：前序遍历(Preorder Traversal 亦称（先序遍历））
     * ——访问根结点的操作发生在遍历其左右子树之前。
     * ② LNR：中序遍历(Inorder Traversal)
     * ——访问根结点的操作发生在遍历其左右子树之中（间）。
     * ③ LRN：后序遍历(Postorder Traversal)
     * ——访问根结点的操作发生在遍历其左右子树之后。
     */
    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [1,3,2]
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        if (root.left != null) {
            result.addAll(inorderTraversal(root.left));
        }
        result.add(root.val);
        if (root.right != null)
            result.addAll(inorderTraversal(root.right));
        return result;
    }

    /**
     * 给定一个二叉树，返回它的 前序 遍历。
     *
     *  示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        result.add(root.val);
        if (root.left != null) {
            result.addAll(preorderTraversal(root.left));
        }
        if (root.right != null)
            result.addAll(preorderTraversal(root.right));
        return result;
    }


    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        if (root.left != null) {
            result.addAll(postorderTraversal(root.left));
        }
        if (root.right != null) {
            result.addAll(postorderTraversal(root.right));
        }
        result.add(root.val);
        return result;
    }


    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层次遍历为：
     *
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     * @param root
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root){
        LinkedList<TreeNode> list = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root!=null)
            list.add(root);
        while(!list.isEmpty()){
            List<Integer> res = new ArrayList<>();
            int size = list.size();
            for(int i =0;i<size;i++){
                TreeNode node = list.pop();
                if(node.left!=null)  list.offer(node.left);
                if(node.right!=null) list.offer(node.right);
                res.add(node.val);
            }
            result.add(0,res);
        }
        return result;
    }



    public static void testInorderTraversal() {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        a1.right = a2;
        a1.left = a3;
        a3.right = a4;
        a4.left = a5;
        System.out.println(inorderTraversal(a1));
    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */

    public static  List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            System.out.println("level size:"+levelNum);
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }

    public static void testLevelOrder() {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        a1.right = a2;
        a1.left = a3;
        a3.right = a4;
        a4.left = a5;
        System.out.println(levelOrderBottom(a1));
    }
}
