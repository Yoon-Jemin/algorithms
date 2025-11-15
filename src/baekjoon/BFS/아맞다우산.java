package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 아맞다우산 {

    static class Node {
        int x;
        int y;
        int dist;
        int mask;

        public Node (int x, int y, int dist, int mask) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.mask = mask;
        }
    }

    static int M, N;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int[][] xId = new int[N][M];
        int totalX = 0;
        int sx = 0, sy = 0;
        int ex = 0, ey = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 'E') {
                    ex = i;
                    ey = j;
                } else if (map[i][j] == 'X') {
                    xId[i][j] = totalX++;
                }
            }
        }

        boolean[][][] visited = new boolean[N][M][1 << totalX];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, sy, 0, 0));
        visited[sx][sy][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.mask == (1 << totalX) - 1) {
                if (cur.x == ex && cur.y == ey) {
                    System.out.println(cur.dist);
                    return;
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == '#') continue;

                int nextMask = cur.mask;
                if (map[nx][ny] == 'X') {
                    nextMask |= (1 << xId[nx][ny]);
                }

                if (!visited[nx][ny][nextMask]) {
                    visited[nx][ny][nextMask] = true;
                    q.add(new Node(nx, ny, cur.dist + 1, nextMask));
                }
            }
        }

    }
}
