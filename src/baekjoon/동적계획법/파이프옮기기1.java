package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 파이프옮기기1 {

    static int N;
    static int[][] map;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        dp = new int[N][N][3];

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        map[0][0] = 1;
        map[0][1] = 1;
        System.out.println(dfs(0, 1, 0));
    }

    private static int dfs(int x, int y, int dir) {
        if (x == N - 1 && y == N - 1) {
            return 1;
        }

        if (dp[x][y][dir] != -1) {
            return dp[x][y][dir];
        }

        int ways = 0;

        if (dir == 0 || dir == 1) { // 가로 이동
            int nextX = x;
            int nextY = y + 1;
            if (nextX < N && nextY < N && map[nextX][nextY] == 0) {
                map[nextX][nextY] = 1;
                ways += dfs(nextX, nextY, 0);
                map[nextX][nextY] = 0;
            }
        }

        if (dir == 1 || dir == 2) { // 세로 이동
            int nextX = x + 1;
            int nextY = y;
            if (nextX < N && nextY < N && map[nextX][nextY] == 0) {
                map[nextX][nextY] = 1;
                ways += dfs(nextX, nextY, 2);
                map[nextX][nextY] = 0;
            }
        }

        int nextX = x + 1;
        int nextY = y + 1;
        if (nextX < N && nextY < N && map[nextX][nextY] == 0 && map[nextX - 1][nextY] == 0 && map[nextX][nextY - 1] == 0) {
            map[nextX][nextY] = 1;
            map[nextX - 1][nextY] = 1;
            map[nextX][nextY - 1] = 1;
            ways += dfs(nextX, nextY, 1);
            map[nextX][nextY] = 0;
            map[nextX - 1][nextY] = 0;
            map[nextX][nextY - 1] = 0;
        }

        return ways;
    }
}
