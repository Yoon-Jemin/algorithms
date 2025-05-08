package baekjoon.DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class 뉴스전하기 {
    /**
     * 24
     * -1 0 0 1 1 1 2 2 3 3 4 4 5 5 6 7 7 8 12 13 14 16 16 16
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int node = 0; node < n; node++) {
            int parent = sc.nextInt();
            if (parent == -1) continue;
            graph[parent].add(node);
        }

        int answer = dfs(0, graph);
        System.out.println(answer);
    }

    private static int dfs(int node, ArrayList<Integer>[] graph) {
        ArrayList<Integer> times = new ArrayList<>();

        for (int child : graph[node]) {
            times.add(dfs(child, graph));
        }

        Collections.sort(times, Comparator.reverseOrder());

        int max = 0;
        int plusTime = 1;
        for (int time : times) {
            max = Math.max(max, time + plusTime);
            plusTime++;
        }

        return max;
    }
}
