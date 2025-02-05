package leetcode.dfs;

public class AddOneRow {

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

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        DFS(root, depth, val, 1);
        return root;
    }

    public void DFS(TreeNode root, int depth, int val, int nowDepth) {
        if (nowDepth + 1 == depth) {
            TreeNode left = root.left;
            TreeNode right = root.right;

            TreeNode newLeft = new TreeNode(val);
            newLeft.left = left;
            TreeNode newRight = new TreeNode(val);
            newRight.right = right;

            root.left = newLeft;
            root.right = newRight;
        } else {
            if (root.left != null) DFS(root.left, depth, val, nowDepth + 1);
            if (root.right != null) DFS(root.right, depth, val, nowDepth + 1);
        }
    }
}
