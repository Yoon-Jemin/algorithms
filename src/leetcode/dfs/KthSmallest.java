package leetcode.dfs;

import java.util.PriorityQueue;

public class KthSmallest {

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

      static PriorityQueue<Integer> pq;
      public int kthSmallest(TreeNode root, int k) {
            pq = new PriorityQueue<>();
            DFS(root);

            for (int i = 1; i < k; i++){
                pq.poll();
            }
            return pq.poll();
      }

      public void DFS(TreeNode root) {
          if (root == null) return;

          pq.offer(root.val);
          if (root.left != null) DFS(root.left);
          if (root.right != null) DFS(root.right);
      }

}
