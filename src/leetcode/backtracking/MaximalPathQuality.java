package leetcode.backtracking;

import java.util.*;

public class MaximalPathQuality {

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4};
        int[][] edges = {
                {0,1,10},
                {1,2,11},
                {2,3,12},
                {1,3,13}
        };
        int maxTime = 50;
        System.out.println(maximalPathQuality(values, edges, maxTime));
    }

    public static int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            graph[u].add(new int[]{v, cost});
            graph[v].add(new int[]{u, cost});
        }

        int[] visitedCount = new int[n];
        return DFS(0, 0, 0, values, graph, visitedCount, maxTime);
    }

    public static int DFS(
            int node,
            int time,
            int quality,
            int[] values,
            ArrayList<int[]>[] graph,
            int[] visitedCount,
            int maxTime
    ) {
        int maxQuality = 0;

        int currentQuality = quality + (visitedCount[node] == 0 ? values[node] : 0);
        visitedCount[node]++;

        if (node == 0) {
            maxQuality = currentQuality;
        }

        for (int[] next : graph[node]) {
            int nextNode = next[0];
            int nextTime = next[1] + time;
            if (nextTime <= maxTime) {
                int pathQuality = DFS(nextNode, nextTime, currentQuality, values, graph, visitedCount, maxTime);
                maxQuality = Math.max(maxQuality, pathQuality);
            }
        }

        visitedCount[node]--;
        return maxQuality;
    }
}
