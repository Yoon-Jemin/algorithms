package leetcode.graph;

import java.util.*;

public class ShortestDistanceAfterQueries {

    public static void main(String[] args) {
        int n = 5;
        int[][] queries = {{2,4},{0,2},{0,4}};
        int[] answer = shortestDistanceAfterQueries(n, queries);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer> answer = new ArrayList<>();

        PriorityQueue<Integer>[] graph  = new PriorityQueue[n];
        for (int i = 0; i < n - 1; i++) {
            graph[i] = new PriorityQueue<>(Comparator.reverseOrder());
            graph[i].add(i+1);
        }

        for (int[] query : queries) {
            graph[query[0]].add(query[1]);

            boolean[] visited = new boolean[n];

            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(a -> a[1]));  // 오름 차순
            queue.offer(new int[]{0, 0});

            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                int now_index = now[0];
                int now_count = now[1];

                if (now_index == n - 1) {
                    answer.add(now_count);
                    break;
                }

                if (visited[now_index]) continue;
                visited[now_index] = true;

                for (int next : graph[now_index]) {
                    queue.offer(new int[]{next, now_count + 1});
                }
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
