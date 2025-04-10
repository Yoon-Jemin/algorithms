package baekjoon.바킹독.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 결혼식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());   // 친구 수
        int m = Integer.parseInt(br.readLine());   // 간선 수

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        visited.add(1);

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int friend = now[0];
            int distance = now[1];

            if (distance == 2) continue;

            for (int next : graph[friend]) {
                if (!visited.contains(next)) {
                    queue.add(new int[]{next, distance + 1});
                    visited.add(next);
                }
            }
        }

        System.out.println(visited.size() - 1);
    }
}
