package leetcode.graph;

import java.util.*;

public class MaximalNetworkRank {

    public static void main(String[] args) {
//        int n = 5;
//        int[][] roads = {{0,1}, {0,3}, {1,2}, {1,3}, {2,3}, {2,4}};

        int n = 5;
        int[][] roads = {{2,3}, {0,3}, {0,4}, {4,1}};

        System.out.println(maximalNetworkRank(n, roads));
    }

    public static int maximalNetworkRank(int n, int[][] roads) {
        HashSet<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{i, graph[i].size()});
        }

        int answer = 0;

        int[] node_1 = pq.poll();
        List<int[]> list = new ArrayList<>();
        list.addAll(pq);

        for (int i = 0; i < n - 1; i++) {
            int[] node_2 =list.get(i);
            if (graph[node_2[0]].contains(node_1[0])) {
                answer = Math.max(answer, node_1[1] + node_2[1] - 1);
            } else {
                answer = Math.max(answer, node_1[1] + node_2[1]);
            }
        }

        node_1 = pq.poll();
        for (int i = 1; i < n - 1; i++) {
            int[] node_2 =list.get(i);
            if (graph[node_2[0]].contains(node_1[0])) {
                answer = Math.max(answer, node_1[1] + node_2[1] - 1);
            } else {
                answer = Math.max(answer, node_1[1] + node_2[1]);
            }
        }

        return answer;
    }
}
