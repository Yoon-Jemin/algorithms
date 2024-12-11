package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class isValidBST {

    public static List<Integer> answer;
    public boolean isValidBST(TreeNode root) {
        answer = new ArrayList<>();
        PreOrder(root);

        for (int i = 0; i < answer.size() - 1; i++) {
            if (answer.get(i) > answer.get(i + 1)) return false;
        }

        return true;
    }

    private static void PreOrder(TreeNode root) {
        if( root == null) return;

        PreOrder(root.left);
        answer.add(root.val);
        PreOrder(root.right);
    }

    public static class TreeNode {
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
