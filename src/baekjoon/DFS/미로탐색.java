package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미로탐색 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int N, M;
    static int[][] visited;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new int[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        DFS(1, 0, 0);
        System.out.println(answer);
    }

    private static void DFS(int depth, int x, int y) {
        visited[x][y] = 1;

        if (x == N - 1 && y == M - 1) { // 도착
            answer = Math.min(answer, depth);
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny] == 0 && map[nx][ny] == 1) {
                DFS(depth + 1, nx, ny);
            }
        }

        visited[x][y] = 0;
    }
}
