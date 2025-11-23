package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 빙산 {

    static int N, M;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int year = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!isTwoBlock()) {
            melt();
            year++;
            if (allZero()) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(year);
    }

    private static boolean isTwoBlock() {
        int[][] visited = new int[N][M];

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j] == 1) continue;

                DFS(i, j, visited);
                count++;
            }
        }

        if (count >= 2) return true;

        return false;
    }

    private static void DFS(int x, int y, int[][] visited) {
        visited[x][y] = 1;

        for (int k = 0; k < 4;  k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny] == 1) continue;

            if (map[nx][ny] != 0) {
                DFS(nx, ny, visited);
            }
        }
    }

    private static void melt() {
        List<int[]> infos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x < 0 || y < 0 || x >= N || y >= M) continue;
                    if (map[x][y] == 0) count++;
                }

                infos.add(new int[] {i, j, count});
            }
        }

        for (int[] info : infos) {
            int x = info[0];
            int y = info[1];
            int melt = info[2];

            if (map[x][y] <= melt) {
                map[x][y] = 0;
            } else {
                map[x][y] -= melt;
            }
        }
    }

    private static boolean allZero() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }
}
