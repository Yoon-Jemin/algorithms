package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class MinOrder {

    public static void main(String[] args) {
//        int n = 6;
//        int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}};
        int n = 5;
        int[][] connections = {{1,0},{1,2},{3,2},{3,4}};
//        int n = 3;
//        int[][] connections = {{1,0},{2,0}};
        System.out.println(minReorder(n, connections));
    }

    public static int minReorder(int n, int[][] connections) {
        ArrayList<int[]>[] graph = new ArrayList[n];    // 다음 위치, Forward 여부 (1: Forward)
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] connection : connections) {
            graph[connection[0]].add(new int[]{connection[1], 1});  // Forward Edge
            graph[connection[1]].add(new int[]{connection[0], 0});
        }

        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        return DFS(0, graph, visited);
    }

    public static int DFS(int start, ArrayList<int[]>[] graph, Boolean[] visited) {
        visited[start] = true;
        int reorderCount = 0;

        for (int[] next : graph[start]) {
            if (visited[next[0]]) continue;
            if (next[1] == 1) reorderCount++;     // Forward Edge
            reorderCount += DFS(next[0], graph, visited);
        }

        return reorderCount;
    }
}
