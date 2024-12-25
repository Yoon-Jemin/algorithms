package leetcode.dfs;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        root.left.right = new TreeNode(2);

        TreeNode ans = lowestCommonAncestor(root, root.left.right, root);
        System.out.println(ans.val);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    static TreeNode answer;
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        DFS(root, p, q);
        return answer;
    }

    public static Boolean DFS(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return null;

        boolean isLeft = false, isRight = false;
        if (node.left != null) isLeft = DFS(node.left, p, q);
        if (node.right != null) isRight = DFS(node.right, p, q);

        if (node == p || node== q){
            if (isLeft) answer = node;
            if (isRight) answer = node;
        }
        if (isLeft && isRight) answer = node;
        if (isRight || isLeft) return true;
        return node == p || node == q;
    }
}
