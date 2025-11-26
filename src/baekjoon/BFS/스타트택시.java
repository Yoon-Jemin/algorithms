package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트택시 {

    static int N, M, FUEL;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[] now = new int[2];
    static boolean impossible = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 지도의 크기
        M = Integer.parseInt(st.nextToken());
        FUEL = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        now[0] = Integer.parseInt(st.nextToken());
        now[1] = Integer.parseInt(st.nextToken());

        Map<String, String> info = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            info.put(startX + " " + startY, endX + " " + endY);
        }

        for (int i = 0; i < M; i++) {
            toCustomer(info);
            if (impossible) {
                System.out.println(-1);
                return;
            }

            String strDest = info.get(now[0] + " " + now[1]);
            String[] dest = strDest.split(" ");
            toDestination(info, Integer.parseInt(dest[0]), Integer.parseInt(dest[1]));
            if (impossible) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(FUEL);
    }

    private static void toCustomer(Map<String, String> info) {
        int[][] visited = new int[N + 1][N + 1];
        Queue<int[]> queue = new LinkedList<>();    // x, y, 이동 거리
        queue.add(new int[] {now[0], now[1], 0});
        visited[now[0]][now[1]] = 1;

        List<int[]> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];
            int dist = poll[2];

            if (dist > minDist) break;

            if (info.containsKey(x + " " + y)) {
                minDist = dist;
                candidates.add(new int[] {x, y});
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
                if (map[nx][ny] == 1 || visited[nx][ny] == 1) continue;
                queue.add(new int[] {nx, ny, dist + 1});
                visited[nx][ny] = 1;
            }
        }

        if (candidates.isEmpty()) {
            impossible = true;
        } else {
            candidates.sort((a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            });
            int[] pick = candidates.get(0);
            now[0] = pick[0];
            now[1] = pick[1];
            FUEL -= minDist;
            if (FUEL < 0) impossible = true;
        }
    }

    private static void toDestination(Map<String, String> info, int destX, int destY) {
        int[][] visited = new int[N + 1][N + 1];
        Queue<int[]> queue = new LinkedList<>();    // x, y, 이동 거리
        queue.add(new int[] {now[0], now[1], 0});
        visited[now[0]][now[1]] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];
            int dist = poll[2];

            if (x == destX && y == destY) {
                info.remove(now[0] + " " + now[1]);
                FUEL -= dist;
                now[0] = x;
                now[1] = y;
                if (FUEL < 0) {
                    impossible = true;
                } else {
                    FUEL += dist * 2;
                }
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
                if (map[nx][ny] == 1 || visited[nx][ny] == 1) continue;
                queue.add(new int[] {nx, ny, dist + 1});
                visited[nx][ny] = 1;
            }
        }

        impossible = true;
    }
}
