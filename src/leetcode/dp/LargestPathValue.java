package leetcode.dp;

import java.util.*;

public class LargestPathValue {

    public static void main(String[] args) {
        String colors = "abaca";
        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};

        System.out.println(largestPathValue(colors, edges));
    }

    public static int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        ArrayList<Integer>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            indegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[][] dp = new int[n][26];

        for (int i = 0; i < n; i++) {
            if (indegree[i] != 0) continue;
            queue.add(i);
            dp[i][colors.charAt(i) - 'a'] = 1;
        }

        int max = 0;
        int visitedNodes = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visitedNodes++;
            int nowColor = colors.charAt(cur) - 'a';

            for (int next : graph[cur]) {
                indegree[next]--;
                int nextColor = colors.charAt(next) - 'a';

                // DP 갱신 (이전 노드의 정보를 반영)
                for (int c = 0; c < 26; c++) {
                    dp[next][c] = Math.max(dp[next][c], dp[cur][c] + (c == nextColor ? 1 : 0));
                }

                max = Math.max(max, dp[next][nextColor]);
                if (indegree[next] == 0) queue.add(next);
            }
        }

        if (visitedNodes != n) return -1;
        return max;
    }
}
