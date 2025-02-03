package leetcode.dfs;

import java.util.*;

public class DistanceK {

    public static void main(String[] args) {
        // 트리 구성 (주어진 root = [3,5,1,6,2,0,8,null,null,7,4])
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(1);
//        root.left.left = new TreeNode(6);
//        root.left.right = new TreeNode(2);
//        root.right.left = new TreeNode(0);
//        root.right.right = new TreeNode(8);
//        root.left.right.left = new TreeNode(7);
//        root.left.right.right = new TreeNode(4);
        TreeNode root = new TreeNode(1);

        // target 찾기 (값이 5인 노드)
        TreeNode target = findTarget(root, 1);

        // k 거리 노드 찾기
        int k = 2;
        List<Integer> result = distanceK(root, target, k);

        // 결과 출력
        System.out.println(result);
    }

    // 특정 값을 가진 노드를 찾는 메서드 (DFS)
    private static TreeNode findTarget(TreeNode root, int targetVal) {
        if (root == null) return null;
        if (root.val == targetVal) return root;
        TreeNode left = findTarget(root.left, targetVal);
        if (left != null) return left;
        return findTarget(root.right, targetVal);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<int[]> path = new ArrayList<>();
        DFS1(root, path);

        ArrayList<Integer>[] graph = new ArrayList[path.size() + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] p : path) {
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }

        List<Integer> ans = new ArrayList<>();
        if (graph.length == 1) {
            if (k == 0) {
                ans.add(target.val);
            }
            return ans;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{target.val, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int distance = poll[1];

            visited.add(now);
            if (distance == k) {
                ans.add(poll[0]);
                continue;
            }

            for (int next : graph[now]) {
                if (!visited.contains(next)) {
                    queue.offer(new int[]{next, distance + 1});
                }
            }
        }

        return ans;
    }

    public static void DFS1(TreeNode root, List<int[]> path) {
        if (root == null) return;
        if (root.left != null) {
            path.add (new int[] {root.val, root.left.val});
            DFS1(root.left, path);
        }
        if (root.right != null) {
            path.add (new int[] {root.val, root.right.val});
            DFS1(root.right, path);
        }
    }
}
