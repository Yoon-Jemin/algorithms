package leetcode.dfs;

import javax.swing.tree.TreeNode;

public class SubtreeWithAllDeepest {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return null;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight > rightHeight) return subtreeWithAllDeepest(root.left);
        else if (leftHeight < rightHeight) return subtreeWithAllDeepest(root.right);
        else return root;
    }

    public static int height(TreeNode root) {
        if (root == null) return 0;
        return (1 + Math.max(height(root.left), height(root.right)));
    }
}
