package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 로봇청소기2 {

    static int w, h;
    static char[][] board;
    static List<Point> points;
    static int[][] dist;
    static boolean[] visited;
    static int minDist;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            board = new char[h][w];
            points = new ArrayList<>();

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = line.charAt(j);
                    if (board[i][j] == 'o') {
                        points.add(0, new Point(j, i));     // 로봇은 index 0
                    } else if (board[i][j] == '*') {
                        points.add(new Point(j, i));
                    }
                }
            }

            int n = points.size();
            dist = new int[n][n];

            boolean impossible = false;
            for (int i = 0; i < n; i++) {
                int[][] d = BFS(points.get(i));
                for (int j = 0; j < n; j++) {
                    dist[i][j] = d[points.get(j).y][points.get(j).x];
                    if (i != j && dist[i][j] == -1) impossible = true;
                }
            }

            if (impossible) {
                System.out.println(-1);
                continue;
            }

            visited = new boolean[n];
            Arrays.fill(visited, false);
            visited[0] = true;
            minDist = Integer.MAX_VALUE;
            permute(0, 0, 1);
            System.out.println(minDist);
        }
    }

    private static int[][] BFS(Point start) {
        int[][] d = new int[h][w];
        for (int[] row : d) Arrays.fill(row, -1);

        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        d[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (0 <= nx && nx < w && 0 <= ny && ny < h) {
                    if (board[ny][nx] != 'x' && d[ny][nx] == -1) {
                        d[ny][nx] = d[point.y][point.x] + 1;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }

        return d;
    }

    private static void permute(int cur, int total, int count) {
        if (total >= minDist) return;

        if (count == points.size()) {
            minDist = Math.min(minDist, total);
            return;
        }

        for (int i = 1; i < points.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                permute(i, total + dist[cur][i], count + 1);
                visited[i] = false;
            }
        }
    }
}
