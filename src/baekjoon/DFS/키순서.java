package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 키순서 {

    static int N, M;
    static int[] bigger;
    static int[] smaller;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 학생들의 수
        M = Integer.parseInt(st.nextToken());   // 키를 비교한 횟수

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        bigger = new int[N + 1];
        smaller = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            Set<Integer> visited = new HashSet<>();
            DFS(i, visited);
            bigger[i] = visited.size();
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (smaller[i] + bigger[i] == N - 1) count++;
        }

        System.out.println(count);
    }

    private static void DFS(int now, Set<Integer> visited) {
        for (int next: graph[now]) {
            if (visited.contains(next)) continue;

            visited.add(next);
            smaller[next]++;
            DFS(next, visited);
        }
    }
}
