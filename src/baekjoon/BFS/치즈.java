package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치즈 {

    static int N,M;
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
        int prev = 0;
        while (true) {
            List<int[]> cheeseToMelt = BFS(map);

            if (cheeseToMelt.isEmpty()) break;
            prev = cheeseToMelt.size();
            time++;

            for (int[] c : cheeseToMelt) {
                map[c[0]][c[1]] = 0;
            }
        }

        System.out.println(time);
        System.out.println(prev);
    }

    private static List<int[]> BFS(int[][] map) {
        List<int[]> cheeseToMelt = new ArrayList<>();
        int[][] visited = new int[N][M];

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny] == 0) {
                    if (map[nx][ny] == 1) cheeseToMelt.add(new int[]{nx, ny});
                    else queue.offer(new int[]{nx, ny});

                    visited[nx][ny] = 1;

                }
            }
        }

        return cheeseToMelt;
    }
}
