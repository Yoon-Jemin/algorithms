package leetcode.graph;

import java.util.*;

public class ReachableNodes {

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0,1},{1,2},{3,1},{4,0},{0,5},{5,6}};
        int[] restricted = {4,5};
        System.out.println(reachableNodes(n, edges, restricted));
    }

    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> restrictedSet = new HashSet<>();
        for (int i : restricted) {
            restrictedSet.add(i);
        }

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        Set<Integer> reachableSet  = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            reachableSet.add(now);
            visited[now] = true;
            for (int next : graph[now]) {
                if (visited[next] || restrictedSet.contains(next)) continue;
                queue.add(next);
            }
        }

        return reachableSet.size();
    }
}
