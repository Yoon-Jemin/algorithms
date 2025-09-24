package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 타임머신 {

    static int N, M;
    static long[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 도시의 개수
        M = Integer.parseInt(st.nextToken());   // 버스 노선의 개수
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges.add(new int[] {start, end, dist});
        }

        answer = new long[N + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int dist = edge[2];

                if (answer[start] != Integer.MAX_VALUE && answer[start] + dist < answer[end]) {
                    answer[end] = answer[start] + dist;
                }
            }
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int dist = edge[2];

            if (answer[start] != Integer.MAX_VALUE && answer[start] + dist < answer[end]) {
                System.out.println(-1);
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (answer[i] == Integer.MAX_VALUE) sb.append(-1);
            else sb.append(answer[i]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
