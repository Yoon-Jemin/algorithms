package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 등대 {

    public static void main(String[] args) {
        int n = 8;
        int[][] lighthouse = {
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {5, 6},
                {5, 7},
                {5, 8}
        };

        System.out.println(solution(n, lighthouse));
    }

    static List<List<Integer>> graph;
    static int[][] dp;
    static boolean[] visited;
    public static int solution(int n, int[][] lighthouse) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] house : lighthouse) {
            int a = house[0];
            int b = house[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        Arrays.fill(visited, false);

        DFS(1);
        return Math.min(dp[1][0], dp[1][1]);
    }

    private static void DFS(int node) {
        visited[node] = true;

        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int child : graph.get(node)) {
            if (visited[child]) continue;

            DFS(child);

            dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            dp[node][0] += dp[child][1];
        }
    }
}
