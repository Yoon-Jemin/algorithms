package baekjoon.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 작업 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        int[] time = new int[n + 1];
        int[] indegree = new int[n + 1];
        for (int now = 1; now <= n; now++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[now] = t;

            int m = Integer.parseInt(st.nextToken());   // 선행 개수;
            for (int j = 0; j < m; j++) {
                int before = Integer.parseInt(st.nextToken());
                graph[before].add(now);
                indegree[now]++;
            }
        }

        int[] totalTime = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                totalTime[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph[now]) {
                indegree[next]--;
                totalTime[next] = Math.max(totalTime[next], totalTime[now] + time[next]);

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) answer = Math.max(answer, totalTime[i]);

        System.out.println(answer);
    }
}