package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountPairs {

    public static void main(String[] args) {
//        int n = 7;
//        int[][] edges = {{0,2}, {0,5}, {2,4}, {1,6}, {5,4}};
        int n = 12;
        int[][] edges = {};
        System.out.println(countPairs(n, edges));
    }

    public static long countPairs(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        List<Integer> components = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = dfs(graph, visited, i);
                components.add(size);
            }
        }

        long totalPairs = (long) n * (n - 1) / 2;
        long reachablePairs = 0;
        for (int size : components) {
            reachablePairs += (long) size * (size - 1) / 2;
        }
        return totalPairs - reachablePairs;
    }

    private static int dfs(List<List<Integer>> graph, boolean[] visited, int i) {
        visited[i] = true;
        int size = 1;
        for (int neighbor : graph.get(i)) {
            if (!visited[neighbor]) {
                size += dfs(graph, visited, neighbor);
            }
        }
        return size;
    }
}
