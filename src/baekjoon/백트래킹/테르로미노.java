package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 테르로미노 {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visited[i], false);
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                find1(i, j);

                visited[i][j] = true;
                find2(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void find1(int i, int j) {
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if (i - 1 >= 0) up = map[i - 1][j];
        if (i + 1 < N) down = map[i + 1][j];
        if (j - 1 >= 0) left = map[i][j - 1];
        if (j + 1 < M) right = map[i][j + 1];

        int center = map[i][j];

        if (up > 0 && down > 0 && left > 0) {
            answer = Math.max(answer, center + up + down + left);
        }

        if (up > 0 && down > 0 && right > 0) {
            answer = Math.max(answer, center + up + down + right);
        }

        if (up > 0 && right > 0 && left > 0) {
            answer = Math.max(answer, center + up + right + left);
        }

        if (right > 0 && down > 0 && left > 0) {
            answer = Math.max(answer, center + right + down + left);
        }
    }

    private static void find2(int x, int y, int depth, int sum) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            find2(nx, ny, depth + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
