package leetcode.graph;

import java.util.*;

public class MinScore {

    public static void main(String[] args) {
        int n = 4;
        int[][] roads = {{1,2,9},{2,3,6},{2,4,5},{1,4,7}};
        System.out.println(minScore(n, roads));
    }

    public static int minScore(int n, int[][] roads) {
        int minScore = Integer.MAX_VALUE;

        List<int[]>[] graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }

        Boolean[] visited = new Boolean[n + 1];
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            visited[now] = true;

            for (int[] next : graph[now]) {
                if (!visited[next[0]]) {
                    queue.add(next[0]);
                    minScore = Math.min(minScore, next[1]);
                }
            }
        }

        return minScore;
    }
}
