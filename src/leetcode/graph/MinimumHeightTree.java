package leetcode.graph;

import java.util.*;

public class MinimumHeightTree {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1,0},{1,2},{1,3}};
        List<Integer> result = findMinHeightTrees(n, edges);
        for (Integer i : result) {
            System.out.println(i);
        }
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Arrays.asList(0);

        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 1) {
                leaves.add(i);
            }
        }

        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();

            for (int leaf : leaves) {
                int neighbor = graph[leaf].iterator().next();
                graph[neighbor].remove(leaf);
                if (graph[neighbor].size() == 1) {
                    newLeaves.add(neighbor);
                }
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}
