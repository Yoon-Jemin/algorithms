package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 다리만들기 {

    static int N;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) map[i][j] = -1;
            }
        }

        int id = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    label(i, j, id);
                    id++;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = -1;

                if (map[i][j] > 0) {
                    q.add(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (map[nx][ny] > 0 && map[nx][ny] != map[x][y]) {
                    answer = Math.min(answer, dist[nx][ny] + dist[x][y]);
                }

                if (map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y];
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        System.out.println(answer);
    }

    public static void label(int x, int y, int id) {
        map[x][y] = id;

        boolean isEdge = false;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (map[nx][ny] == 0 && !isEdge) {
                isEdge = true;
            }

            if (map[nx][ny] == -1) label(nx, ny, id);
        }
    }

    public static void calculate (int start, int end, HashMap<Integer, List<int[]>> hashMap) {
        Queue<int[]> queue = new LinkedList<>();    // x, y, dist
        int[][] visited = new int[N][N];
        for (int[] point : hashMap.get(start)) {
            queue.add(new int[] {point[0], point[1], 0});
            visited[point[0]][point[1]] = 1;
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int dist = poll[2];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] == 1) continue;

                if (map[nx][ny] == end) {
                    answer = Math.min(answer, dist);
                    return;
                }

                if (map[nx][ny] == 0) {
                    queue.add(new int[] {nx, ny, dist + 1});
                    visited[nx][ny] = 1;
                }
            }
        }
    }
}
