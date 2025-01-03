package leetcode.graph;

import java.util.*;

public class GetAncestors {

    public static void main(String[] args) {
        int n = 8;
        int[][] edges = {{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        List<List<Integer>> answer = getAncestors(n, edges);
        for (List<Integer> list : answer) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            indegree[edge[1]]++;
        }

        List<Set<Integer>> parents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parents.add(new HashSet<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph[node]) {
                parents.get(neighbor).add(node);
                parents.get(neighbor).addAll(parents.get(node));

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.add(neighbor);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Set<Integer> set : parents) {
            List<Integer> list = new ArrayList<>();
            list.addAll(set);
            Collections.sort(list);
            ans.add(list);
        }

        return ans;
    }
}
