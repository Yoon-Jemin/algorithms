package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소가길을건너간이유6 {

    static int N;
    static int K;
    static int R;
    static int[][] comp;
    static boolean[][][] road;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {     // todo: 다시 풀기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());   // 소
        R = Integer.parseInt(st.nextToken());   // 도로

        road = new boolean[N + 1][N + 1][4];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int d1 = getDir(r1, c1, r2, c2);
            int d2 = getDir(r2, c2, r1, c1);

            road[r1][c1][d1] = true;
            road[r2][c2][d2] = true;
        }

        List<Point> cows = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cows.add(new Point(r, c));
        }

        comp = new int[N + 1][N + 1];
        int compId = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (comp[i][j] == 0) {
                    compId++;
                    bfs(i, j, compId);
                }
            }
        }

        int[] cowCount = new int[compId + 1];
        for (Point cow : cows) {
            cowCount[comp[cow.x][cow.y]]++;
        }

        long ans = 0;
        long sum = 0;

        for (int i = 1; i <= compId; i++) {
            ans += (long) cowCount[i] * sum;
            sum += cowCount[i];
        }

        System.out.println(ans);

    }

    private static void bfs(int sx, int sy, int compId) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx, sy));
        comp[sx][sy] = compId;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if (comp[nx][ny] != 0) continue;
                if (road[cur.x][cur.y][d]) continue;

                comp[nx][ny] = compId;
                q.add(new Point(nx, ny));
            }
        }
    }

    private static int getDir(int x1, int y1, int x2, int y2) {
        if (x2 == x1 - 1 && y2 == y1) return 0;
        if (x2 == x1 + 1 && y2 == y1) return 1;
        if (x2 == x1 && y2 == y1 - 1) return 2;
        if (x2 == x1 && y2 == y1 + 1) return 3;
        return -1;
    }
}


