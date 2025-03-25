package baekjoon.바킹독;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 바이러스 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 컴퓨터의 개수

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());   // 쌍의 개수

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        System.out.println(DFS(1, graph, new HashSet<>()) - 1);
    }

    private static int DFS(int node, ArrayList<Integer>[] graph, HashSet<Integer> visited) {
        int count = 1;
        visited.add(node);

        for (int next : graph[node]) {
            if (!visited.contains(next)) {
                count += DFS(next, graph, visited);
            }
        }

        return count;
    }
}
