package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안전영역 {

    static int N;
    static int[] moveX = {0, 0, -1, 1};
    static int[] moveY = {-1, 1, 0 ,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        int[][] waterMap = new int[N][N];


        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int answer = 0;
        for (int limit = minHeight - 1; limit <= maxHeight; limit++) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= limit) waterMap[i][j] = 1;
                }
            }

            int[][] copyMap = new int[N][N];
            for (int i = 0; i < N; i++) {   // 깊은 복사
                System.arraycopy(waterMap[i], 0, copyMap[i], 0, N);
            }

            answer = Math.max(answer, countArea(copyMap));
        }

        System.out.println(answer);
    }

    private static int countArea(int[][] map) {
        int area = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    DFS(map, i, j);
                    area++;
                }
            }
        }

        return area;
    }

    private static void DFS(int[][] map, int x, int y) {
        map[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + moveX[i];
            int ny = y + moveY[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[nx][ny] == 0) {
                DFS(map, nx, ny);
            }
        }
    }
}
