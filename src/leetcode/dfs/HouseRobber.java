package leetcode.dfs;

public class HouseRobber {

    public static void main(String[] args) {

    }

    public static int rob(TreeNode root) {
        int[] result = DFS(root);   // 안훔친 경우, 훔친 경우
        return Math.max(result[0], result[1]);
    }

    public static int[] DFS(TreeNode house) {
        if (house == null) return new int[]{0, 0};

        int[] left = DFS(house.left);
        int[] right = DFS(house.right);

        int rob = house.val + left[0] + right[0];
        int skip = Math.max(right[0], right[1]) + Math.max(left[0], left[1]);

        return new int[]{skip, rob};
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
