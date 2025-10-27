package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 불켜기 {

    static int N, M;
    static boolean[][] light;
    static boolean[][] visited;
    static List<int[]>[][] switches;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        light = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        switches = new ArrayList[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                light[i][j] = false;
                visited[i][j] = false;
                switches[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            switches[a][b].add(new int[] {x, y});
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 1});
        visited[1][1] = true;
        light[1][1] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];

            for (int[] next: switches[x][y]) {
                int nx = next[0];
                int ny = next[1];

                if (!light[nx][ny]) {
                    light[nx][ny] = true;
                    count++;

                    if (!visited[nx][ny] && isAdjacent(nx, ny)) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                if (visited[nx][ny]) continue;
                if (!light[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny});
            }
        }

        return count;
    }

    public static boolean isAdjacent(int x, int y) {
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
            if (visited[nx][ny]) return true;
        }

        return false;
    }

}
