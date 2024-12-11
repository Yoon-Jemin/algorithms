package leetcode.dfs;

public class RecoverTree {

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev;
    private void recoverTree(TreeNode root) {
        prev = new TreeNode(Integer.MIN_VALUE);
        BFS(root);

        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

    }

    private void BFS(TreeNode root) {
        if (root == null) return;

        BFS(root.left);
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        BFS(root.right);
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
