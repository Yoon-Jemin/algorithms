package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 특정한최단경로 {

    static int N, E;
    static int[][] dist;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 정점의 개수
        E = Integer.parseInt(st.nextToken());   // 간선의 개수

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, dist});
            graph[b].add(new int[]{a, dist});
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        int path1 = Integer.MAX_VALUE;
        if (distFrom1[v1] != Integer.MAX_VALUE && distFromV1[v2] != Integer.MAX_VALUE && distFromV2[N] != Integer.MAX_VALUE) {
            path1 = distFrom1[v1] + distFromV1[v2] + distFromV2[N];
        }

        int path2 = Integer.MAX_VALUE;
        if (distFrom1[v2] != Integer.MAX_VALUE && distFromV2[v1] != Integer.MAX_VALUE && distFromV1[N] != Integer.MAX_VALUE) {
            path2 = distFrom1[v2] + distFromV2[v1] + distFromV1[N];
        }

        int answer = Math.min(path1, path2);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int now = poll[0];
            int nowDist = poll[1];

            if (dist[now] > nowDist) dist[now] = nowDist;
            else continue;

            for (int[] n : graph[now]) {
                int next = n[0];
                int nextDist = n[1];


                pq.add(new int[] {next, nowDist + nextDist});
            }
        }

        return dist;
    }
}
