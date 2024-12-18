package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class Flatten {

    static List<TreeNode> nodes;
    public void flatten(TreeNode root) {
        nodes = new ArrayList<>();
        DFS1(root);

        for (int i = 1; i < nodes.size(); i++) {
            TreeNode parent = nodes.get(i - 1);
            TreeNode child = nodes.get(i);
            parent.left = null;
            parent.right = child;
        }

    }

    public static void DFS1 (TreeNode node) {
        if (node == null) return;

        nodes.add(node);
        DFS1(node.left);
        DFS1(node.right);
    }


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
}
