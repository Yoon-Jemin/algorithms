package leetcode.graph;

import java.util.Arrays;

public class FindCircleNum {

    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(findCircleNum(isConnected));
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected[0].length;

        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);

        int answer = 0;
        for (int now = 0; now < n; now++) {
            if (visited[now]) continue;
            DFS(isConnected, visited, now);
            answer++;
        }

        return answer;
    }

    private static void DFS(int[][] isConnected, Boolean[] visited, int now) {
        visited[now] = true;

        int[] neighbor = isConnected[now];
        for (int i = 0; i < neighbor.length; i++) {
            int nextIndex = i;
            if (neighbor[nextIndex] == 0) continue;
            if (now == nextIndex) continue;
            if (visited[nextIndex]) continue;
            DFS(isConnected, visited, nextIndex);
        }
    }
}
