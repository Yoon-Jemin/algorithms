package leetcode.graph;

import java.util.*;

public class CountCompleteComponents {

    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{2,0}, {2,1}};

        System.out.println(countCompleteComponents(n, edges));
    }

    public static int countCompleteComponents(int n, int[][] edges) {
        int[][] graph = new int[n][n];

        for (int[] row : graph) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i < n; i++) {
            graph[i][i] = 1;
        }

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == Integer.MAX_VALUE / 2) {
                    graph[i][j] = 0;
                }
            }
        }

        boolean[] visited = new boolean[n];

        int count = 0;
        for (int i = 0; i < n; i++) {
            if(visited[i]) continue;
            boolean isComplete = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                visited[node] = true;
                int[] row = graph[node];
                for (int j = 0; j < row.length; j++){
                    if (row[j] != 0 && row[j] != 1) {
                        isComplete = false;
                    }
                    if(!visited[j] && row[j] == 1) queue.add(j);
                }
            }
            if (isComplete) count++;
        }

        return count;
    }
}
