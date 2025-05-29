package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 미네랄 {

    static int R, C;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());    // 막대를 던진 횟수
        st = new StringTokenizer(br.readLine());

        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            throwStick(heights[i], i % 2 ==0);
            fallCluster();
        }

        printMap();
    }

    static void throwStick(int height, boolean fromLeft) {
        int row = R - height;

        if (fromLeft) {
            for (int j = 0; j < C; j++) {
                if (map[row][j] == 'x') {
                    map[row][j] = '.';
                    break;
                }
            }
        } else {
            for (int j = C - 1; j >= 0; j--) {
                if (map[row][j] == 'x') {
                    map[row][j] = '.';
                    break;
                }
            }
        }
    }

    static void fallCluster() {
        visited = new int[R][C];

        for (int j = 0; j < C; j++) {
            if (map[R - 1][j] == 'x' && visited[R - 1][j] == 0) {
                dfs(R - 1, j);
            }
        }

        List<int[]> cluster = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && visited[i][j] == 0) {
                    cluster.add(new int[]{i, j});
                }
            }
        }

        if (cluster.isEmpty()) return;

        int drop = Integer.MAX_VALUE;
        for (int[] pos : cluster) {
            int x = pos[0];
            int y = pos[1];
            int d = 0;
            while (true) {
                int nx = x + d + 1;
                if (nx >= R) break;
                if (map[nx][y] == 'x' && visited[nx][y] == 1) break;
                d++;
            }
            drop = Math.min(drop, d);
        }

        for (int[] pos : cluster) {
            map[pos[0]][pos[1]] = '.';
        }

        for (int[] pos : cluster) {
            map[pos[0] + drop][pos[1]] = 'x';
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = 1;
        for (int dir = 0; dir < 4; dir++) {
            int newX = x + dx[dir];
            int newY = y + dy[dir];
            if (newX >= 0 && newX < R && newY >= 0 && newY < C) {
                if (map[newX][newY] == 'x' && visited[newX][newY] == 0) {
                    dfs(newX, newY);
                }
            }
        }
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
