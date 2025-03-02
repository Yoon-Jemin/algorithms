package leetcode.bfs;

import java.util.*;

public class FindTheCity {

    public static void main(String[] args) {
//        int n = 6;
//        int[][] edges = {{0,1,10},{0,2,1},{2,3,1},{1,3,1},{1,4,1},{4,5,10}};
//        int distanceThreshold = 20;
        int n = 4;
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int distanceThreshold = 4;
        System.out.println(findTheCity(n, edges, distanceThreshold));
    }

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        ArrayList<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int min = Integer.MAX_VALUE;
        int answer = -1;

        for (int i = 0; i < n; i++) {
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            queue.add(new int[]{i, 0});
            distances[i] = 0;
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                int nowNode = now[0];
                int nowDistance = now[1];

                if (nowDistance > distances[nowNode]) continue;

                for (int[] next : graph[nowNode]) {
                    int nextNode = next[0];
                    int nextDistance = next[1];
                    if (nowDistance + nextDistance < distances[nextNode] && nowDistance + nextDistance <= distances[nextNode]) {
                        distances[nextNode] = nowDistance + nextDistance;
                        queue.add(new int[]{nextNode, distances[nextNode]});
                    }
                }
            }

            int reachableCount = 0;
            for (int distance : distances) {
                if (distance <= distanceThreshold) reachableCount++;
            }
            if (reachableCount <= min) {
                min = reachableCount;
                answer = i;
            }
        }

        return answer;
    }
}
