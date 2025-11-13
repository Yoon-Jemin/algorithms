package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    static int sand = 0;
    static int[][][] spreadPattern = {
            {   // ←
                    {-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7},
                    {-2, 0, 2}, {2, 0, 2}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}
            },
            {   // ↓
                    {-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7},
                    {0, -2, 2}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}
            },
            {   // →
                    {-1, -1, 1}, {1, -1, 1}, {-1, 0, 7}, {1, 0, 7},
                    {-2, 0, 2}, {2, 0, 2}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}
            },
            {   // ↑
                    {1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7},
                    {0, -2, 2}, {0, 2, 2}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}
            }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작
        int tornadoX = n / 2;
        int tornadoY = n / 2;

        List<Integer> moveCount = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            moveCount.add(i);
            moveCount.add(i);
        }
        moveCount.add(n - 1);

        int moveIndex = 0;
        for (int move : moveCount) {
            for (int i = 0; i < move; i++) {
                tornadoX += dx[moveIndex % 4];
                tornadoY += dy[moveIndex % 4];

                spread(tornadoX, tornadoY, moveIndex % 4);
                if (tornadoX == 0 && tornadoY == 0) break; // 종료 조건
            }

            if (tornadoX == 0 && tornadoY == 0) break;
            moveIndex++;
        }

        System.out.println(sand);
    }

    private static void spread(int x, int y, int dir) {
        int sandAmount = map[x][y];
        map[x][y] = 0;
        int moved = 0;

        for (int[] s : spreadPattern[dir]) {
            int nx = x + s[0];
            int ny = y + s[1];
            int percent = s[2];

            int amount = sandAmount * percent / 100;
            moved += amount;

            if (nx < 0 || ny < 0 || nx >= map.length  || ny >= map.length) {
                sand += amount;
            } else {
                map[nx][ny] += amount;
            }
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int remain = sandAmount - moved;

        if (nx < 0 || ny < 0 || nx >= map.length  || ny >= map.length) {
            sand += remain;
        } else {
            map[nx][ny] += remain;
        }
    }


}
