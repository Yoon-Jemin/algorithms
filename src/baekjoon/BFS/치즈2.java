package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치즈2 {

    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            List<int[]> cheeseToMelt = BFS();

            if (cheeseToMelt.isEmpty()) break;
            time++;

            for (int[] cheese : cheeseToMelt) {
                map[cheese[0]][cheese[1]] = 0;
            }
        }

        System.out.println(time);
    }

    private static List<int[]> BFS() {
        int[][] newMap = new int[N][M];
        List<int[]> check = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        newMap[0][0] = 2;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (map[nx][ny] == 0) {
                    if (newMap[nx][ny] == 2) continue;
                    newMap[nx][ny] = 2;
                    queue.add(new int[]{nx, ny});
                } else if (map[nx][ny] == 1){
                    if (newMap[nx][ny] == 3) continue;
                    check.add(new int[]{nx, ny});
                    newMap[nx][ny] = 3;
                }
            }
        }

        List<int[]> res = new ArrayList<>();
        for (int[] c : check) {
            int count = 0;
            for (int k = 0; k < 4; k++) {
                int nx = c[0] + dx[k];
                int ny = c[1] + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (newMap[nx][ny] == 2) count++;
            }

            if (count >= 2) res.add(new int[]{c[0], c[1]});
        }
        return res;
    }
}
