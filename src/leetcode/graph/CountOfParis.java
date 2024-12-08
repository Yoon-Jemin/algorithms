package leetcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CountOfParis {

    public static void main(String[] args) {
        int[] answer = countOfPairs(3,1,3);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] countOfPairs(int n, int x, int y) {
        List<Integer>[] graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            graph[i].add(i+1);
            graph[i+1].add(i);
        }

        graph[x].add(y);
        graph[y].add(x);

        int[] answer = new int[n + 1];

        for (int start = 1; start <= n; start++) {
            int[] distance = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            distance[start] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
            pq.offer(new int[]{start, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curNode = cur[0];
                int curDistance = cur[1];

                if (curDistance > distance[curNode]) continue;

                for (int next : graph[curNode]) {
                    if( distance[next] < curDistance + 1) continue;
                    distance[next] = curDistance + 1;
                    pq.offer(new int[]{next, curDistance + 1});
                }
            }

            for (int i = 1; i < distance.length; i++) {
                if (i == start) continue;
                answer[distance[i]]++;
            }
        }

        int[] answer2 = new int[n];

        for (int i = 0; i < n; i++) {
            answer2[i] = answer[i+1];
        }

        return answer2;
    }
}
