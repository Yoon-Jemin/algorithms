package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 유기농배추 {

    static int[] moveX = {0, 0, -1, 1};
    static int[] moveY = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int cabbage = Integer.parseInt(st.nextToken());

            int[][] map = new int[n][m];
            for (int j = 0; j < cabbage; j++) {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st1.nextToken());
                int b = Integer.parseInt(st1.nextToken());
                map[a][b] = 1;
            }

            int answer = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[j][k] == 1) {
                        DFS(map, j, k);
                        answer++;
                    }
                }
            }

            System.out.println(answer);
        }
    }

    private static void DFS(int[][] map, int x, int y) {
        map[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + moveX[i];
            int ny = y + moveY[i];

            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
            if (map[nx][ny] == 1) DFS(map, nx, ny);
        }
    }
}
