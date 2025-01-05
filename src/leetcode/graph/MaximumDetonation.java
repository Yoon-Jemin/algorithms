package leetcode.graph;

import java.util.*;

public class MaximumDetonation {

    public static void main(String[] args) {
        int[][] bombs = {{2,1,3}, {6,1,4}};
//        int[][] bombs = {{1,1,5}, {10,10,5}};
//        int[][] bombs = {{1,2,3}, {2,3,1}, {3,4,2}, {4,5,3}, {5,6,4}};
        System.out.println(maximumDetonation(bombs));
    }

    public static int maximumDetonation(int[][] bombs) {
        ArrayList<Integer>[] graph = new ArrayList[bombs.length];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < bombs.length; i++) {
            int[] nowBomb = bombs[i];

            for (int j = 0; j < bombs.length; j++) {
                if (j == i) continue;
                int[] nextBomb = bombs[j];
                if (isNeighbor(nowBomb, nextBomb)){
                    graph[i].add(j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < graph.length; i++) {
            int denoteCount = BFS(i, graph);
            max = Math.max(max, denoteCount);
        }

        return max;
    }

    private static int BFS(int denote, ArrayList<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(visited, false);

        int denoteCount = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(denote);
        visited[denote] = true;

        while (!queue.isEmpty()) {
            int bomb = queue.poll();
            denoteCount++;

            for (int next : graph[bomb]){
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        return denoteCount;
    }

    private static boolean isNeighbor(int[] nowBomb, int[] nextBomb) {
        double a = Math.pow(nowBomb[0] - nextBomb[0], 2) + Math.pow(nowBomb[1] - nextBomb[1], 2);
        double b = Math.pow(nowBomb[2], 2);
        return a <= b;
    }
}
