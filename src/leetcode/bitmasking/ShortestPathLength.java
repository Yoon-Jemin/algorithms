package leetcode.bitmasking;

import java.util.*;

public class ShortestPathLength {

    public static void main(String[] args) {
//        int[][] graph = {{1}, {0,2,4}, {1,3,4}, {2}, {1,2}};
        int[][] graph = {{1},{0,2,4},{1,3},{2},{1,5},{4}};
        System.out.println(shortestPathLength(graph));
    }

    public static int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int finalState = (1 << n) - 1;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];

        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0});   // {현재 노드, 방문 상태, 이동 횟수}
            visited[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int node = state[0];
            int mask = state[1];
            int steps = state[2];

            if (mask == finalState) return steps;

            for (int next : graph[node]) {
                int nextMask = mask | (1 << next);
                if (!visited[next][nextMask]) {
                    visited[next][nextMask] = true;
                    queue.offer(new int[]{next, nextMask, steps + 1});
                }
            }
        }

        return -1;
    }

}
