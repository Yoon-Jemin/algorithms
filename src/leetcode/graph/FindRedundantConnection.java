package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class FindRedundantConnection {

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[] result = findRedundantConnection(edges);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;

        int [] answer = new int[2];
        for (int i = n - 2; i > 0; i--) {
            int[] remove = edges[i];

            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int j = 0; j < n; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < n - 1; j++) {
                if (i == j) continue;
                graph[edges[j][1]].add(edges[j][0]);
                graph[edges[j][0]].add(edges[j][1]);
            }

            Boolean[] visited = new Boolean[n];
            Arrays.fill(visited, false);

            if (DFS(graph, visited, 1) == n - 1) return remove;
        }

        return answer;
    }

    public static int DFS(ArrayList<Integer>[] graph, Boolean[] visited, int start) {
        visited[start] = true;
        int count = 1;
        for (int next : graph[start]) {
            if (!visited[next]) {
                count += DFS(graph, visited, next);
            }
        }
        return count;
    }
}
