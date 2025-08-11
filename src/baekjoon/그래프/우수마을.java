package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 우수마을 {

    static int N;
    static int[] villages;
    static ArrayList<Integer>[] tree;
    static int[] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        villages = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            villages[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dp = new int[N + 1][2];
        visited = new int[N + 1];

        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int now) {
        visited[now] = 1;
        dp[now][1] = villages[now];

        for (int child : tree[now]) {
            if (visited[child] == 0) {
                dfs(child);
                dp[now][0] += Math.max(dp[child][0], dp[child][1]);
                dp[now][1] += dp[child][0];
            }
        }
    }
}
