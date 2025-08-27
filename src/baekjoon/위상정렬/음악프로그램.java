package baekjoon.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 음악프로그램 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 사람 수
        int m = Integer.parseInt(st.nextToken());   // 순서 개수

        int[] indegree = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 1; j < number; j++) {
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                indegree[b]++;
                a = b;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            ans.add(now);

            for (int next : graph[now]) {
                indegree[next]--;
                if (indegree[next] == 0) queue.add(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (ans.size() == n) {
            for (int a : ans) sb.append(a).append("\n");
        } else {
            sb.append(0);
        }

        System.out.println(sb.toString());
    }
}
