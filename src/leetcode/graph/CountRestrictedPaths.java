package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class CountRestrictedPaths {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,2,3},{1,3,3},{2,3,1},{1,4,2},{5,2,2},{3,5,1},{5,4,10}};
        System.out.println(countRestrictedPaths(n, edges));
    }

    public static int countRestrictedPaths(int n, int[][] edges) {
        int MOD = 1_000_000_007 ;
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{n, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();  // [현재 위치, 비용]
            int curNode = (int) cur[0];
            long curCost = cur[1];

            if (curCost > dist[curNode]) continue;

            for (int[] next : graph[curNode]) {
                int nextNode = next[0];
                long nextCost = curCost + next[1];

                if (nextCost < dist[nextNode]) {
                    dist[nextNode] = nextCost;
                    pq.add(new long[]{nextNode, nextCost});
                }
            }
        }

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);


        return DFS(1, n, dist, graph, memo, MOD);
    }

    public static int DFS(int curNode, int end, long[] dist, ArrayList<int[]>[] graph, int[] memo, int MOD) {
        if (curNode == end) return 1;
        if (memo[curNode] != -1) return memo[curNode];

        int path = 0;
        for (int[] next : graph[curNode]) {
            int nextNode = next[0];

            if (dist[curNode] > dist[nextNode]) {
                path = (path + DFS(nextNode, end, dist, graph, memo, MOD)) % MOD;
            }
        }

        return memo[curNode] = path;
    }
}
