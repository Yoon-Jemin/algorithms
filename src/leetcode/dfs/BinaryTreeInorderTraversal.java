package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {

        static List<Integer> answer;
        public List<Integer> inorderTraversal(TreeNode root) {
            answer = new ArrayList<>();

            InOrder(root);

            return answer;
        }

        private void InOrder(TreeNode now) {
            if (now == null) {
                return;
            }
            InOrder(now.left);
            answer.add(now.val);
            InOrder(now.right);
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
}
