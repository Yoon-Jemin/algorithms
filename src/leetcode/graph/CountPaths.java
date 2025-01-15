package leetcode.graph;

import java.util.*;

public class CountPaths {

    public static void main(String[] args) {
        int n = 7;
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};

        System.out.println(countPaths(n, roads));
    }

    public static int countPaths(int n, int[][] roads) {
        final int MOD = 1_000_000_007;

        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            graph[road[0]].add(new int[] {road[1], road[2]});
            graph[road[1]].add(new int[] {road[0], road[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        int[] ways = new int[n];
        ways[0] = 1;

        PriorityQueue<long[]> queue = new PriorityQueue<>((a,b) -> Long.compare(a[1] , b[1]));
        queue.add(new long[]{0, 0});

        while (!queue.isEmpty()) {
            long[] now = queue.poll();   // 현재 위치, 비용
            int currentNode = (int) now[0];
            long currentCost = now[1];

            if (currentCost > dist[currentNode]) continue;

            for (int[] next : graph[(int) now[0]]) {
                int nextNode = next[0];
                long nextCost = currentCost + next[1];

                if (nextCost < dist[nextNode]) {
                    dist[nextNode] = nextCost;
                    ways[nextNode] = ways[currentNode];
                    queue.add(new long[]{nextNode, nextCost});
                } else if (nextCost == dist[nextNode]) {
                    ways[nextNode] = (ways[nextNode] + ways[currentNode]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }
}
