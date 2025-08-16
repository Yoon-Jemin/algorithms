package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {

    static int N;
    static int[][] map;
    static int[] now;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        now = new int[2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    now[0] = i;
                    now[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        int sharkSize = 2;
        int eatCount = 0;
        int time = 0;

        while (true) {
            int[] result = bfs(sharkSize);
            if (result == null) break;

            int dist = result[0];
            int x = result[1];
            int y = result[2];

            time += dist;
            now[0] = x;
            now[1] = y;
            eatCount++;
            map[x][y] = 0;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(time);
    }

    private static int[] bfs(int sharkSize) {
        int[][] visited = new int[N][N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if (a[0] != b[0]) return a[0] - b[0];   // 거리 비교
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        pq.add(new int[]{0, now[0], now[1]});
        visited[now[0]][now[1]] = 1;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int dist = now[0];
            int x = now[1];
            int y = now[2];

            if (map[x][y] > 0 && map[x][y] < sharkSize) {
                return new int[]{dist, x, y};
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] == 1) continue;
                if (map[nx][ny] <= sharkSize) {
                    visited[nx][ny] = 1;
                    pq.add(new int[]{dist + 1, nx, ny});
                }
            }
        }

        return null;
    }
}
