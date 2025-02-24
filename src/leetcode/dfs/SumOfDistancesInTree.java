package leetcode.dfs;

import java.util.*;

public class SumOfDistancesInTree {

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        int[] result = sumOfDistancesInTree(n, edges);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] count = new int[n];
        int[] result = new int[n];

        Arrays.fill(count, 1);

        dfs1(0, -1, graph, count, result);
        dfs2(0, -1, graph, count, result, n);

        return result;
    }

    public static void dfs1(int node, int parent, ArrayList<Integer>[] graph, int[] count, int[] result) {
        for (int child : graph[node]) {
            if (child == parent) continue;
            dfs1(child, node, graph, count, result);
            count[node] += count[child];    // 자식의 수
            result[node] += result[child] + count[child];   // 자식 거리 합 + 자식 개수
        }
    }

    public static void dfs2(int node, int parent, ArrayList<Integer>[] graph, int[] count, int[] result, int n) {
        for (int child : graph[node]) {
            if (child == parent) continue;
            result[child] = result[node] - count[child] + (n - count[child]);
            dfs2(child, node, graph, count, result, n);
        }
    }
}
