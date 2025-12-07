package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출2 {

    static int N, M;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static class Ball {
        int x, y, dist;

        Ball(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int redX = 0, redY = 0;
        int blueX = 0, blueY = 0;
        int holeX = 0, holeY = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'R') {
                    redX = i; redY = j;
                    map[i][j] = '.';
                }

                if (map[i][j] == 'B') {
                    blueX = i; blueY = j;
                    map[i][j] = '.';
                }

                if (map[i][j] == 'O') {
                    holeX = i; holeY = j;
                }
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {redX, redY, blueX, blueY, 0});
        visited[redX][redY][blueX][blueY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int rx = cur[0];
            int ry = cur[1];
            int bx = cur[2];
            int by = cur[3];
            int depth = cur[4];

            if (depth >= 10) continue;

            for (int dir = 0; dir < 4; dir++) {

                Ball redMoved, blueMoved;

                if (needMoveRedFirst(dir, rx, ry, bx, by)) {
                    redMoved = moveBall(rx, ry, dir);
                    blueMoved = moveBall(bx, by, dir);
                } else {
                    blueMoved = moveBall(bx, by, dir);
                    redMoved = moveBall(rx, ry, dir);
                }

                if (map[blueMoved.x][blueMoved.y] == 'O') continue;

                if (map[redMoved.x][redMoved.y] == 'O') {
                    System.out.println(depth + 1);
                    return;
                }

                if (redMoved.x == blueMoved.x && redMoved.y == blueMoved.y) {
                    if (redMoved.dist > blueMoved.dist) {
                        redMoved.x -= dx[dir];
                        redMoved.y -= dy[dir];
                    } else {
                        blueMoved.x -= dx[dir];
                        blueMoved.y -= dy[dir];
                    }
                }

                if (!visited[redMoved.x][redMoved.y][blueMoved.x][blueMoved.y]) {
                    visited[redMoved.x][redMoved.y][blueMoved.x][blueMoved.y] = true;
                    q.add(new int[] {redMoved.x, redMoved.y, blueMoved.x, blueMoved.y, depth + 1});
                }
            }
        }

        System.out.println(-1);
    }

    static boolean needMoveRedFirst(int dir, int rx, int ry, int bx, int by) {
        // 오른쪽
        if (dir == 0) return ry > by;
        // 왼쪽
        if (dir == 1) return ry < by;
        // 위
        if (dir == 2) return rx < bx;
        // 아래
        if (dir == 3) return rx > bx;

        return true;
    }

    static Ball moveBall(int x, int y, int dir) {
        int nx = x;
        int ny = y;
        int moveCount = 0;

        while (true) {
            int nextX = nx + dx[dir];
            int nextY = ny + dy[dir];

            if (map[nextX][nextY] == '#') break;

            nx = nextX;
            ny = nextY;
            moveCount++;

            if (map[nx][ny] == 'O') break;
        }

        return new Ball(nx, ny, moveCount);
    }
}
