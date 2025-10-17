package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 레이저통신 {

    static class Node {
        int x, y, dir, mirror;
        Node (int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int W, H;
    static char[][] map;
    static int[][][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        dist = new int[H][W][4];

        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') points.add(new int[]{i, j});
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        int[] start = points.get(0);
        int[] end = points.get(1);

        bfs(start, end);

        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[end[0]][end[1]][d]);
        }
        System.out.println(answer);
    }

    private static void bfs(int[] start, int[] end) {
        Queue<Node> q = new LinkedList<>();

        for (int d = 0; d < 4; d++) {
            dist[start[0]][start[1]][d] = 0;
            q.offer(new Node(start[0], start[1], d, 0));
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                if (map[nx][ny] == '*') continue;

                int nm = cur.mirror + ((cur.dir == d) ? 0 : 1);

                if (dist[nx][ny][d] > nm) {
                    dist[nx][ny][d] = nm;
                    q.offer(new Node(nx, ny, d, nm));
                }
            }
        }
    }
}
