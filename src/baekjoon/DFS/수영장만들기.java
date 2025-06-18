package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 수영장만들기 {

    static class Cell implements Comparable<Cell> {
        int x, y, height;

        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Cell o) {
            return this.height - o.height;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line.charAt(j) + "");
                if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    pq.add(new Cell(i, j, map[i][j]));
                    visited[i][j] = 1;
                }
            }
        }

        int totalWater = 0;

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] == 1) continue;

                visited[nx][ny] = 1;

                if (map[nx][ny] < curr.height) {
                    totalWater += curr.height - map[nx][ny];
                    pq.add(new Cell(nx, ny, curr.height));
                } else {
                    pq.add(new Cell(nx, ny, map[nx][ny]));
                }
            }
        }
        System.out.println(totalWater);
    }
}
