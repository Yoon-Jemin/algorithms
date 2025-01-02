package leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ClosestMeetingNode {

    public static void main(String[] args) {
//        int[] edges = {2,2,3,-1};
//        int node1 = 0;
//        int node2 = 1;
        int[] edges = {9,8,7,0,5,6,1,3,2,2};
        int node1 = 1;
        int node2 = 6;
        System.out.println(closestMeetingNode(edges, node1, node2));
    }

    public static int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] distance1 = getDistance(edges, node1);
        int[] distance2 = getDistance(edges, node2);

        int answer = -1;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < distance1.length; i++) {
            int bigger = Math.max(distance1[i], distance2[i]);
            if (bigger == Integer.MAX_VALUE) continue;
            if (bigger < minVal) {
                minVal = bigger;
                answer = i;
            }
        }

        return answer;
    }

    private static int[] getDistance(int[] edges, int node) {
        int[] distance = new int[edges.length];
        boolean[] visited = new boolean[edges.length];
        Arrays.fill(visited, false);
        Arrays.fill(distance, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();
        distance[node] = 0;
        queue.add(node);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;

            int next = edges[cur];
            if (next == -1 || visited[next]) continue;
            queue.add(next);
            distance[next] = distance[cur] + 1;
        }
        return distance;
    }
}
