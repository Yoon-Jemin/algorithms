package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시 {

    static int N, M;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    static int[][][] directions = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2,3}, {3,0}},
            {{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
            {{0,1,2,3}}
    };

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        List<int[]> cctv5 = new ArrayList<>();
        List<int[]> cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 5) cctv5.add(new int[]{i, j});
                if (map[i][j] >= 1 && map[i][j] <= 4) cctvs.add(new int[]{i, j});
            }
        }

        simulate5(cctv5);
        dfs(cctvs, 0, map);
        System.out.println(answer);
    }

    private static void simulate5(List<int[]> cctv5) {
        for (int[] cctv : cctv5) {
            for (int d = 0; d < 4; d++) {
                watch(map, cctv[0], cctv[1], d);
            }
        }
    }

    private static void watch(int[][] board, int i, int j, int type) {
        int nx = i + dx[type];
        int ny = j + dy[type];

        while (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] != 6) {
            if (board[nx][ny] == 0) board[nx][ny] = -1;
            nx += dx[type];
            ny += dy[type];
        }
    }

    private static void dfs(List<int[]> cctvs, int index, int[][] curMap) {
        if (index == cctvs.size()) {
            answer = Math.min(answer, countBlindSpot(curMap));
            return;
        }

        int[] cctv = cctvs.get(index);
        int x = cctv[0];
        int y = cctv[1];
        int type = curMap[x][y];

        for (int[] dirs : directions[type]) {
            int[][] copy = copyMap(curMap);

            for (int d : dirs) {
                watch(copy, x, y, d);
            }
            dfs(cctvs, index + 1, copy);
        }
    }

    private static int countBlindSpot(int[][] curMap) {
        int count = 0;
        for (int i = 0; i < curMap.length; i++) {
            for (int j = 0; j < curMap[i].length; j++) {
                if (curMap[i][j] == 0) count++;
            }
        }
        return count;
    }

    private static int[][] copyMap(int[][] curMap) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < curMap.length; i++) {
            for (int j = 0; j < curMap[i].length; j++) {
                copy[i][j] = curMap[i][j];
            }
        }
        return copy;
    }

}
