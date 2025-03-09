package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;

public class MakeConnected {

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println(makeConnected(n, connections));
    }

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] connection : connections) {
            graph[connection[0]].add(connection[1]);
            graph[connection[1]].add(connection[0]);
        }

        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);

        int cluster = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            DFS(i, graph, visited);
            cluster++;
        }

        return cluster - 1;
    }

    private static void DFS(int now, ArrayList<Integer>[] graph, Boolean[] visited) {
        if(visited[now]) return;
        visited[now] = true;
        for (Integer neighbor : graph[now]) {
            if (!visited[neighbor]) DFS(neighbor, graph, visited);
        }
    }
}
