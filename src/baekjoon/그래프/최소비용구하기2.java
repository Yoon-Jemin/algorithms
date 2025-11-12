package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소비용구하기2 {

    static int start, end;
    static ArrayList<int[]>[] graph;
    static List<Integer> path = new ArrayList<>();
    static int minCost = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 도시의 개수

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());   // 버스의 개수

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, dist});
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int u = poll[0];
            int cost = poll[1];

            if (cost > dist[u]) continue;

            for (int[] nxt : graph[u]) {
                int v = nxt[0];
                int w = nxt[1];

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    queue.add(new int[] {v, dist[u] + w});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int v = end; v != 0; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);

        System.out.println(dist[end]);
        System.out.println(path.size());
        for (int v : path) System.out.print(v + " ");
    }

}
