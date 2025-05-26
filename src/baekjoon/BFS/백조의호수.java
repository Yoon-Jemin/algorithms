package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백조의호수 {

    static int n, m;
    static int[][] map;
    static int[][] visited;
    static Queue<int[]> waterQ = new LinkedList<>();
    static Queue<int[]> swanQ = new LinkedList<>();
    static Queue<int[]> nextSwanQ = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int swanX, swanY, destX, destY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);

                if (c == '.') {
                    map[i][j] = 1;
                    waterQ.add(new int[]{i, j});
                } else if (c == 'L') {
                    if (swanX == 0 && swanY == 0) {
                        swanX = i;
                        swanY = j;
                    } else {
                        destX = i;
                        destY = j;
                    }
                    map[i][j] = 1;
                    waterQ.add(new int[]{i, j});
                } else {
                    map[i][j] = 0;
                }
            }
        }

        swanQ.add(new int[]{swanX, swanY});
        visited[swanX][swanY] = 1;

        int day = 0;

        while (true) {
            if (moveSwan()) {
                System.out.println(day);
                break;
            }
            melt();
            swanQ = nextSwanQ;
            nextSwanQ = new LinkedList<>();
            day++;
        }
    }

    private static boolean moveSwan() {
        while (!swanQ.isEmpty()) {
            int[] cur = swanQ.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == destX && y == destY) return true;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] == 1) continue;
                visited[nx][ny] = 1;

                if (map[nx][ny] == 1) swanQ.add(new int[]{nx, ny});
                else nextSwanQ.add(new int[]{nx, ny});
            }
        }

        return false;
    }

    private static void melt() {
        int size = waterQ.size();
        for (int i = 0; i < size; i++) {
            int[] cur = waterQ.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    waterQ.add(new int[]{nx, ny});
                }
            }
        }
    }
}
