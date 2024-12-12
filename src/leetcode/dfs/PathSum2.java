package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {

    public static void main(String[] args) {
        // 주어진 예시: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22

        // 이진 트리 생성
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        // 목표 합
        int targetSum = 22;

        // Solution 클래스의 메서드 호출
        List<List<Integer>> result = pathSum(root, targetSum);

        // 결과 출력
        System.out.println("Has path sum: " + result); // 출력: true
    }

    public static List<List<Integer>> answer;
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return new ArrayList<>();
        answer = new ArrayList<>();
        DFS(new ArrayList<>(), root, 0, targetSum);

        return answer;
    }

    public static void DFS(List<Integer> path, TreeNode now, int currentSum, int target) {
        currentSum += now.val;
        path.add(now.val);
        if (now.left == null && now.right == null) {
            if (currentSum == target) {
                answer.add(new ArrayList<>(path));
            }
        }

        if (now.right != null) DFS(path, now.right, currentSum, target);
        if (now.left != null) DFS(path, now.left, currentSum, target);
        path.remove(path.size() - 1);
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
