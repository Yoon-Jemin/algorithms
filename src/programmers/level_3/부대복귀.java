package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class 부대복귀 {

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{1, 2},{1, 4},{2, 4},{2, 5},{4, 5}};
        int[] source = {1, 3, 5};
        int destination = 5;
        int[] result = solution(n, roads, source, destination);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);

        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);   // 위치, 거리
        pq.offer(new int[]{destination, 0});
        visited.add(destination);

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int now = poll[0];
            int nowDistance = poll[1];

            distance[now] = nowDistance;

            for (int next : graph[now]) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    pq.offer(new int[]{next, nowDistance + 1});
                }
            }
        }

        int[] answer = new int[sources.length];
        int index = 0;
        for (int source : sources) {
            answer[index] = distance[source];
            index++;
        }

        return answer;
    }
}
