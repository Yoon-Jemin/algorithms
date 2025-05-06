package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 우주탐사선 {

    static int[][] graph;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        visited = new boolean[n];
        visited[start] = true;
        dfs(1, start, 0);
        System.out.println(answer);
    }

    private static void dfs(int count, int now, int cost) {
        if (count == n) {
            answer = Math.min(cost, answer);
        }

        for (int next = 0; next < n; next++) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(count + 1, next, cost + graph[now][next]);
            visited[next] = false;
        }
    }

}
