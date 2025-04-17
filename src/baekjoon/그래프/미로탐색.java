package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 미로탐색 {

    /**
     * 4 6
     * 101111
     * 101010
     * 101011
     * 111011
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];
        int[][] visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);    // x, y, 이동 횟수
        pq.offer(new int[]{0, 0, 1});
        visited[0][0] = 1;

        int[] moveX = {0, 0, -1, 1};
        int[] moveY = {1, -1, 0, 0};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int distance = cur[2];

            if (x == n - 1 && y == m - 1) {
                answer = distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue;
                if (visited[newX][newY] == 0 && graph[newX][newY] == 1) {
                    visited[newX][newY] = 1;
                    pq.offer(new int[]{newX, newY, distance + 1});
                }
            }
        }

        System.out.println(answer);
    }
}
