package leetcode.dfs;

public class PathSum {

    public static void main(String[] args) {
        // 이진 트리 생성
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // 목표 합
        int targetSum = 5;

        // Solution 클래스의 메서드 호출
        boolean result = hasPathSum(root, targetSum);

        // 결과 출력
        System.out.println("Has path sum: " + result); // 출력: false
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        return DFS(root, 0, targetSum);
    }

    private static boolean DFS(TreeNode now, int currentSum, int target) {
        currentSum += now.val;

        if (now.left == null && now.right == null) {
            return currentSum == target;
        }

        boolean rightTree = now.right != null && DFS(now.right, currentSum, target);
        boolean leftTree = now.left != null && DFS(now.left, currentSum, target);

        return rightTree || leftTree;
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
