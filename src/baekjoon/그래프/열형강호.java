package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 열형강호 {

    static List<Integer>[] graph;
    static int[] job;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 직원 수
        M = Integer.parseInt(st.nextToken());   // 일의 수

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int taskCount = Integer.parseInt(st.nextToken());
            for (int j = 0; j < taskCount; j++) {
                int task = Integer.parseInt(st.nextToken());
                graph[i].add(task);
            }
        }

        job = new int[M + 1];
        int result = 0;

        for (int i = 1; i <= N; i++) {
            visited = new boolean[M + 1];
            if (dfs(i)) result++;
        }

        System.out.println(result);
    }

    private static boolean dfs(int employee) {
        for (int work : graph[employee]) {
            if (visited[work]) continue;
            visited[work] = true;

            if (job[work] == 0 || dfs(job[work])) {
                job[work] = employee;
                return true;
            }
        }

        return false;
    }
}
