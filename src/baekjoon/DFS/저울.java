package baekjoon.DFS;

import java.util.ArrayList;
import java.util.Scanner;

public class 저울 {

    /**
     * 6
     * 5
     * 1 2
     * 2 3
     * 3 4
     * 5 4
     * 6 5
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer>[] graph1 = new ArrayList[n + 1];
        ArrayList<Integer>[] graph2 = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph1[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph1[a].add(b);
            graph2[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            boolean[] visited1 = new boolean[n + 1];
            boolean[] visited2 = new boolean[n + 1];

            int heavier = dfs(i, graph1, visited1);
            int lighter = dfs(i, graph2, visited2);

            System.out.println(n - heavier - lighter + 1);
        }

    }

    private static int dfs(int now, ArrayList<Integer>[] graph, boolean[] visited) {
        int depth = 1;
        visited[now] = true;
        for (int next : graph[now]) {
            if (visited[next]) continue;
            depth += dfs(next, graph, visited);
        }
        return depth;
    }
}
