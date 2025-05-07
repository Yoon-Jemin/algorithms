package baekjoon.그래프;

import java.util.ArrayList;
import java.util.Scanner;

public class 촌수계산 {

    /**
     * 9
     * 7 3
     * 7
     * 1 2
     * 1 3
     * 2 7
     * 2 8
     * 2 9
     * 4 5
     * 4 6
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int start = sc.nextInt();
        int end = sc.nextInt();

        int m = sc.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        int answer = -1;
        answer = DFS(0, start, end, graph);
        System.out.println(answer);
    }

    private static int DFS(int prev, int now, int end, ArrayList<Integer>[] graph) {
        int distance = -1;
        if (now == end) {
            return 0;
        }

        for (int next : graph[now]) {
            if (next == prev) continue;
            int nextResult = DFS(now, next, end, graph);
            if (nextResult != -1) {
                return nextResult + 1;
            }
        }

        return distance;
    }
}
