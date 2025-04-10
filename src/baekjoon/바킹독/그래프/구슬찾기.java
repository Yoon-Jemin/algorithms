package baekjoon.바킹독.그래프;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 구슬찾기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();     // 구슬의 개수
        int limit = (num + 1) / 2;
        int n = sc.nextInt();   // 쌍의 개수

        ArrayList<Integer>[] biggerGraph = new ArrayList[num + 1];
        ArrayList<Integer>[] lighterGraph = new ArrayList[num + 1];
        for (int i = 1; i < num + 1; i++) {
            biggerGraph[i] = new ArrayList<>();
            lighterGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int b = sc.nextInt();   // 2
            int a = sc.nextInt();   // 1
            biggerGraph[a].add(b);
            lighterGraph[b].add(a);
        }

        Set<Integer> ans = new HashSet<>();
        calculateImpossible(ans, limit, biggerGraph, num);
        calculateImpossible(ans, limit, lighterGraph, num);
        System.out.println(ans.size());
    }

    private static void calculateImpossible(Set<Integer> ans, int limit, ArrayList<Integer>[] graph, int num) {
        int[] count = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            int[] visited = new int[num + 1];
            count[i] = DFS(i, graph, visited) - 1;
        }

        for (int i = 1; i <= num ; i++) {
            if (count[i] >= limit) ans.add(i);
        }
    }

    private static int DFS(int now, ArrayList<Integer>[] graph, int[] visited) {
        int count = 1;
        visited[now] = 1;
        for (int next : graph[now]) {
            if (visited[next] == 1) continue;
            count += DFS(next, graph, visited);
        }
        return count;
    }
}
