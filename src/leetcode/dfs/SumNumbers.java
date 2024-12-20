package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class SumNumbers {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        System.out.println(sumNumbers(root));
    }

    static List<String> strNums;
    public static int sumNumbers(TreeNode root) {
        strNums = new ArrayList<>();

        DFS(root, "");

        int answer = 0;
        for (int i = 0; i < strNums.size(); i++) {
            answer += Integer.parseInt(strNums.get(i));
        }

        return answer;
    }

    public static void DFS(TreeNode root, String strVal) {
        String strNumber = strVal + String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            strNums.add(strNumber);
        }

        if (root.left != null) DFS(root.left, strNumber);
        if (root.right != null) DFS(root.right, strNumber);
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
