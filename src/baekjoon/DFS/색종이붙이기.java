package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 색종이붙이기 {

    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int[] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        paper = new int[6];
        Arrays.fill(paper, 5);

        dfs(0, 0, 0);

        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void dfs(int x, int y, int usedCount) {
        if (usedCount >= min) return;

        if (x == 10) {
            min = Math.min(min, usedCount);
            return;
        }

        int nx = (y == 9) ? x + 1 : x;
        int ny = (y + 1) % 10;

        if (map[x][y] == 0) {
            dfs(nx, ny, usedCount);
        } else {
            for (int size = 5; size >= 1; size--) {
                if (canPlace(x,y,size) && paper[size] > 0) {
                    place(x, y, size, 0);
                    paper[size]--;
                    dfs(nx, ny, usedCount + 1);
                    place(x, y, size, 1);
                    paper[size]++;
                }
            }
        }
    }

    private static boolean canPlace(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= 10 || j >= 10) return false;
                if (map[i][j] == 0) return false;
            }
        }

        return true;
    }

    private static void place(int x, int y, int size, int num) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = num;
            }
        }
    }
}
