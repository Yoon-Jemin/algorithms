package leetcode.dfs;

import java.util.*;

public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> answer = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        map.put(root, 0);

        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            int nowLevel = map.get(now);
            if (!queue.isEmpty() && map.get(queue.peek()) != nowLevel ) answer.add(now.val);
            if (queue.isEmpty()) answer.add(now.val);

            if (now.left != null) {
                map.put(now.left, map.get(now) + 1);
                queue.add(now.left);
            }
            if (now.right != null) {
                map.put(now.right, map.get(now) + 1);
                queue.add(now.right);
            }
        }

        return answer;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }
}
