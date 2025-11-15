package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 가스관 {

    static int N, M;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M];

        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'M') {
                    start[0] = i;
                    start[1] = j;
                }

                if (map[i][j] == 'Z') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            char pipe = map[x][y];

            if (pipe == '|') {
                if (x - 1 >= 0 && visited[x - 1][y] == 0) {
                    visited[x - 1][y] = 1;
                    queue.add(new int[] {x - 1, y});
                } else if (x + 1 <= N - 1 && visited[x + 1][y] == 0) {
                    visited[x + 1][y] = 1;
                    queue.add(new int[] {x + 1, y});
                }
            }

            if (pipe == '-') {
                if (y - 1 >= 0 && visited[x][y - 1] == 0) {
                    visited[x][y - 1] = 1;
                    queue.add(new int[] {x, y - 1});
                } else if (y + 1 <= M - 1 && visited[x][y + 1] == 0) {
                    visited[x][y + 1] = 1;
                    queue.add(new int[] {x, y + 1});
                }
            }

            if (pipe == '+' || pipe == 'M') {
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (visited[nx][ny] == 1) continue;
                    if (map[nx][ny] == '.') continue;

                    visited[nx][ny] = 1;
                    queue.add(new int[] {nx, ny});
                }
            }

            if (pipe == '1') {
                if (y + 1 <= M - 1 && visited[x][y + 1] == 0) {
                    visited[x][y + 1] = 1;
                    queue.add(new int[] {x, y + 1});
                } else {
                    visited[x + 1][y] = 1;
                    queue.add(new int[] {x + 1, y});
                }
            }

            if (pipe == '2') {
                if (x - 1 >= 0 && visited[x - 1][y] == 0) {
                    visited[x - 1][y] = 1;
                    queue.add(new int[] {x - 1, y});
                } else {
                    visited[x][y + 1] = 1;
                    queue.add(new int[] {x, y + 1});
                }
            }

            if (pipe == '3') {
                if (x - 1 >= 0 && visited[x - 1][y] == 0) {
                    visited[x - 1][y] = 1;
                    queue.add(new int[] {x - 1, y});
                } else {
                    visited[x][y - 1] = 1;
                    queue.add(new int[] {x, y - 1});
                }
            }

            if (pipe == '4') {
                if (y - 1 >= 0 && visited[x][y - 1] == 0) {
                    visited[x][y - 1] = 1;
                    queue.add(new int[] {x, y - 1});
                } else {
                    visited[x + 1][y] = 1;
                    queue.add(new int[] {x + 1, y});
                }
            }

            if (pipe == '.') {      // 지워진 위치

                boolean up = false;
                if (x - 1 >= 0 && map[x - 1][y] != '.') {
                    if (map[x - 1][y] == '|' || map[x - 1][y] == '+' || map[x - 1][y] == '1' || map[x - 1][y] == '4') {
                        up = true;
                    }
                }

                boolean down = false;
                if (x + 1 <= N - 1 && map[x + 1][y] != '.') {
                    if (map[x + 1][y] == '|' || map[x + 1][y] == '+' || map[x + 1][y] == '2' || map[x + 1][y] == '3') {
                        down = true;
                    }
                }

                boolean left = false;
                if (y - 1 >= 0 && map[x][y - 1] != '.') {
                    if (map[x][y - 1] == '-' || map[x][y - 1] == '+' || map[x][y - 1] == '1' || map[x][y - 1] == '2') {
                        left = true;
                    }
                }

                boolean right = false;
                if (y + 1 <= M - 1 && map[x][y + 1] != '.') {
                    if (map[x][y + 1] == '-' || map[x][y + 1] == '+' || map[x][y + 1] == '3' || map[x][y + 1] == '4') {
                        right = true;
                    }
                }

                char block = 'b';
                if (up && down && left && right) block = '+';
                else if (up && down) block = '|';
                else if (right && left) block = '-';
                else if (down && right) block = '1';
                else if (up && right) block = '2';
                else if (up && left) block = '3';
                else if (down && left) block = '4';

                System.out.println((x + 1) + " " + (y + 1) + " " + block);
                return;
            }
        }
    }
}
