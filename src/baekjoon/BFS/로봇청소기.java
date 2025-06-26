package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇청소기 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, dir});

        if (map[x][y] == 0) {
            map[x][y] = 2;
            answer++;
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowDir = now[2];

            boolean allClean = true;
            int nextDir = nowDir;
            for (int i = 0; i < 4; i++) {
                nextDir = (nextDir + 3) % 4;

                int nextX = nowX + dx[nextDir];
                int nextY = nowY + dy[nextDir];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = 2;
                    answer++;
                    queue.add(new int[]{nextX, nextY, nextDir});
                    allClean = false;
                    break;
                }
            }

            if (allClean) {
                int backDir = (nowDir + 2) % 4;
                int nextX = nowX + dx[backDir];
                int nextY = nowY + dy[backDir];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 1) break;

                queue.add(new int[]{nextX, nextY, nowDir});
            }
        }

        System.out.println(answer);
    }
}
