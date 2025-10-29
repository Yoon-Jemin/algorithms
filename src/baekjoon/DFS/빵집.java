package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빵집 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 6
        M = Integer.parseInt(st.nextToken());   // 10
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            DFS(i, 0);
        }

        System.out.println(answer);
    }

    private static boolean DFS(int x, int y) {
        if (y == M - 1) {
            answer++;
            return true;
        }

        visited[x][y] = true;

        for (int k = 0; k < 3; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 'x') continue;

            if (DFS(nx, ny)) return true;
        }

        return false;
    }
}
