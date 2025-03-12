package leetcode.bfs;

import java.util.*;

public class MinimumTime {

    public static void main(String[] args) {
        int n = 8;
        int[][] edges = {{4,4,1},{7,4,1},{5,0,5},{1,7,8},{2,5,2},{5,5,7},{7,0,8},{4,0,2}};
        int[] disappear = {3,19,1,1,17,5,1,11};
        int[] answer = minimumTime(n, edges, disappear);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<List<int[]>> graph = new ArrayList<>();
        int[] distance = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        distance[0] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if (curDist > distance[curNode]) continue;

            for (int[] edge : graph.get(curNode)) {
                int nextNode = edge[0];
                int weight = edge[1];
                int newDist = curDist + weight;

                if (newDist >= disappear[nextNode]) continue;

                if (newDist < distance[nextNode]) {
                    distance[nextNode] = newDist;
                    queue.add(new int[]{nextNode, newDist});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (distance[i] >= disappear[i]) {
                distance[i] = -1;
            }
        }

        return distance;
    }
}
