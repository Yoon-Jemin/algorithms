package baekjoon.그래프;

import java.io.*;
import java.util.*;

public class 효율적인해킹 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 컴퓨터의 개수
        int m = Integer.parseInt(st.nextToken());   // 신뢰관계 컴퓨터의 개수

        int[] result = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        for (int i = 1; i <= n; i++) {
            int[] visited = new int[n + 1];
            BFS(graph, result, visited, i);
        }

        int maxHack = 0;
        for (int i = 1; i <= n; i++) {
            maxHack = Math.max(maxHack, result[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (result[i] == maxHack) bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }

    private static void BFS(ArrayList<Integer>[] graph, int[] result, int[] visited, int now) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(now);
        visited[now] = 1;
        result[now]++;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (visited[next] == 0) {
                    visited[next] = 1;
                    queue.add(next);
                    result[next]++;
                }
            }
        }
    }
}