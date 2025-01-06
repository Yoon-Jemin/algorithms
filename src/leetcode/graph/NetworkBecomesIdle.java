package leetcode.graph;

import java.util.*;

public class NetworkBecomesIdle {

    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2}};
        int[] patience = {0,2,1};
//        int[][] edges = {{0,1},{0,2},{1,2}};
//        int[] patience = {0,10,10};

        System.out.println(networkBecomesIdle(edges, patience));
    }

    public static int networkBecomesIdle(int[][] edges, int[] patience) {
        List<Integer>[] graph = new ArrayList[patience.length];
        int num = patience.length;

        for (int i = 0; i < num; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] shortestPath = new int[num];
        Arrays.fill(shortestPath, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        shortestPath[0] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int neighbor : graph[cur]) {
                if (shortestPath[neighbor] == -1) {
                    shortestPath[neighbor] = shortestPath[cur] + 1;
                    queue.add(neighbor);
                }
            }
        }

        int max = 0;
        for (int i = 1; i < num; i++) {
            int roundTripTime = shortestPath[i] * 2;
            int lastResendTime = (roundTripTime - 1) / patience[i] * patience[i];
            int totalTime = roundTripTime + lastResendTime;

            max = Math.max(max, totalTime);
        }

        return max + 1;
    }

}
