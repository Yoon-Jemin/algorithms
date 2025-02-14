package leetcode.dfs;

public class GoodNodes {

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

    public int goodNodes(TreeNode root) {
        return DFS(root, root.val);
    }

    public int DFS(TreeNode root, int max) {
         if (root == null) return 0;

         int count = 0;
         if (root.val >= max) count++;

         count += DFS(root.right, Math.max(max, root.val));
         count += DFS(root.left, Math.max(max, root.val));

         return count;
    }
}
