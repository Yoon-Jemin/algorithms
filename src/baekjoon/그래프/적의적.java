package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 적의적 {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean isPossible = true;
    static int[] color;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 주위 사람의 수
        M = Integer.parseInt(st.nextToken());   // 적대관계의 수

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }


        color = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                bfs(i);
            }

            if (!isPossible) break;
        }

        if (isPossible) System.out.println(1);
        else System.out.println(0);
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int nowColor = color[now];

            for (int next : graph[now]) {
                if (color[next] == 0) {
                    color[next] = nowColor == 1 ? 2 : 1;
                    queue.add(next);
                } else if (nowColor == color[next]) {
                    isPossible = false;
                    return;
                }
            }
        }
    }
}
