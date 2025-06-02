package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[m][n];
        int[][] visited = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();
        int targetRipe = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = 1;
                }

                if (map[i][j] == 1 || map[i][j] == 0) {
                    targetRipe++;
                }
            }
        }

        int maxDay = 0;
        int ripe = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int day = now[2];
            ripe++;

            maxDay = Math.max(maxDay, day);

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] == 1) continue;
                if (map[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny, day + 1});
                    visited[nx][ny] = 1;
                }
            }
        }

        if (ripe < targetRipe) {
            System.out.println(-1);
            return;
        }
        System.out.println(maxDay);
    }
}
