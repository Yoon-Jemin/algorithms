package programmers.level_2;

import java.util.*;

public class 배달 {

    public static void main(String[] args) {
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        System.out.println(solution(N, road, K));
    }

    public static int solution(int N, int[][] road, int K) {
        HashSet<Integer> set = new HashSet<>();

        ArrayList<int[]>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int[] r : road) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int nowIndex = poll[0];
            int distance = poll[1];

            set.add(nowIndex);

            for (int[] next : graph[nowIndex]) {
                int nextIndex = next[0];
                int nextDistance = distance + next[1];

                if (nextDistance > K) continue;
                if (set.contains(nextIndex)) continue;
                pq.add(new int[]{nextIndex, nextDistance});
            }
        }

        return set.size();
    }
}
