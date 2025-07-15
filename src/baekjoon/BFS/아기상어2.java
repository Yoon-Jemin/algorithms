package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어2 {

    static int N;
    static int M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    static int[] dy = {-1, 0, 1, 0, 1, -1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = -1;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 8; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                if (dist[nextX][nextY] == -1) {     // 미방문
                    dist[nextX][nextY] = dist[nowX][nowY] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }


        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, dist[i][j]);
            }
        }

        System.out.println(max);
    }
}
