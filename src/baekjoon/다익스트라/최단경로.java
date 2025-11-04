package baekjoon.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로 {

    static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());   // 정점의 개수
        E = Integer.parseInt(st.nextToken());   // 간선의 개수

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int[] answer = new int[V + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);

        ArrayList<int[]>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, dist});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int now = poll[0];
            int nowDist = poll[1];

            if (answer[now] <= nowDist) continue;
            answer[now] = nowDist;

            for (int[] next : graph[now]) {
                pq.add(new int[] {next[0], nowDist + next[1]});
            }
        }

        for (int i = 1; i < answer.length; i++) {
            int a = answer[i];
            if (a == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(a);
        }
    }
}
