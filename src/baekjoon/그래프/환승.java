package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 환승 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 역의 수 : 9
        int m = Integer.parseInt(st.nextToken());   // 하이퍼튜브가 서로 연결하는 역의 개수 : 3
        int k = Integer.parseInt(st.nextToken());   // 하이퍼튜브의 개수 : 5

        int total = n + k;
        ArrayList<Integer>[] graph = new ArrayList[total + 1];

        for (int i = 1; i <= total; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int hyperLoop = n + i;

            for (int j = 0; j < m; j++) {
                int station  = Integer.parseInt(st.nextToken());
                graph[station].add(hyperLoop);
                graph[hyperLoop].add(station);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int[] dist = new int[total + 1];

        dist[1] = 1;
        visited.add(1);
        queue.add(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == n) {
                System.out.println(dist[now]);
                return;
            }

            for (int next : graph[now]) {
                if (visited.contains(next)) continue;
                visited.add(next);

                if (next <= n) dist[next] = dist[now] + 1;
                else dist[next] = dist[now];
                queue.add(next);
            }
        }

        System.out.println(-1);
    }
}
