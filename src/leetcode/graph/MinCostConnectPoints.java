package leetcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MinCostConnectPoints {

    public static void main(String[] args) {
//        int[][] points = {{0,0},{1,1},{1,0},{-1,1}};
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(minCostConnectPoints(points));
    }

    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[0] - o2[0]));

        pq.offer(new int[]{0, 0});

        int answer = 0;
        int count = 0;

        while (count < n) {
            int[] cur = pq.poll();
            int distance = cur[0];
            int currentPoint = cur[1];

            if (visited[currentPoint]) continue;
            visited[currentPoint] = true;
            answer += distance;
            count++;

            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int weight = Math.abs(points[currentPoint][0] - points[next][0])
                            + Math.abs(points[currentPoint][1] - points[next][1]);
                    pq.offer(new int[]{weight, next});
                }
            }
        }

        return answer;
    }
}
