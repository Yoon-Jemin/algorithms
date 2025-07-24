package baekjoon.BFS;

import java.util.*;

public class 숨바꼭질4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int start = sc.nextInt();
        int end = sc.nextInt();

        int[] visited = new int[100001];
        int[] prev = new int[100001];
        int[] dist = new int[100001];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == end) break;

            for (int next : new int[] {now - 1, now + 1, now * 2}) {
                if (next >= 0 && next <= 100000 && visited[next] == 0) {
                    visited[next] = 1;
                    dist[next] = dist[now] + 1;
                    prev[next] = now;
                    queue.add(next);
                }
            }
        }

        System.out.println(dist[end]);

        List<Integer> path = new ArrayList<>();
        for (int at = end; at != start; at = prev[at] ) {
            path.add(at);
        }
        path.add(start);
        Collections.reverse(path);

        for (int p : path) {
            System.out.print(p + " ");
        }
    }
}
