package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        Queue<TreeNode> queueP = new LinkedList<>();
        queueP.add(p);

        Queue<TreeNode> queueQ = new LinkedList<>();
        queueQ.add(q);

        while (!queueP.isEmpty() && !queueQ.isEmpty()) {
            TreeNode nodeP = queueP.poll();
            TreeNode nodeQ = queueQ.poll();
            if (nodeP == null && nodeQ == null) continue;
            if (nodeP == null || nodeQ == null) return false;
            if (nodeP.val != nodeQ.val) return false;

            queueP.add(nodeP.left);
            queueQ.add(nodeQ.left);
            queueP.add(nodeP.right);
            queueQ.add(nodeQ.right);
        }

        return true;
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
