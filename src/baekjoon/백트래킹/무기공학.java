package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 무기공학 {

    static int N,M;
    static int[][] board;
    static int[][] visited;
    static int maxSum = 0;

    static int[][] dx = {
            {0, 1, 0},
            {0, 1, 0},
            {0, -1, 0},
            {0, -1, 0}
    };

    static int[][] dy = {
            {0, 0, 1},
            {0, 0, -1},
            {0, 0, 1},
            {0, 0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(maxSum);
    }

    private static void dfs(int x, int y, int sum) {
        if (y == M) {
            x++;
            y = 0;
        }

        if (x == N) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        if (visited[x][y] == 0) {
            for (int i = 0; i < 4; i++) {
                int ax = x + dx[i][1];
                int ay = y + dy[i][1];
                int bx = x + dx[i][2];
                int by = y + dy[i][2];

                if (inRange(ax, ay) && inRange(bx, by)) {
                    if (visited[ax][ay] == 0 && visited[bx][by] == 0) {
                        visited[x][y] = 1;
                        visited[ax][ay] = 1;
                        visited[bx][by] = 1;

                        int newWeapon = 2 * board[x][y] + board[ax][ay] + board[bx][by];
                        dfs (x, y + 1, sum + newWeapon);

                        visited[x][y] = 0;
                        visited[ax][y] = 0;
                        visited[bx][by] = 0;
                    }
                }
            }
        }

        dfs(x, y + 1, sum);
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
